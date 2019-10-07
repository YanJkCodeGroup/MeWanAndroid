package com.android.wanandroid.module.book.bookactivity;
import android.content.Intent;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;

import com.android.mymvp.base.BaseActivity;
import com.android.wanandroid.R;
import com.android.wanandroid.module.book.bookadapers.SystemTabAndVpAdaper;
import com.android.wanandroid.module.book.bookbeans.BookSystemBean;
import com.android.wanandroid.module.book.booktabfragments.SystemDetailsFragment;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

import butterknife.BindView;

public class BookSystemActivity extends BaseActivity {
    private static final String TAG = "BookSystemActivity";
    @BindView(R.id.activity_booksys_toolbar)
    Toolbar activityBooksysToolbar;
    @BindView(R.id.activity_booksys_tab)
    TabLayout activityBooksysTab;
    @BindView(R.id.activity_booksys_vp)
    ViewPager activityBooksysVp;
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    private ArrayList<BookSystemBean.ChildrenBean> list;


    @Override
    protected void initView() {
        super.initView();
        Intent intent = getIntent();
        list = intent.getParcelableArrayListExtra("List");
        String name = intent.getStringExtra("name");
        toolbarTitle.setText(name);
        activityBooksysToolbar.setTitle("");
        setSupportActionBar(activityBooksysToolbar);

        FragmentManager fm = getSupportFragmentManager();
        ArrayList<Fragment> fl = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            int childernId = list.get(i).getId();
            SystemDetailsFragment systemDetailsFragment = new SystemDetailsFragment(childernId);
            fl.add(systemDetailsFragment);
        }

        ArrayList<String> titleList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            String titleName = list.get(i).getName();
            titleList.add(titleName);
        }
        SystemTabAndVpAdaper systemTabAndVpAdaper = new SystemTabAndVpAdaper(fm, titleList, fl);
        activityBooksysVp.setAdapter(systemTabAndVpAdaper);
        activityBooksysTab.setupWithViewPager(activityBooksysVp);
    }

    @Override
    public int initLayout() {
        return R.layout.activity_book_system;
    }


}