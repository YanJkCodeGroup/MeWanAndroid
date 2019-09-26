package com.android.utils.popup;

import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;

public class PopupUtil {
   private static PopupUtil popupUtil;
   private PopupWindow popupWindow;

   private PopupUtil() {
   }

   public static PopupUtil getpopupUtil() {
      if (popupUtil == null) {
         synchronized (PopupUtil.class) {
            if (popupUtil == null) {
               popupUtil = new PopupUtil();
            }
         }
      }
      return popupUtil;
   }

   /**
    * 自适应模式
    */
   public static final int TYPE_WRAP_CONTENT = 0;
   /**
    * 全屏模式
    */
   public static final int TYPE_MATCH_PARENT = 1;

   /**
    * 创建popup
    *
    * @param view 布局对象
    * @param type 显示方式 自适应模式,全屏模式
    * @return 返回的工具类对象
    */
   public PopupUtil createPopup(View view, int type) {
      switch (type) {
         case 0:
            popupWindow = new PopupWindow(view, ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            break;
         case 1:
            popupWindow = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT);
            break;
      }
      if (popupWindow != null) {
         popupWindow.setOutsideTouchable(true);
         popupWindow.setBackgroundDrawable(null);
         popupWindow.setFocusable(true);
      }
      return popupUtil;
   }

   /**
    * 创建popup
    *
    * @param view 布局对象
    * @return 返回的工具类对象
    */
   public PopupUtil createPopup(View view) {
      popupWindow = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT,
              ViewGroup.LayoutParams.MATCH_PARENT);
      if (popupWindow != null) {
         popupWindow.setOutsideTouchable(true);
         popupWindow.setBackgroundDrawable(null);
         popupWindow.setFocusable(true);
      }
      return popupUtil;
   }

   /**
    * @param view 显示到指定控件
    */
   public void showAt(View view) {
      popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);
   }

   /**
    * @param view 显示到指定控件
    * @param x    偏移X(横向)
    * @param y    偏移Y(竖向)
    */
   public void showAt(View view, int x, int y) {
      popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);
   }

   /**
    * @param view    显示到指定控件
    * @param gravity 设置显示方式如creter
    * @param x       偏移X(横向)
    * @param y       偏移Y(竖向)
    */
   public void showAt(View view, int gravity, int x, int y) {
      popupWindow.showAtLocation(view, gravity, 0, 0);
   }

   /**
    * @param view 显示到指定控件下方
    */
   public void showAs(View view) {
      popupWindow.showAsDropDown(view);
   }

   /**
    * @param view 显示到指定控件下方
    * @param x    偏移X(横向)
    * @param y    偏移Y(竖向)
    */
   public void showAs(View view, int x, int y) {
      popupWindow.showAsDropDown(view, x, y);
   }

   /**
    * 隐藏控件
    */
   public void dismiss() {
      popupWindow.dismiss();
   }
}
