package com.android.utils.anim;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

@SuppressLint("ObjectAnimatorBinding")
public class AnimatorUtil {
   private static final List<Animator> mAnimatorList = new ArrayList<>();
   /**
    * 渐变
    */
   public static final String ALPHA = "alpha";
   /**
    * 旋转
    */
   public static final String ROTATION = "rotation";
   public static final String ROTATION_X = "rotationX";
   public static final String ROTATION_Y = "rotationY";
   /**
    * 缩放
    */
   public static final String SCALE_X = "scaleX";
   public static final String SCALE_Y = "scaleY";
   /**
    * 平移
    */
   public static final String TRANSLATION_Y = "translationY";
   public static final String TRANSLATION_X = "translationX";
   private static AnimatorSet sAnimatorSet;

   /**
    * 创建渐变动画
    *
    * @param view   要进行动画效果的控件
    * @param values 透明度 从多少到多少   最小0 最大1
    */
   public static ObjectAnimator createAlpha(View view, float... values) {
      ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(view, ALPHA, values);
      mAnimatorList.add(objectAnimator);
      return objectAnimator;
   }

   /**
    * 创建旋转动画 正面旋转
    *
    * @param view   要进行动画效果的控件
    * @param values 旋转角度   从多少到多少
    */
   public static ObjectAnimator createRotation(View view, float... values) {
      ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(view, ROTATION, values);
      mAnimatorList.add(objectAnimator);
      return objectAnimator;
   }

   /**
    * 创建旋转动画 X轴旋转
    *
    * @param view   要进行动画效果的控件
    * @param values 旋转角度   从多少到多少
    */
   public static ObjectAnimator createRotationX(View view, float... values) {
      ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(view, ROTATION_X, values);
      mAnimatorList.add(objectAnimator);
      return objectAnimator;
   }

   /**
    * 创建旋转动画 Y轴旋转
    *
    * @param view   要进行动画效果的控件
    * @param values 旋转角度   从多少到多少
    */
   public static ObjectAnimator createRotationY(View view, float... values) {
      ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(view, ROTATION_Y, values);
      mAnimatorList.add(objectAnimator);
      return objectAnimator;
   }

   /**
    * 创建缩放动画 X轴缩放
    *
    * @param view   要进行动画效果的控件
    * @param values 倍数  默认是1  0等于消失了不显示也点不到
    */
   public static ObjectAnimator createScaleX(View view, float... values) {
      ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(view, SCALE_X, values);
      mAnimatorList.add(objectAnimator);
      return objectAnimator;
   }

   /**
    * 创建缩放动画 Y轴缩放
    *
    * @param view   要进行动画效果的控件
    * @param values 倍数  默认是1  0等于消失了不显示也点不到
    */
   public static ObjectAnimator createScaleY(View view, float... values) {
      ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(view, SCALE_Y, values);
      mAnimatorList.add(objectAnimator);
      return objectAnimator;
   }

   /**
    * 创建平移动画 Y轴平移
    *
    * @param view   要进行动画效果的控件
    * @param values 从屏幕左上0为开始 开始Y值  结束Y值
    */
   public static ObjectAnimator createTranslationY(View view, float... values) {
      ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(view, TRANSLATION_Y, values);
      mAnimatorList.add(objectAnimator);
      return objectAnimator;
   }

   /**
    * 创建平移动画 X轴平移
    *
    * @param view   要进行动画效果的控件
    * @param values 从屏幕左上0为开始 开始X值  结束X值
    */
   public static ObjectAnimator createTranslationX(View view, float... values) {
      ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(view, TRANSLATION_X, values);
      mAnimatorList.add(objectAnimator);
      return objectAnimator;
   }

   public static AnimatorSet createAnimatorSet() {
      sAnimatorSet = new AnimatorSet();
      sAnimatorSet.playTogether(mAnimatorList);
      return sAnimatorSet;
   }
}
