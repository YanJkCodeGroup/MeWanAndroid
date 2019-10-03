package com.android.wanandroid.utils;

import android.app.Application;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.ColorInt;
import androidx.annotation.LayoutRes;

import com.android.wanandroid.R;

public class TabLayoutUtil {
   private static Context context = getApplicationContext();

   /**
    * 获取当前的应用ApplicationContext
    */
   private static final Context getApplicationContext() {
      if (context == null) {
         synchronized (TabLayoutUtil.class) {
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

   public static View getTabView(int icon, String title, @LayoutRes int layoutId) {
      View view = LayoutInflater.from(context)
              .inflate(layoutId, null);
      TabViewHolder holder = new TabViewHolder(view);
      holder.tvTitle.setText(title);
      holder.ivIcon.setImageResource(icon);
      return view;
   }

   public static View getTabView(int icon, int layoutId) {
      View view = LayoutInflater.from(context)
              .inflate(layoutId, null);
      TabIconViewHolder holder = new TabIconViewHolder(view);
      holder.ivIcon.setImageResource(icon);
      return view;
   }

   public static View getTabView(String title, @ColorInt int colorResId, @LayoutRes int layoutId) {
      View view = LayoutInflater.from(context)
              .inflate(layoutId, null);
      TabViewHolder holder = new TabViewHolder(view);
      holder.tvTitle.setText(title);
      holder.tvTitle.setTextColor(colorResId);
      return view;
   }

   static class TabViewHolder {
      @butterknife.BindView(R.id.icon)
      ImageView ivIcon;
      @butterknife.BindView(R.id.title)
      TextView tvTitle;

      public TabViewHolder(View view) {
         butterknife.ButterKnife.bind(this, view);
      }
   }

   static class TabIconViewHolder {
      @butterknife.BindView(R.id.icon)
      ImageView ivIcon;

      public TabIconViewHolder(View view) {
         butterknife.ButterKnife.bind(this, view);
      }
   }
}
