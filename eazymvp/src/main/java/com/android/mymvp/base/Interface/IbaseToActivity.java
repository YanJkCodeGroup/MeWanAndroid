package com.android.mymvp.base.Interface;

import android.content.Intent;

public interface IbaseToActivity {
   /**
    * 启动一个activity
    *
    * @param tClass 必填跳转到那个activit
    * @param intent 可选带不带数据
    */
   void toActivity(Class<?> tClass, Intent intent);

   /**
    * 启动一个回传值的activity
    *
    * @param tClass 必填跳转到那个activit
    * @param intent 可选带不带数据
    */
   void toActivityForResult(Class<?> tClass, int resuleCode, Intent intent);
}
