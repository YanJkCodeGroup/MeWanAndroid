package com.android.wanandroid.module.wechat.wehcatpresetner;

import com.android.mymvp.base.BaseCallback;
import com.android.mymvp.base.BasePresenter;
import com.android.wanandroid.AppMode;
import com.android.wanandroid.Contract;

import com.android.wanandroid.module.wechat.WechatListBean;

import java.util.List;

public class WechatListPresenter extends BasePresenter<Contract.wechatlistView, AppMode> implements Contract.wechatlistPresenter {

    @Override
    public AppMode initModel() {
        return AppMode.getAppMode();
    }


    @Override
    public void initwechatlistPresenter(int id, int page) {
        getModel().wechatlist(getLifecycleProvider(), id, page, new BaseCallback<WechatListBean>() {
            @Override
            public void onCallSuccessful(WechatListBean value) {
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
