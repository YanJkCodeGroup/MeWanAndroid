package com.android.wanandroid.module.home;

import com.android.mymvp.base.BaseCallback;
import com.android.mymvp.base.BasePresenter;
import com.android.wanandroid.AppMode;
import com.android.wanandroid.Contract;

import java.util.List;

public class HomePresenter extends BasePresenter<Contract.HomeView, AppMode> implements Contract.HomePresenter {
    @Override
    public AppMode initModel() {
        return AppMode.getAppMode();
    }


    @Override
    public void initHomePresenter(int page) {
        getModel().getHome(getLifecycleProvider(), new BaseCallback<HomeBean>() {
            @Override
            public void onCallSuccessful(HomeBean value) {
                super.onCallSuccessful(value);
                if (mView != null) {
                    mView.homeBeanSucceed(value);
                }
            }

            @Override
            public <M extends Throwable> void onCallFailed(M msg) {
                super.onCallFailed(msg);
                if (mView != null) {
                    mView.homeFail(msg.getMessage());
                }
            }
        },page);
    }

    @Override
    public void initHomeBannerPresenter() {
        getModel().getHomeBanner(getLifecycleProvider(), new BaseCallback<List<HomeBannerBean>>() {
            @Override
            public void onCallSuccessful(List<HomeBannerBean> value) {
                super.onCallSuccessful(value);
                if (mView != null) {
                    mView.homeBannerSucceed(value);
                }
            }

            @Override
            public <M extends Throwable> void onCallFailed(M msg) {
                super.onCallFailed(msg);
                if (mView != null) {
                    mView.homeBannerFail(msg.getMessage());
                }
            }
        });
    }
}
