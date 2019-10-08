package com.android.mymvp.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentManager;

import com.android.loadingview.LoadingView;
import com.android.mymvp.R;
import com.android.mymvp.base.Interface.IBaseFragmentDecorate;
import com.android.mymvp.base.Interface.IBaseLayout;
import com.android.mymvp.base.Interface.IbaseToActivity;
import com.trello.rxlifecycle2.components.support.RxFragment;

import butterknife.ButterKnife;
import butterknife.Unbinder;


public abstract class BaseFragment extends RxFragment implements IBaseLayout, IbaseToActivity,
        IBaseFragmentDecorate {

   protected Unbinder unbinder;
   protected Context mContext;
   protected Activity mActivity;
   protected View rootView;
   private LoadingView mLoadingView;

   public BaseFragment() {
   }

   @Nullable
   @Override
   public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                            @Nullable Bundle savedInstanceState) {
      rootView = inflater.inflate(initLayout(), container, false);
      unbinder = ButterKnife.bind(this, rootView);
      return rootView;
   }

   @Override
   public void onAttach(Context context) {
      super.onAttach(context);
      mContext = context;
      mActivity = getActivity();
   }

   @Override
   public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
      super.onViewCreated(view, savedInstanceState);
      initMvp();

      initView();

      initData();

      initListener();
   }

   public void showHint(String msg) {
      Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
   }

   public void showHint(int resId) {
      Toast.makeText(mContext, resId, Toast.LENGTH_SHORT).show();
   }

   public void finish() {
      mActivity.finish();
   }

   public BaseFragment addFragment(FragmentManager manager, Class<? extends BaseFragment> fClass,
                                   int groupID, Bundle args) {
      if (mActivity instanceof BaseActivity) {
         return ((BaseActivity) mActivity).addFragment(manager, fClass, groupID, args);
      }
      return null;
   }

   protected void back() {
      getFragmentManager().popBackStack();
   }

   @Override
   public final void toActivity(Class<?> toClass, Intent intent) {
      startActivity(createIntent(toClass, intent));
   }

   @Override
   public final void toActivityForResult(Class<?> toClass, int resuleCode, Intent intent) {
      startActivityForResult(createIntent(toClass, intent), resuleCode);
   }

   private Intent createIntent(Class<?> toClass, Intent intent) {
      Intent start;
      if (intent != null) {
         intent.setClass(mContext, toClass);
         start = intent;
      } else {
         start = new Intent(mContext, toClass);
      }
      return start;
   }

   @Override
   public int enterAnim() {
      if (!isNeedAnimation()) {
         return 0;
      }
      return R.anim.common_page_right_in;
   }

   @Override
   public int exitAnim() {
      if (!isNeedAnimation()) {
         return 0;
      }
      return R.anim.common_page_left_out;
   }

   @Override
   public int popEnterAnim() {
      if (!isNeedAnimation()) {
         return 0;
      }
      return R.anim.common_page_left_in;
   }

   @Override
   public int popExitAnim() {
      if (!isNeedAnimation()) {
         return 0;
      }
      return R.anim.common_page_right_out;
   }

   @Override
   public boolean isNeedAddToBackStack() {
      return true;
   }

   @Override
   public boolean isNeedAnimation() {
      return true;
   }

   protected void initMvp() {
   }

   protected void initView() {
   }

   protected void initData() {
   }

   protected void initListener() {
   }

   protected void showLoading() {
      showLoading(LoadingView.BG_MODE_TRANSPARENT);
   }

   protected void showLoading(@LoadingView.LoadingMode int mode) {
      showLoading(mode, (ViewGroup) getView().getParent());
   }

   protected void showLoading(@LoadingView.LoadingMode int mode, ViewGroup group) {
      mLoadingView=LoadingView.injectViewGroup(group);
      if (mLoadingView != null)
         mLoadingView.showLoading(mode);
   }

   protected void hideLoading() {
      if (mLoadingView != null)
         mLoadingView.loadingClose();
   }

   protected void LoadingError(String msg, LoadingView.RetryCallBack callBack) {
      if (mLoadingView != null)
         mLoadingView.onError(msg, callBack);

   }

   @Override
   public void onDestroyView() {
      super.onDestroyView();
      unbinder.unbind();
   }
}
