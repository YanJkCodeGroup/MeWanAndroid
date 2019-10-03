package com.android.mymvp.base.util;

import android.app.Activity;
import android.app.Application;
import android.content.ComponentCallbacks;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Build;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.lang.reflect.Field;
import java.math.BigDecimal;

public class Density {
   private static final int DEF_DPI = 160;
   private static float appDensity;
   private static float appScaledDensity;
   private static float xDpi = 480;
   private static float yDpi = 480;
   private static Application sApplication = getApplication();

   public final static String WIDTH = "width";
   public final static String HEIGHT = "height";

   /**
    * 获取当前的应用Application
    */
   private static final Application getApplication() {
      if (sApplication == null) {
         synchronized (Density.class) {
            if (sApplication == null) {
               try {
                  return (Application) Class
                          .forName("android.app.ActivityThread")
                          .getMethod("currentApplication")
                          .invoke(null, (Object[]) null);
               } catch (Exception e) {
                  e.printStackTrace();
               }
            }
         }
      }
      return sApplication;
   }

   /**
    * 此方法在BaseActivity中做初始化(如果不封装BaseActivity的话,直接用下面那个方法就好了)
    * 默认使用高进行适配
    */
   public static void setDefault(Activity activity) {
      setAppOrientation(activity, WIDTH, sApplication);
   }


   //此方法用于在某一个Activity里面更改适配的方向
   public static void setOrientation(Activity activity, String orientation) {
      setAppOrientation(activity, orientation, sApplication);
   }

   /**
    * 在application中调用
    * 初始化 设计图DPI
    */
   public static void initBlueprintDPI(int blueprintDPI) {
      xDpi = blueprintDPI;
      yDpi = blueprintDPI;
   }

   /**
    * targetDensity
    * targetScaledDensity
    * targetDensityDpi
    * 这三个参数是统一修改过后的值
    * <p>
    * orientation:方向值,传入width或height
    * application 为了获取系统参数
    */
   private static void setAppOrientation(@Nullable Activity activity, String orientation,
                                         @NonNull final Application application) {
      //获取application的DisplayMetrics
      DisplayMetrics appDisplayMetrics = application.getResources().getDisplayMetrics();
      //获取状态栏高度
      int barHeight = getStateBar2(application);
      xDpi = (float) (Math.sqrt(Math.pow(appDisplayMetrics.widthPixels, 2) + Math.pow(appDisplayMetrics.heightPixels, 2)) /
              getPingMuSize(application));
      yDpi = xDpi;
      if (appDensity == 0) {
         //初始化的时候赋值
         appDensity = appDisplayMetrics.density;
         appScaledDensity = appDisplayMetrics.scaledDensity;

         //添加字体变化的监听
         application.registerComponentCallbacks(new ComponentCallbacks() {
            @Override
            public void onConfigurationChanged(Configuration newConfig) {
               //字体改变后,将appScaledDensity重新赋值
               if (newConfig != null && newConfig.fontScale > 0) {
                  appScaledDensity = application.getResources().getDisplayMetrics().scaledDensity;
               }
            }

            @Override
            public void onLowMemory() {
            }
         });
      }

      float targetDensity;

      if (orientation.equals("height")) {
         targetDensity = (appDisplayMetrics.heightPixels - barHeight) / yDpi;
      } else {
         targetDensity = appDisplayMetrics.widthPixels / xDpi;
      }

      float targetScaledDensity = targetDensity * (appScaledDensity / appDensity);
      int targetDensityDpi = (int) (DEF_DPI * targetDensity);

      /**
       * 最后在这里将修改过后的值赋给系统参数
       */
      appDisplayMetrics.density = targetDensity;
      appDisplayMetrics.scaledDensity = targetScaledDensity;
      appDisplayMetrics.densityDpi = targetDensityDpi;


      DisplayMetrics activityDisplayMetrics = activity.getResources().getDisplayMetrics();
      activityDisplayMetrics.density = targetDensity;
      activityDisplayMetrics.scaledDensity = targetScaledDensity;
      activityDisplayMetrics.densityDpi = targetDensityDpi;
   }

   /**
    * 获取当前手机屏幕尺寸
    */
   public static float getPingMuSize(Context mContext) {
      WindowManager wm = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
      Display display = wm.getDefaultDisplay();
      DisplayMetrics dm = new DisplayMetrics();
      if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
         display.getRealMetrics(dm);
      } else {
         display.getMetrics(dm);
      }
      double x = Math.pow(dm.widthPixels / dm.xdpi, 2);
      double y = Math.pow(dm.heightPixels / dm.ydpi, 2);
      // 屏幕尺寸
      BigDecimal decimal = new BigDecimal(Math.sqrt(x + y));
      decimal = decimal.setScale(1, BigDecimal.ROUND_UP);
      double mScreenInches = decimal.doubleValue();
      return (float) mScreenInches;
   }

   /**
    * 获取当前手机的 状态栏高度
    *
    * @param context
    * @return
    */
   private static int getStateBar2(Context context) {
      Class c = null;
      try {
         c = Class.forName("com.android.internal.R$dimen");
         Object obj = c.newInstance();
         Field field = c.getField("status_bar_height");
         int x = Integer.parseInt(field.get(obj).toString());
         int statusBarHeight = context.getResources().getDimensionPixelSize(x);
         return statusBarHeight;
      } catch (Exception e) {
         e.printStackTrace();
      }
      return 0;
   }
}
