package com.android.wanandroid.module.home;


import androidx.fragment.app.Fragment;

import com.android.mymvp.base.BaseFragment;
import com.android.wanandroid.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends BaseFragment {


   public HomeFragment() {
      // Required empty public constructor
   }

   @Override
   public int initLayout() {
      return R.layout.fragment_home;
   }
}
