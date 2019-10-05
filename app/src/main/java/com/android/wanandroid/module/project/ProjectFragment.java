package com.android.wanandroid.module.project;


import androidx.fragment.app.Fragment;

import com.android.mymvp.base.BaseFragment;
import com.android.mymvp.base.util.AppUtils;
import com.android.wanandroid.R;
import com.google.android.material.tabs.TabLayout;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProjectFragment extends BaseFragment {


   @BindView(R.id.project_tab)
   TabLayout projectTab;

   public ProjectFragment() {
      // Required empty public constructor
   }

   @Override
   public int initLayout() {
      return R.layout.fragment_project;
   }

   @Override
   protected void initView() {
      projectTab.setPadding(projectTab.getPaddingLeft(),
              AppUtils.getStateBar2(getContext()),
              projectTab.getPaddingRight(),
              projectTab.getPaddingBottom());
   }
}
