package com.android.utils.custom;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.Checkable;
import android.widget.ImageView;

import androidx.annotation.Nullable;

/*
 * created by taofu on 2019-09-09
 **/
@SuppressLint("AppCompatCustomView")
public class SelectorImageView extends ImageView implements Checkable {

   //可选中的状态码
   int[] CHECKED_STATE_SET = {android.R.attr.state_checked};


   private boolean mChecked = false;   //选中状态

   public SelectorImageView(Context context) {
      super(context);
   }

   public SelectorImageView(Context context, @Nullable AttributeSet attrs) {
      super(context, attrs);
   }

   public SelectorImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
      super(context, attrs, defStyleAttr);
   }

   public void playAnimation() {
      if (isChecked()) {
         // 播放从选中到未选中的动画
      } else {
         // 播放 从未选中到选中的动画
      }
   }

   /**
    * 创建控件状态类型
    *
    * @param extraSpace 当前数组里的状态数据量
    * @return 返回状态数组
    */
   @Override
   public int[] onCreateDrawableState(int extraSpace) {
      //调用父类的本方法可以获得到在父类添加过状态的数组,我们给数组加1然后添加我们的状态到状态数组中
      final int[] drawableState = super.onCreateDrawableState(extraSpace + 1);
      if (isChecked()) {//判断当前的状态是不是true 如果是就添加状态到数组中
         mergeDrawableStates(drawableState, CHECKED_STATE_SET);//调用view自带的合并功能添加状态
      }
      return drawableState;//把添加好的数组返回给父类
   }

   /**
    * 设置选中状态并刷新当前控件的状态
    *
    * @param checked
    */
   @Override
   public void setChecked(boolean checked) {
      if (mChecked != checked) {
         mChecked = checked;
         refreshDrawableState();
      }
   }

   /**
    * 获取当前状态
    */
   @Override
   public boolean isChecked() {
      return mChecked;
   }

   @Override
   public void toggle() {
      setChecked(!mChecked);
   }
}
