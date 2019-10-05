package com.android.wanandroid.module.adapter;

import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.ArrayList;

public class HomeViewPagerAdapter extends FragmentPagerAdapter {
   private ArrayList<Fragment> fragments;

   public HomeViewPagerAdapter(@NonNull FragmentManager fm, ArrayList<Fragment> fragments) {
      super(fm);
      this.fragments = fragments;
   }

   @NonNull
   @Override
   public Fragment getItem(int position) {
      return fragments.get(position);
   }

   @Override
   public int getCount() {
      return fragments.size();
   }

   @Nullable
   @Override
   public Parcelable saveState() {
      return null;
   }
}
