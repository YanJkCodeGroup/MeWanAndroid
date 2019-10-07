package com.android.wanandroid.module.project;


import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.android.mymvp.base.BaseMvpFragment;
import com.android.mymvp.base.util.AppUtils;
import com.android.wanandroid.Contract.ProjectContract.ProjectPresenter;
import com.android.wanandroid.Contract.ProjectContract.ProjectView;
import com.android.wanandroid.R;
import com.android.wanandroid.module.project.adapter.ProjectVpAdapter;
import com.android.wanandroid.module.project.entity.ProjectList;
import com.google.android.material.tabs.TabLayout;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProjectFragment extends BaseMvpFragment<ProjectPresenter> implements ProjectView {

   @BindView(R.id.project_tab)
   TabLayout projectTab;
   @BindView(R.id.project_vp)
   ViewPager projectVp;

   public ProjectFragment() {
   }

   @Override
   public int initLayout() {
      return R.layout.fragment_project;
   }

   @Override
   protected void initData() {
      mPresenter.getProjectTabList();
   }

   @Override
   protected void initView() {
      projectTab.setPadding(projectTab.getPaddingLeft(),
              AppUtils.getStateBar2(getContext()),
              projectTab.getPaddingRight(),
              projectTab.getPaddingBottom());
   }

   @Override
   public ProjectPresenter createPresenter() {
      return new com.android.wanandroid.module.project.presenter.ProjectPresenter();
   }

   @Override
   public void onSuccessful(List<ProjectList> projectLists) {
      projectVp.setAdapter(new ProjectVpAdapter(getChildFragmentManager(), projectLists));
      projectTab.setupWithViewPager(projectVp);
      for (int i = 0 ; i < projectLists.size() ; i++) {
         projectTab.getTabAt(i).setText(projectLists.get(i).getName());
      }
   }

   @Override
   public void onFailed(String msg) {

   }

   @Override
   public void setUserVisibleHint(boolean isVisibleToUser) {
      super.setUserVisibleHint(isVisibleToUser);
      if (rootView == null) {
         return;
      }

      if (isVisibleToUser) {
         unbinder = ButterKnife.bind(this, rootView);
         initData();
      }
   }
}
