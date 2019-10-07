package com.android.wanandroid.test;

import android.util.Log;

import com.android.mymvp.base.BaseMvpActivity;
import com.android.wanandroid.Contract;
import com.android.wanandroid.R;
import com.android.wanandroid.test.entity.Test;

public class MainActivity extends BaseMvpActivity<Contract.TestRequest.TestPresenter> implements Contract.TestRequest.TestView {

   @Override
   public int initLayout() {
      return R.layout.activity_main;
   }

   @Override
   protected void initView() {
      super.initView();
   }

   @Override
   protected void initData() {
      super.initData();
      mPresenter.getTest(0);
   }

   @Override
   public Contract.TestRequest.TestPresenter createPresenter() {
      return new TestMainPresenter();
   }


   @Override
   public void onSuccessful(Test test) {
      Log.d("test", test.toString());
      Log.d("test", test.getDatas().get(0).getEnvelopePic());
   }

   @Override
   public void onFailed(String msg) {
      Log.d("test", msg);
   }

}
