package com.android.wanandroid.module.home;


import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.android.mymvp.base.BaseFragment;
import com.android.wanandroid.R;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends BaseFragment {


   @BindView(R.id.home_toolbar)
   Toolbar homeToolbar;
   @BindView(R.id.home_rv)
   RecyclerView homeRv;

   public HomeFragment() {
   }

   @Override
   public int initLayout() {
      return R.layout.fragment_home;
   }

}
