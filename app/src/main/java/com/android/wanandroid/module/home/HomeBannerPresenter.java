package com.android.wanandroid.module.home;

import com.android.mymvp.base.BaseCallback;
import com.android.mymvp.base.BasePresenter;
import com.android.wanandroid.AppMode;
import com.android.wanandroid.Contract;

import java.util.List;

public class HomeBannerPresenter extends BasePresenter<Contract.homeBannerView, AppMode> implements Contract.homeBannerPresenter {
    @Override
    public AppMode initModel() {
        return AppMode.getAppMode();
    }

    @Override
    public void initHomeBannerPresenter() {
        getModel().getHomeBanner(getLifecycleProvider(), new BaseCallback<List<HomeBannerBean>>() {
            @Override
            public void onCallSuccessful(List<HomeBannerBean> value) {
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
