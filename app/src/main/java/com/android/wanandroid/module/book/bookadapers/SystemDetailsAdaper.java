package com.android.wanandroid.module.book.bookadapers;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;

public class SystemDetailsAdaper extends FragmentPagerAdapter {
    ArrayList<String> titleList;
    ArrayList<Fragment> fl;

    public SystemDetailsAdaper(@NonNull FragmentManager fm, ArrayList<String> titleList, ArrayList<Fragment> fl) {
        super(fm);
        this.titleList = titleList;
        this.fl = fl;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fl.get(position);
    }

    @Override
    public int getCount() {
        return fl.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return titleList.get(position);
    }
}
