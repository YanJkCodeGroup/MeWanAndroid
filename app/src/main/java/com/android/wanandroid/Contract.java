package com.android.wanandroid;

import com.android.mymvp.base.BaseCallback;
import com.android.mymvp.base.Interface.IBasePresenter;
import com.android.mymvp.base.Interface.IBaseView;
import com.android.mymvp.base.Interface.IBaseViewback;
import com.android.wanandroid.module.book.bookbeans.BookNavigationBean;
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
    }

    //导航(book Navigation)
    public interface bookNavigationPresenter extends IBasePresenter<bookNavigationView> {
        void initNavigationPresenter();
    }

    public interface bookNavigationView extends IBaseView<bookNavigationPresenter> {
        void succeed(List<BookNavigationBean> beanList);

        void fail(String error);
    }

}
