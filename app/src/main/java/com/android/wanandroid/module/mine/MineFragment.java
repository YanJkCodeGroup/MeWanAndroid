package com.android.wanandroid.module.mine;


import android.view.View;

import androidx.fragment.app.Fragment;

import com.android.mymvp.base.BaseFragment;
import com.android.mymvp.base.util.AppUtils;
import com.android.wanandroid.R;
import com.google.android.material.navigation.NavigationView;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class MineFragment extends BaseFragment {


   @BindView(R.id.mine_navigation)
   NavigationView mineNavigation;

   public MineFragment() {
      // Required empty public constructor
   }

   @Override
   public int initLayout() {
      return R.layout.fragment_mine;
   }

   @Override
   protected void initView() {
      View headerView = mineNavigation.getHeaderView(0);
      headerView.setPadding(headerView.getPaddingLeft(),
              AppUtils.getStateBar2(getContext()),
              headerView.getPaddingRight(),
              headerView.getPaddingBottom());
   }
}
