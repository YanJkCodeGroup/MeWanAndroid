package com.android.wanandroid.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.android.wanandroid.R;

/**
 * @author CuiZhen
 * @date 2019/5/15
 * QQ: 302833254
 * E-mail: goweii@163.com
 * GitHub: https://github.com/goweii
 */
public class CollectView extends RevealLayout implements View.OnTouchListener {

   private OnClickListener mOnClickListener = null;

   public CollectView(Context context) {
      this(context, null);
   }

   public CollectView(Context context, AttributeSet attrs) {
      this(context, attrs, 0);
   }

   public CollectView(Context context, AttributeSet attrs, int defStyleAttr) {
      super(context, attrs, defStyleAttr);
   }

   @Override
   protected void initAttr(AttributeSet attrs) {
      super.initAttr(attrs);
      setCheckWithExpand(true);
      setUncheckWithExpand(false);
      setCheckedLayoutId(R.layout.layout_collect_view_checked);
      setUncheckedLayoutId(R.layout.layout_collect_view_unchecked);
      setAnimDuration(500);
      setAllowRevert(true);
      setOnTouchListener(this);
   }

   @Override
   public boolean onTouch(View v, MotionEvent event) {
      switch (event.getAction()) {
         default:
            break;
         case MotionEvent.ACTION_UP:
            //todo 判断是否登录
            if (true) {
               if (mOnClickListener != null) {
                  mOnClickListener.onClick(this);
               }
            }
            break;
      }
      //todo todo 判断是否登录
      return false;
   }

   public void setOnClickListener(OnClickListener onClickListener) {
      mOnClickListener = onClickListener;
   }

   public interface OnClickListener {
      void onClick(CollectView v);
   }
}