package com.android.wanandroid.module.book.bookpresenter;

import com.android.mymvp.base.BaseCallback;
import com.android.mymvp.base.BasePresenter;
import com.android.wanandroid.AppMode;
import com.android.wanandroid.Contract;
import com.android.wanandroid.module.book.bookbeans.BookSystemBean;

import java.util.List;

public class BookSystemPresenter extends BasePresenter<Contract.bookSystemView, AppMode> implements Contract.bookSystemPresenter {
    @Override
    public AppMode initModel() {
        return AppMode.getAppMode();
    }

    @Override
    public void initSystemPresenter() {
        getModel().bookSystem(getLifecycleProvider(), new BaseCallback<List<BookSystemBean>>() {
            @Override
            public void onCallSuccessful(List<BookSystemBean> value) {
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
