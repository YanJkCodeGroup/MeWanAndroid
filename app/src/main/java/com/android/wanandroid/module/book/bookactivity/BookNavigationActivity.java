package com.android.wanandroid.module.book.bookactivity;

import android.content.Intent;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.android.mymvp.base.BaseActivity;
import com.android.mymvp.base.util.AppUtils;
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
   protected void initView() {
      booknavWeb.setPadding(booknavWeb.getPaddingLeft(),
              AppUtils.getStateBar2(getContext()),
              booknavWeb.getPaddingRight(),
              booknavWeb.getPaddingBottom());
   }

   @Override
   protected void initData() {
      super.initData();
      //获取传过来的link 网址
      Intent intent = getIntent();
      String link = intent.getStringExtra("link");


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

      //设置在webview 上
      booknavWeb.setWebViewClient(new WebViewClient());
   }

   @Override
   protected void onDestroy() {
      booknavWeb.destroy();
      super.onDestroy();
   }


   @Override
   public void onBackPressed() {
      if (booknavWeb.canGoBack()) {
         booknavWeb.goBack();
      } else {
         super.onBackPressed();
      }
   }
}
