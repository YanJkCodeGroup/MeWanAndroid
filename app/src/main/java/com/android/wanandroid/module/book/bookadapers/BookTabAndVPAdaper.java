package com.android.wanandroid.module.book.bookadapers;

import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.ArrayList;

public class BookTabAndVPAdaper extends FragmentPagerAdapter {
    ArrayList<Fragment> fl;
    String[] title = {"体系", "导航"};

    public BookTabAndVPAdaper(@NonNull FragmentManager fm, ArrayList<Fragment> fl) {
        super(fm);
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
        return title[position];
    }

    @Nullable
    @Override
    public Parcelable saveState() {
        return null;
    }
}
