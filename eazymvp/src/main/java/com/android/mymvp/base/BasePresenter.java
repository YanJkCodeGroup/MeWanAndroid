package com.android.mymvp.base;

import com.android.mymvp.base.Interface.IBaseModel;
import com.android.mymvp.base.Interface.IBasePresenter;
import com.android.mymvp.base.Interface.IBaseView;
import com.trello.rxlifecycle2.LifecycleProvider;

import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public abstract class BasePresenter<V extends IBaseView, M extends BaseModel>
        implements IBasePresenter<V>,
        IBaseModel<M> {

   protected V mView;
   protected WeakReference<M> mModel = new WeakReference<>(initModel());
   private static ExecutorService executorService;

   /**
    * 获取V层对象 在创建创建MVP时就调用了该方法
    */
   @Override
   public void attachView(V v) {
      this.mView = v;
   }

   /**
    * 把绑定的view转换成LifecycleProvider 发送到M层用
    */
   protected final LifecycleProvider getLifecycleProvider() {
      if (mView == null) {
         return null;
      }
      return (LifecycleProvider) mView;
   }

   /**
    * 获取线程池
    */
   public final ExecutorService getThreadPool() {
      if (executorService == null) {
         synchronized (BasePresenter.class) {
            if (executorService == null) {
               executorService = Executors.newCachedThreadPool();
            }
         }
      }
      return executorService;
   }

   public Map<String, String> getStringHashMap() {
      return new HashMap();
   }

   @Override
   public void detachView() {
      //销毁了则置空线程池
      if (executorService != null) {
         executorService = null;
      }
      if (mModel != null) {
         mModel.clear();
         mModel = null;
      }
      //view置空
      if (mView != null) {
         mView = null;
      }
   }

   public final M getModel() {
      return mModel.get();
   }

}
