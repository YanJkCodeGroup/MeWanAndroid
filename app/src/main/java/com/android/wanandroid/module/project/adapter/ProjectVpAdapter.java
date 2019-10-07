package com.android.wanandroid.module.project.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.android.wanandroid.module.project.entity.ProjectList;
import com.android.wanandroid.module.project.fragment.ProjectItemFragment;

import java.util.List;

public class ProjectVpAdapter extends FragmentStatePagerAdapter {
   private List<ProjectList> mFragments;

   public ProjectVpAdapter(@NonNull FragmentManager fm, List<ProjectList> fragments) {
      super(fm, FragmentStatePagerAdapter.BEHAVIOR_SET_USER_VISIBLE_HINT);
      mFragments = fragments;
   }

   @NonNull
   @Override
   public Fragment getItem(int position) {
      return new ProjectItemFragment(mFragments.get(position).getId());
   }

   @Override
   public int getCount() {
      return mFragments.size();
   }
}