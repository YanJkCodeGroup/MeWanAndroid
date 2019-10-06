package com.android.wanandroid.module.wechat.wehcatpresetner;

import com.android.mymvp.base.BaseCallback;
import com.android.mymvp.base.BasePresenter;
import com.android.wanandroid.AppMode;
import com.android.wanandroid.Contract;
import com.android.wanandroid.module.wechat.DataBean;
import com.android.wanandroid.module.wechat.WechatBean;

import java.util.List;

public class WechatPresenter extends BasePresenter<Contract.wechatView, AppMode> implements Contract.wechatPresenter {
    @Override
    public AppMode initModel() {
        return AppMode.getAppMode();
    }
    @Override
    public void initwechatPresenter() {
    getModel().wechat(getLifecycleProvider(), new BaseCallback<List<DataBean>>() {
        @Override
        public void onCallSuccessful(List<DataBean> value) {
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
