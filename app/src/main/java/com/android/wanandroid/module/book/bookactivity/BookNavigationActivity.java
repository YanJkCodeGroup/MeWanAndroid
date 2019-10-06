package com.android.wanandroid.module.book.bookactivity;

import android.content.Intent;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.android.mymvp.base.BaseActivity;
import com.android.wanandroid.R;

import butterknife.BindView;

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
        WebSettings settings = booknavWeb.getSettings();
        settings.setJavaScriptEnabled(true);//js支持
        settings.setJavaScriptCanOpenWindowsAutomatically(true);//js支持
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
        settings.setAllowFileAccess(true);
        settings.setUseWideViewPort(true);
        settings.setDomStorageEnabled(true);
        settings.setDisplayZoomControls(false);
        settings.setBuiltInZoomControls(true);
        settings.setSupportMultipleWindows(true);
        settings.setAppCacheEnabled(true);
        settings.setDomStorageEnabled(true);
        settings.setAppCacheMaxSize(Long.MAX_VALUE);
        settings.setPluginState(WebSettings.PluginState.ON_DEMAND);
        settings.setCacheMode(WebSettings.LOAD_DEFAULT);
        settings.setLoadWithOverviewMode(true);
        settings.setGeolocationEnabled(true);
        settings.setUserAgentString(settings.getUserAgentString());
        settings.setAppCacheEnabled(true);
        settings.setDatabaseEnabled(true);
        settings.setMediaPlaybackRequiresUserGesture(false);

        booknavWeb.loadUrl(link);
    }
}
