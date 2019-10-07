package com.android.wanandroid.app;

import android.app.Application;

import com.android.utils.system.SystemFacade;
import com.android.wanandroid.HttpConfig;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;

public class WanAndroidApplication extends Application {

   static {
      //设置全局的Header构建器
      SmartRefreshLayout.setDefaultRefreshHeaderCreator((context, layout) -> {
         layout.setPrimaryColorsId(android.R.color.darker_gray, android.R.color.white);//全局设置主题颜色
         return new ClassicsHeader(context);//.setTimeFormat(new DynamicTimeFormat("更新于 %s"));
         // 指定为经典Header，默认是 贝塞尔雷达Header
      });
      //设置全局的Footer构建器
      SmartRefreshLayout.setDefaultRefreshFooterCreator((context, layout) -> {
         //指定为经典Footer，默认是 BallPulseFooter
         return new ClassicsFooter(context).setDrawableSize(20);
      });
   }

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
