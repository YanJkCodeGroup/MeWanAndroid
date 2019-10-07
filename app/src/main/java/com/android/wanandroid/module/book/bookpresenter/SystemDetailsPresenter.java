package com.android.wanandroid.module.book.bookpresenter;

import com.android.mymvp.base.BaseCallback;
import com.android.mymvp.base.BasePresenter;
import com.android.wanandroid.AppMode;
import com.android.wanandroid.Contract;
import com.android.wanandroid.module.book.bookbeans.SystemDetailsBean;

public class SystemDetailsPresenter extends BasePresenter<Contract.SystemDetailsView, AppMode> implements Contract.SystemDetailsPresenter {

    @Override
    public AppMode initModel() {
        return AppMode.getAppMode();
    }

    @Override
    public void initSystemDetailsPresenter(int page, int id) {
        getModel().systemDetails(getLifecycleProvider(), page, id, new BaseCallback<SystemDetailsBean>() {
            @Override
            public void onCallSuccessful(SystemDetailsBean value) {
                mView.succeed(value);
            }

            @Override
            public <M extends Throwable> void onCallFailed(M msg) {
                mView.fail(msg.getMessage());
            }
        });
    }
}
