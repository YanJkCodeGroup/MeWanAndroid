package com.android.wanandroid.module.wechat.presetner;

import com.android.mymvp.base.BaseCallback;
import com.android.mymvp.base.BasePresenter;
import com.android.wanandroid.AppMode;
import com.android.wanandroid.Contract;
import com.android.wanandroid.module.wechat.entity.WechatBean;

import java.util.List;

public class WechatPresenter extends BasePresenter<Contract.WechatView, AppMode> implements Contract.WechatPresenter {
   @Override
   public AppMode initModel() {
      return AppMode.getAppMode();
   }

   @Override
   public void initwechatPresenter() {
      getModel().wechat(getLifecycleProvider(), new BaseCallback<List<WechatBean>>() {
         @Override
         public void onCallSuccessful(List<WechatBean> value) {
            super.onCallSuccessful(value);
            if (mView != null)
               mView.succeed(value);

         }

         @Override
         public <M extends Throwable> void onCallFailed(M msg) {
            super.onCallFailed(msg);
            if (mView != null)
               mView.fail(msg.getMessage());
         }
      });
   }
}
