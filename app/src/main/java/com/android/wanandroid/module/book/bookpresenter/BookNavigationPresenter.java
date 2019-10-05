package com.android.wanandroid.module.book.bookpresenter;

import com.android.mymvp.base.BaseCallback;
import com.android.mymvp.base.BasePresenter;
import com.android.wanandroid.AppMode;
import com.android.wanandroid.Contract;
import com.android.wanandroid.module.book.bookbeans.BookNavigationBean;

import java.util.List;

public class BookNavigationPresenter extends BasePresenter<Contract.bookNavigationView, AppMode> implements Contract.bookNavigationPresenter {
    @Override
    public AppMode initModel() {
        return AppMode.getAppMode();
    }

    @Override
    public void initNavigationPresenter() {
        getModel().bookNavigation(getLifecycleProvider(), new BaseCallback<List<BookNavigationBean>>() {
            @Override
            public void onCallSuccessful(List<BookNavigationBean> value) {
                super.onCallSuccessful(value);
                getView().succeed(value);
            }

            @Override
            public <M extends Throwable> void onCallFailed(M msg) {
                super.onCallFailed(msg);
                getView().fail(msg.getMessage());
            }
        });
    }
}