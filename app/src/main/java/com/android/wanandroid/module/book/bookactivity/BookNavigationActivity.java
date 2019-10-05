package com.android.wanandroid.module.book.bookactivity;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.android.mymvp.base.BaseActivity;
import com.android.wanandroid.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BookNavigationActivity extends BaseActivity {


    @BindView(R.id.booknav_web)
    WebView booknavWeb;

    @Override
    public int initLayout() {
        return R.layout.activity_book_navigation;
    }


    @Override
    protected void initData() {
        super.initData();
        //获取传过来的link 网址
        Intent intent = getIntent();
        String link = intent.getStringExtra("link");
        //设置在webview 上
        booknavWeb.setWebViewClient(new WebViewClient());
        booknavWeb.loadUrl(link);
    }
}
