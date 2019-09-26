package com.android.wanandroid.test;

import com.android.mymvp.base.BaseCallback;
import com.android.mymvp.base.BasePresenter;
import com.android.wanandroid.AppMode;
import com.android.wanandroid.Contract;
import com.android.wanandroid.test.entity.Test;

public class TestMainPresenter extends BasePresenter<Contract.TestRequest.testView, AppMode> implements Contract.TestRequest.testPresenter {
   @Override
   public AppMode initModel() {
      return AppMode.getAppMode();
   }

   @Override
   public void getTest(int page) {
      getModel().getTest(getLifecycleProvider(), page, new BaseCallback<Test>() {
         @Override
         public void onCallSuccessful(Test value) {
            getView().onSuccessful(value);
         }
      });
   }
}
