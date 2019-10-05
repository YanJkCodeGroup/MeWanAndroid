package com.android.wanandroid.module.book;


import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
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
import butterknife.ButterKnife;

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
        FragmentManager cfm =getActivity().getSupportFragmentManager();
        ArrayList<Fragment> fl = new ArrayList<>();
        BookSystemFragment bookSystemFragment = new BookSystemFragment();
        BookNavigationFragment bookNavigationFragment = new BookNavigationFragment();
        fl.add(bookSystemFragment);
        fl.add(bookNavigationFragment);
        BookTabAndVPAdaper bookTabAndVPAdaper = new BookTabAndVPAdaper(cfm, fl);
        fragmentBookVp.setAdapter(bookTabAndVPAdaper);
        fragmentBookTab.setupWithViewPager(fragmentBookVp);
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
