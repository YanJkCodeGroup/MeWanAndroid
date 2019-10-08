package com.android.wanandroid.module.login;

import com.android.mymvp.base.BaseCallback;
import com.android.mymvp.base.BasePresenter;
import com.android.wanandroid.AppMode;
import com.android.wanandroid.Contract;

/**
 * @author Mr.Li：lkx
 * @description: I brandished my keyboard and book, vowing to write the world clearly.
 * @date : 2019/10/8 10:37
 */
public class LoginPresenter extends BasePresenter<Contract.ILoginView, AppMode> implements Contract.ILoginPresenter {
    @Override
    public AppMode initModel() {
        return AppMode.getAppMode();
    }

    @Override
    public void login(String username,String password) {
        getModel().login(getLifecycleProvider(), username, password, new BaseCallback<LoginData>() {
            @Override
            public void onCallSuccessful(LoginData value) {
                mView.onSuccess(value);
            }

            @Override
            public <M extends Throwable> void onCallFailed(M msg) {
                mView.onFail(msg.getMessage());
            }
        });
    }
}
