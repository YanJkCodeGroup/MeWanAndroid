package com.android.mymvp.base;

import android.util.Log;

import com.android.httpservice.http.HttpResult;
import com.android.httpservice.http.HttpUtil;
import com.trello.rxlifecycle2.LifecycleProvider;
import com.trello.rxlifecycle2.LifecycleTransformer;
import com.trello.rxlifecycle2.android.ActivityEvent;
import com.trello.rxlifecycle2.android.FragmentEvent;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;
import com.trello.rxlifecycle2.components.support.RxFragment;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public abstract class BaseModel<HttpService> {

   private HttpService httpService;

   {
      //判断是否初始化网络请求接口对象
      if (isOpenHttpService()) {
         createHttpService();
      }
   }


   /**
    * 如果 isOpenHttpService() 返回true则会初始化一个网络请求接口
    * 需要重写getHttpServiceClass和GetBaseUrl
    */
   private final void createHttpService() {
      Class<HttpService> httpServiceClass = getHttpServiceClass();
      if (httpServiceClass == null) {
         if (isOpenHttpService())
            Log.d("BaseModel", "httpServiceClass = null," +
                    "请确认重写了 getHttpServiceClass() 并传入请求服务接口 ");

         return;
      }

      String baseUrl = getBaseUrl();
      if (!baseUrl.matches("^([http:\\/\\/]|[https:\\/\\/])?[^\\s\\.]?[^\\s]+\\.[^\\s]+\\/+$")) {
         if (isOpenHttpService())
            Log.d("BaseModel", "跟地址不正确,请确认重写了getBaseUrl() 并传入了正确的跟地址");
         return;
      }

      httpService = HttpUtil.getHttpUtil().getService(baseUrl, httpServiceClass);
   }

   /**
    * @return 网络请求服务接口对象
    */
   protected final HttpService getHttpService() {
      return httpService;
   }

   /**
    * 判断是否启动Http服务
    */
   protected abstract boolean isOpenHttpService();

   /**
    * @return 服务接口的运行时类
    */
   protected Class<HttpService> getHttpServiceClass() {
      return null;
   }

   /**
    * @return 服务器的跟地址 ,可以是http或https,
    */
   protected String getBaseUrl() {
      return "";
   }

   /**
    * observer方法的默认flatMap方法
    *
    * @param provider
    * @param observable 网络请求回的Observable<T>
    * @param callback   P层传入的返回接口
    * @param <T>        网络请求返回时的泛型
    * @param <R>        被Function处理完成后的类型
    */
   protected final <T extends HttpResult, R> void observer(LifecycleProvider provider,
                                                           Observable<T> observable,
                                                           BaseCallback<R> callback) {
      observer(provider, observable, null, callback);
   }

   /**
    * 把处理完成的数据返回为callback指定的类型
    *
    * @param provider
    * @param observable 网络请求回的Observable<T>
    * @param flatMap    定义创建一个Function定义如何处理Observable的数据
    * @param <T>        网络请求返回时的泛型
    * @param <R>        被Function处理完成后的类型
    */
   protected <T extends HttpResult, R> void observer(LifecycleProvider provider,
                                                     Observable<T> observable,
                                                     Function<T, ObservableSource<R>> flatMap,
                                                     BaseCallback<R> baseCallback) {
      //以被观察者调用flatmap处理数据,flatmap是在model层所写的数据处理方式
      Observable<R> rObservable;
      if (flatMap == null) {
         rObservable = observable.flatMap(new Function<T, ObservableSource<R>>() {
            @Override
            public ObservableSource<R> apply(T t) throws Exception {
               if (t == null) {
                  return Observable.error(new Exception("请求失败,数据为空"));
               }
               Object data = t.getData();
               return data == null ?
                       (ObservableSource<R>) Observable.error(new Exception(t.getMessage())) :
                       data instanceof String ?
                               (ObservableSource<R>) Observable.just(t.getMessage()) :
                               (ObservableSource<R>) Observable.just(data);
            }
         });
      } else {
         rObservable = observable.flatMap(flatMap);
      }
      rObservable
              .compose(rxObservableTransformer())
              .compose(isLifectcleProviderType(provider))//用于防止Activity关闭时回调造成的内存泄露与空指针
              //处理完成发送数据到callBack
              .subscribe(new BaseObserver(baseCallback));
   }

   /**
    * 用于observble的线程切换功能
    *
    * @param <T>
    * @return
    */
   protected <T> ObservableTransformer<T, T> rxObservableTransformer() {
      return new ObservableTransformer<T, T>() {
         @Override
         public ObservableSource<T> apply(Observable<T> upstream) {
            //把当前的observble的线程转换到io线程请求,把返回结果的线程设置为主线程
            return upstream
                    .subscribeOn(Schedulers.io())
                    .unsubscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread());
         }
      };
   }


   /**
    * 用于Rxjava的防内存溢出
    *
    * @param provider
    * @param <T>
    * @return
    */
   protected <T> LifecycleTransformer<T> isLifectcleProviderType(LifecycleProvider provider) {
      //三元运算符 判断这个类是否为RxFragment的子类或RxFragment本身   如果是则以RxFragment的方式处理
      return (LifecycleTransformer<T>) (provider instanceof RxFragment ?
                    //RxFragment的处理方式,把provider对象转换成RxFragment,
                    // 调用bindUntilEvent方法设置为在DESTROY,是销毁时取消订阅状态
                    ((RxFragment) provider).bindUntilEvent(FragmentEvent.DESTROY) :
                    //RxAppCompatActivity的处理方式,也是先转换对象
                    //然后调用bindUntilEvent方法设置为在ActivityEvent.DESTROY,是销毁时取消订阅状态
                    //还有各种生命周期状态可以选择,ActivityEvent和FragmentEvent的生命周期方法不同
                    //所以提供的方法也不同
                    ((RxAppCompatActivity) (provider)).bindUntilEvent(ActivityEvent.DESTROY));
   }


}
