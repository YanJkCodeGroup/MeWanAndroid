package com.android.wanandroid.app;

import android.app.Application;

import com.android.utils.system.SystemFacade;
import com.android.wanandroid.HttpConfig;

public class WanAndroidApplication extends Application {

   @Override
   public void onCreate() {
      super.onCreate();
      if (SystemFacade.isApkInDebug(this)) {
         HttpConfig.setType(HttpConfig.SERVICE_TYPE_DEBUG);
      } else {
         HttpConfig.setType(HttpConfig.SERVICE_TYPE_RELEASE);
      }
      //Density.initBlueprintDPI(480);
   }
}
