package com.android.utils.file;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

public class SPUtil {

   private static Context context = createApplicationContext();

   /**
    * 获取当前的应用ApplicationContext
    */
   private static final Context createApplicationContext() {
      if (context == null) {
         synchronized (SPUtil.class) {
            if (context == null) {
               try {
                  context = (Application) Class
                          .forName("android.app.ActivityThread")
                          .getMethod("currentApplication")
                          .invoke(null, (Object[]) null);
               } catch (Exception e) {
                  e.printStackTrace();
               }
            }
         }
      }
      return context;
   }

   //文件名
   private static final String FILE_NAME = "share_data";

   /**
    * 把数据存储到默认文件下
    *
    * @param key    键
    * @param object 值
    * @return Commit提交有返回值
    */
   public static boolean putDefDataByCommit(String key, Object object) {
      return putDataByCommit(FILE_NAME, key, object);
   }

   /**
    * 把数据存储到默认文件下,Apply提交没有返回值
    *
    * @param key    键
    * @param object 值
    */
   public static void putDefDataByApply(String key, Object object) {
      putDataByApply(FILE_NAME, key, object);
   }

   /**
    * 保存数据,用Commit提交
    *
    * @param key    键
    * @param object 值
    * @return Commit提交有返回值
    */
   public static boolean putDataByCommit(String fileName, String key, Object object) {
      return putData(fileName, key, object).commit();
   }

   /**
    * 保存数据,用Apply提交
    *
    * @param key    键
    * @param object 值
    */
   public static void putDataByApply(String fileName, String key, Object object) {
      putData(fileName, key, object).apply();
   }

   /**
    * 保存数据
    *
    * @param fileName
    * @param key
    * @param object
    * @return
    */
   private static SharedPreferences.Editor putData(String fileName, String key, Object object) {
      return putData(key, object, context
              .getSharedPreferences(fileName, Context.MODE_PRIVATE)
              .edit(), object.getClass()
              .getSimpleName());
   }

   /**
    * 存储数据
    *
    * @param key
    * @param object
    * @param editor
    * @param simpleName
    */
   private static SharedPreferences.Editor putData(String key, Object object,
                                                   SharedPreferences.Editor editor,
                                                   String simpleName) {
      switch (simpleName) {
         case "String":
            editor.putString(key, (String) object);
            break;
         case "Integer":
            editor.putInt(key, (Integer) object);
            break;
         case "Boolean":
            editor.putBoolean(key, (Boolean) object);
            break;
         case "Float":
            editor.putFloat(key, (Float) object);
            break;
         case "Long":
            editor.putLong(key, (Long) object);
            break;
      }
      return editor;
   }

   public static <T> T getDefData(String key, T t) {
      return getData(FILE_NAME, key, t);
   }

   /**
    * 读取数据
    *
    * @param key      键
    * @param defaultT 默认值(缺省值)
    * @return 省缺值的类型
    */
   public static <T> T getData(String fileName, String key, T defaultT) {
      SharedPreferences sp = context
              .getSharedPreferences(fileName, Context.MODE_PRIVATE);
      switch (defaultT.getClass().getSimpleName()) {
         case "String":
            defaultT = (T) sp.getString(key, (String) defaultT);
            break;
         case "Integer":
            defaultT = (T) ((Integer) sp.getInt(key, (Integer) defaultT));
            break;
         case "Boolean":
            defaultT = (T) ((Boolean) sp.getBoolean(key, (Boolean) defaultT));
            break;
         case "Float":
            defaultT = (T) ((Float) sp.getFloat(key, (Float) defaultT));
            break;
         case "Long":
            defaultT = (T) ((Long) sp.getLong(key, (Long) defaultT));
            break;
      }
      return defaultT;
   }
}