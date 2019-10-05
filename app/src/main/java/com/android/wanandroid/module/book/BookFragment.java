package com.android.wanandroid.module.book;


import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;

import com.android.mymvp.base.BaseFragment;
import com.android.mymvp.base.util.AppUtils;
import com.android.wanandroid.R;
import com.android.wanandroid.module.book.bookadapers.BookTabAndVPAdaper;
import com.android.wanandroid.module.book.booktabfragments.BookNavigationFragment;
import com.android.wanandroid.module.book.booktabfragments.BookSystemFragment;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class BookFragment extends BaseFragment {


   @BindView(R.id.fragment_book_tab)
   TabLayout fragmentBookTab;
   @BindView(R.id.fragment_book_vp)
   ViewPager fragmentBookVp;

   public BookFragment() {
      // Required empty public constructor
   }

   @Override
   public int initLayout() {
      return R.layout.fragment_book;
   }

   @Override
   protected void initView() {
      fragmentBookTab.setPadding(fragmentBookTab.getPaddingLeft(),
              AppUtils.getStateBar2(getContext()),
              fragmentBookTab.getPaddingRight(),
              fragmentBookTab.getPaddingBottom());
   }

   @Override
   protected void initData() {
      super.initData();
      FragmentManager fm = getFragmentManager();
      ArrayList<Fragment> fl = new ArrayList<>();
      BookSystemFragment bookSystemFragment = new BookSystemFragment();
      BookNavigationFragment bookNavigationFragment = new BookNavigationFragment();
      fl.add(bookSystemFragment);
      fl.add(bookNavigationFragment);
      BookTabAndVPAdaper bookTabAndVPAdaper = new BookTabAndVPAdaper(fm, fl);
      fragmentBookVp.setAdapter(bookTabAndVPAdaper);
      fragmentBookTab.setupWithViewPager(fragmentBookVp);

   }
}
