package com.android.wanandroid;

import androidx.annotation.IntDef;

public class HttpConfig {
   public static final int SERVICE_TYPE_RELEASE = 1;
   public static final int SERVICE_TYPE_EXTERNAL_DEBUG = 2;
   public static final int SERVICE_TYPE_DEBUG = 3;
   public static String BASE_URL;
   private static int type = 1;

   public static void setType(@ServiceType int type) {
      HttpConfig.type = type;
      isTypeService();
   }

   private static void isTypeService() {
      switch (type) {
         case SERVICE_TYPE_RELEASE:
            BASE_URL = "";
            break;
         case SERVICE_TYPE_EXTERNAL_DEBUG:
            BASE_URL = "";
            break;
         case SERVICE_TYPE_DEBUG:
            BASE_URL = "https://wanandroid.com/";
            break;
      }
   }

   @IntDef({SERVICE_TYPE_RELEASE, SERVICE_TYPE_EXTERNAL_DEBUG, SERVICE_TYPE_DEBUG})
   private @interface ServiceType {
   }
}
