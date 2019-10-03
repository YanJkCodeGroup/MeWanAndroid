package com.android.wanandroid.module.project;


import androidx.fragment.app.Fragment;

import com.android.mymvp.base.BaseFragment;
import com.android.wanandroid.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProjectFragment extends BaseFragment {


   public ProjectFragment() {
      // Required empty public constructor
   }

   @Override
   public int initLayout() {
      return R.layout.fragment_project;
   }
}
