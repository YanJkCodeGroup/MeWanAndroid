package com.android.wanandroid.module.wechat.wehcatpresetner;

import com.android.mymvp.base.BaseCallback;
import com.android.mymvp.base.BasePresenter;
import com.android.wanandroid.AppMode;
import com.android.wanandroid.Contract;
import com.android.wanandroid.module.wechat.WechatListBean;

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
            if (mView != null)
               mView.succeed(value);

         }

         @Override
         public <M extends Throwable> void onCallFailed(M msg) {
            if (mView != null)
               mView.fail(msg.getMessage());
         }
      });
   }
}
