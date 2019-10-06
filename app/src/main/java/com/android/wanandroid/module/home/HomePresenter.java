package com.android.wanandroid.module.home;

import com.android.mymvp.base.BaseCallback;
import com.android.mymvp.base.BasePresenter;
import com.android.wanandroid.AppMode;
import com.android.wanandroid.Contract;

import java.util.List;

public class HomePresenter extends BasePresenter<Contract.homeView, AppMode> implements Contract.homePresenter{
    @Override
    public AppMode initModel() {
        return AppMode.getAppMode();
    }

    @Override
    public void initHomePresenter() {
        getModel().getHome(getLifecycleProvider(), new BaseCallback<List<HomeBean>>() {
            @Override
            public void onCallSuccessful(List<HomeBean> value) {
                super.onCallSuccessful(value);
//                getView().succeed(value);
            }

            @Override
            public <M extends Throwable> void onCallFailed(M msg) {
                super.onCallFailed(msg);
//                getView().fail(msg.getMessage());
            }
        });
    }

    @Override
    public void initHomeBannerPresenter() {

    }
}
