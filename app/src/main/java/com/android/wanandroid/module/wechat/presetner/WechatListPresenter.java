package com.android.wanandroid.module.wechat.presetner;

import com.android.mymvp.base.BaseCallback;
import com.android.mymvp.base.BasePresenter;
import com.android.wanandroid.AppMode;
import com.android.wanandroid.Contract;
import com.android.wanandroid.module.wechat.entity.WechatListBean;

public class WechatListPresenter extends BasePresenter<Contract.WechatlistView, AppMode> implements Contract.WechatlistPresenter {

   @Override
   public AppMode initModel() {
      return AppMode.getAppMode();
   }


   @Override
   public void initwechatlistPresenter(int id, int page) {
      getModel().wechatList(getLifecycleProvider(), id, page, new BaseCallback<WechatListBean>() {
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
