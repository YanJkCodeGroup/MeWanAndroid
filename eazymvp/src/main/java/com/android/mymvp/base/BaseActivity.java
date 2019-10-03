package com.android.mymvp.base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.annotation.IdRes;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.android.loadingview.LoadingView;
import com.android.mymvp.base.Interface.IBaseContext;
import com.android.mymvp.base.Interface.IBaseLayout;
import com.android.mymvp.base.Interface.IbaseFragmentAddManeage;
import com.android.mymvp.base.Interface.IbaseToActivity;
import com.android.mymvp.base.util.Density;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseActivity extends RxAppCompatActivity implements IBaseContext, IBaseLayout
        , IbaseToActivity, IbaseFragmentAddManeage {

   private Unbinder mBind;
   private LoadingView mLoadingView;

   @Override
   protected void onCreate(@Nullable Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      if (!isShowTitle()) {
         //无title
         requestWindowFeature(Window.FEATURE_NO_TITLE);
      }
      if (isShowFullScreen()) {
         //全屏
         getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                 WindowManager.LayoutParams.FLAG_FULLSCREEN);
      }
      //使用今日头条万能适配方式 强制修改手机DPI密度
      //使用前请在Application中调用初始化 Density.initBlueprintDPI(设计图DPI)
      //否则默认480DPI
      Density.setDefault(this);
      setContentView(initLayout());

      //使用Btterknife需要添加
      //annotationProcessor 'com.jakewharton:butterknife-compiler:10.1.0'
      //依赖才有效
      mBind = ButterKnife.bind(this);
      initMvp();

      initView();

      initData();

      initListener();
   }

   /**
    * 是否全屏 默认false不全屏
    */
   protected boolean isShowFullScreen() {
      return false;
   }

   /**
    * 是否显示title 默认false不显示
    */
   protected boolean isShowTitle() {
      return false;
   }


   @Override
   public final void toActivity(Class<?> tClass, Intent intent) {
      startActivity(createIntent(tClass, intent));
   }

   @Override
   public final void toActivityForResult(Class<?> tClass, int resuleCode, Intent intent) {
      startActivityForResult(createIntent(tClass, intent), resuleCode);
   }

   private Intent createIntent(Class<?> tClass, Intent intent) {
      Intent start;
      if (intent != null) {
         intent.setClass(getContext(), tClass);
         start = intent;
      } else {
         start = new Intent(getContext(), tClass);
      }
      return start;
   }

   @Override
   public BaseFragment addFragment(FragmentManager manager, Class<? extends BaseFragment> fClass,
                                   int groupID, Bundle args) {
      //获取一个tag用于存储当前的fragment时的标识
      String tag = fClass.getName();
      //先以这个tag找fragment,如果找到的fragment是空则说明之前没有进行存储回退,如果不是空说明这个fragment存在
      //但不一定是在当前的fragment集合中,可能是被replace给替换并remove到回退栈的集合中了
      Fragment fragment = manager.findFragmentByTag(tag);
      BaseFragment baseFragment = null;
      FragmentTransaction transaction = manager.beginTransaction();
      //判断是否为空
      if (fragment != null) {
         //不为空,则把这个fragment赋值给baseFragment
         baseFragment = (BaseFragment) fragment;
         //判断一下这个fragment是否被添加过,添加过则判断是否被隐藏,如果被隐藏则把这个fragment显示出来
         if (baseFragment.isAdded()) {
            if (baseFragment.isHidden()) {
               transaction.show(baseFragment);
            }
         } else {
            //没有被添加过说明这个fragment被replace方法调用过被添加到了回退栈集合中了
            //判断当前的fragment的回退栈状态值和进出场动画的调用
            //因为这个fragment没有显示所以添加显示
            transaction.add(groupID, baseFragment, tag);
         }
      } else {
         //说明fragment是不存在与回退栈中的
         if (fClass != null) {
            try {
               //以反射的newInstance方法创建一个对象
               baseFragment = fClass.newInstance();
            } catch (Exception e) {
               e.printStackTrace();
            }
            //判断这个创建的对象是否为空,如果为空说明这个fragment没有提供无参构造
            if (baseFragment == null) {
               throw new UnsupportedOperationException(tag + "::Fragment必须提供无参构造方法");
            }
            //判断当前的fragment的回退栈状态值和进出场动画的调用
            isFragmentState(tag, baseFragment, transaction, manager.getFragments().size());
            //不等于null说明创建成功
            if (baseFragment != null)
               //把这个fragment添加显示
               transaction.add(groupID, baseFragment, tag);
         }
      }
      //判断fragment是否为空,不为空则隐藏其他的fragment
      if (baseFragment != null)
         hideAllFragment(manager, transaction, baseFragment);
      //判断Bundle是否存在,如果不为空说明需要传值到fragment中
      if (args != null)
         baseFragment.setArguments(args);
      //提交事件,把这个fragment返回,以便之后有需要调用的地方
      transaction.commit();
      return baseFragment;
   }

   private void isFragmentState(String tag, BaseFragment baseFragment,
                                FragmentTransaction transaction, int fragmrntSize) {
      if (baseFragment.isNeedAnimation())
         transaction.setCustomAnimations(
                 baseFragment.enterAnim(),
                 baseFragment.exitAnim(),
                 baseFragment.popEnterAnim(),
                 baseFragment.popExitAnim());
      if (baseFragment.isNeedAddToBackStack() && fragmrntSize != 0)
         transaction.addToBackStack(tag);
   }

   @Override
   public void hideAllFragment(FragmentManager manager, FragmentTransaction transaction,
                               Fragment curFragment) {
      List<Fragment> fragments = manager.getFragments();
      for (Fragment fragment : fragments)
         if (curFragment != fragment && !fragment.isHidden())
            transaction.hide(fragment);
   }

   /**
    * 显示loding页面
    */
   protected void showLoading() {
      showLoading(LoadingView.BG_MODE_TRANSPARENT);
   }

   /**
    * 显示loding页面
    */
   protected void showLoading(@LoadingView.LoadingMode int mode) {
      showLoading(mode, android.R.id.content);
   }

   /**
    * showLoading的重载方法添加了布局ID 必须传入一个容器的ID,显示loding到这个容器上
    *
    * @param mode        样式类型
    * @param containerId 布局ID
    */
   protected void showLoading(@LoadingView.LoadingMode int mode, @IdRes int containerId) {
      showLoading(mode, (ViewGroup) findViewById(containerId));
   }

   /**
    * @param mode  样式类型
    * @param group 布局对象
    */
   protected void showLoading(@LoadingView.LoadingMode int mode, ViewGroup group) {
      mLoadingView = LoadingView.injectViewGroup(group);
      if (mLoadingView != null)
         mLoadingView.showLoading(mode);
   }

   protected void hideLoading() {
      if (mLoadingView != null)
         mLoadingView.loadingClose();

   }

   protected void showHint(String msg) {
      Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
   }

   protected void showHint(int resId) {
      Toast.makeText(this, resId, Toast.LENGTH_SHORT).show();
   }

   protected void onError(String msg, LoadingView.RetryCallBack callBack) {
      if (mLoadingView != null)
         mLoadingView.onError(msg, callBack);
   }

   @Override
   public Context getContext() {
      return this;
   }

   protected void initMvp() {
   }

   protected void initView() {
   }

   protected void initData() {
   }

   protected void initListener() {
   }

   @Override
   protected void onDestroy() {
      super.onDestroy();
      if (mBind != null) {
         mBind.unbind();
      }
   }
}
