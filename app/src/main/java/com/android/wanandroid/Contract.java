package com.android.wanandroid;

import com.android.mymvp.base.BaseCallback;
import com.android.mymvp.base.Interface.IBasePresenter;
import com.android.mymvp.base.Interface.IBaseView;
import com.android.mymvp.base.Interface.IBaseViewback;
import com.android.wanandroid.module.book.bookbeans.BookNavigationBean;
import com.android.wanandroid.module.wechat.DataBean;

import com.android.wanandroid.module.wechat.WechatListBean;
import com.android.wanandroid.module.book.bookbeans.BookSystemBean;
import com.android.wanandroid.test.entity.Test;
import com.trello.rxlifecycle2.LifecycleProvider;

import java.util.List;

public interface Contract {
    interface TestRequest {
        interface testPresenter extends IBasePresenter<testView> {
            void getTest(int page);
        }

        interface testView extends IBaseView<testPresenter>, IBaseViewback<Test> {
        }
    }

    interface AppModeImpl {
        void getTest(LifecycleProvider provider, int page, BaseCallback<Test> callback);

        void bookNavigation(LifecycleProvider provider, BaseCallback<List<BookNavigationBean>> callback);

        void bookSystem(LifecycleProvider provider, BaseCallback<List<BookSystemBean>> callback);

        //公众号
        void wechat(LifecycleProvider provider,BaseCallback<List<DataBean>> callback);

        //公众号page页
        void  wechatlist(LifecycleProvider provider, int id, int page, BaseCallback<WechatListBean> callback);
    }

    //导航(book Navigation)
    public interface bookNavigationPresenter extends IBasePresenter<bookNavigationView> {
        void initNavigationPresenter();
    }

    public interface bookNavigationView extends IBaseView<bookNavigationPresenter> {
        void succeed(List<BookNavigationBean> beanList);

        void fail(String error);
    }

    //体系(book system)
    public interface bookSystemPresenter extends IBasePresenter<bookSystemView> {
        void initSystemPresenter();
    }

    public interface bookSystemView extends IBaseView<bookSystemPresenter> {
        void succeed(List<BookSystemBean> sysList);

        void fail(String error);
    }


    //公众号
    public interface  wechatPresenter extends IBasePresenter<wechatView>{
        void initwechatPresenter();
    }

    public interface wechatView extends  IBaseView<wechatPresenter>{
        void  succeed(List<DataBean> wechatList);
        void  fail(String error);
    }

    //公众号page页
    public interface  wechatlistPresenter extends IBasePresenter<wechatlistView>{
        void initwechatlistPresenter(int id,int page);
    }

    public interface wechatlistView extends  IBaseView<wechatlistPresenter>{
        void  succeed(WechatListBean wechatList);
        void  fail(String error);
    }
}
