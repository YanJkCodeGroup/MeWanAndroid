package com.android.wanandroid.module.book.bookactivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;

import com.android.mymvp.base.BaseActivity;
import com.android.wanandroid.R;
import com.android.wanandroid.module.book.bookadapers.SystemDetailsAdaper;
import com.android.wanandroid.module.book.bookbeans.BookSystemBean;
import com.android.wanandroid.module.book.booktabfragments.SystemDetailsFragment;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

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
    private ArrayList<BookSystemBean> list;
    private List<BookSystemBean.ChildrenBean> childrenList;


    @Override
    protected void initView() {
        super.initView();
        Intent intent = getIntent();
        list = intent.getParcelableArrayListExtra("List");
        String name = intent.getStringExtra("name");
        toolbarTitle.setText(name);
        activityBooksysToolbar.setTitle("");
        setSupportActionBar(activityBooksysToolbar);

        int size = intent.getIntExtra("size", 0);
        int id = intent.getIntExtra("id", 0);
        FragmentManager fm = getSupportFragmentManager();
        ArrayList<Fragment> fl = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            SystemDetailsFragment systemDetailsFragment = new SystemDetailsFragment();
            fl.add(systemDetailsFragment);
        }
        for (int i = 0; i < list.size(); i++) {
            int uid = list.get(i).getId();
            if (uid == id) {
                childrenList = list.get(i).getChildren();
                break;
            }
        }
        ArrayList<String> titleList = new ArrayList<>();
        for (int i = 0; i < childrenList.size(); i++) {
            String titleName = childrenList.get(i).getName();
            titleList.add(titleName);
        }
        SystemDetailsAdaper systemDetailsAdaper = new SystemDetailsAdaper(fm, titleList, fl);
        activityBooksysVp.setAdapter(systemDetailsAdaper);
        activityBooksysTab.setupWithViewPager(activityBooksysVp);

    }

    @Override
    public int initLayout() {
        return R.layout.activity_book_system;
    }


}
