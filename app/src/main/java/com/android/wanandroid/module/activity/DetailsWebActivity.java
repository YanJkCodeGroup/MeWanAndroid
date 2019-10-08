package com.android.wanandroid.module.activity;

import android.content.Intent;
import android.os.Build;
import android.text.TextUtils;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;

import com.android.mymvp.base.BaseActivity;
import com.android.mymvp.base.util.AppUtils;
import com.android.utils.system.ImmersionModeUtil;
import com.android.wanandroid.R;

import butterknife.BindView;
import butterknife.OnClick;

public class DetailsWebActivity extends BaseActivity {


   @BindView(R.id.booknav_web)
   WebView booknavWeb;
   @BindView(R.id.toolbar)
   Toolbar toolbar;
   @BindView(R.id.progressBar)
   ProgressBar progressBar;
   @BindView(R.id.tv_title)
   TextView tvTitle;
   @BindView(R.id.top_bg)
   ImageView topBg;

   @Override
   public int initLayout() {
      return R.layout.activity_details_web;
   }

   @Override
   protected void initView() {
      ImmersionModeUtil.setStatusBar(this, false);
      topBg.setPadding(topBg.getPaddingLeft(),
              AppUtils.getStateBar2(getContext()),
              topBg.getPaddingRight(),
              topBg.getPaddingBottom());
      toolbar.setTitle("");
      setSupportActionBar(toolbar);
   }

   @Override
   protected void initData() {
      //获取传过来的link 网址
      Intent intent = getIntent();
      String link = intent.getStringExtra("link");
      String title = intent.getStringExtra("title");
      if (title != null && !TextUtils.isEmpty(title)) {
         tvTitle.setText(title);
      }
      booknavWeb.setWebChromeClient(new WebChromeClient() {
         @Override
         public void onProgressChanged(WebView view, int newProgress) {
            if (newProgress % 10 == 0) {
               progressBar.setVisibility(View.VISIBLE);
            }
            progressBar.setProgress(newProgress);
            if (newProgress == 100) {
               progressBar.setVisibility(View.GONE);
            }
         }
      });
      //设置在webview 上
      booknavWeb.setWebViewClient(new WebViewClient() {
         @Override
         public boolean shouldOverrideUrlLoading(WebView view, String url) {
            if (url != null) {
               view.loadUrl(url);
               return true;
            }
            return super.shouldOverrideUrlLoading(view, url);
         }

         @Override
         public void onPageFinished(WebView view, String url) {
            view.getSettings().setJavaScriptEnabled(true);
            super.onPageFinished(view, url);
         }
      });
      WebSettings settings = booknavWeb.getSettings();
      booknavWeb.setOverScrollMode(WebView.OVER_SCROLL_NEVER);
      settings.setJavaScriptEnabled(true);
      settings.setLoadsImagesAutomatically(true);
      settings.setUseWideViewPort(true);
      settings.setLoadWithOverviewMode(true);
      settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
      if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
         CookieManager.getInstance().setAcceptThirdPartyCookies(booknavWeb, true);
      }
      booknavWeb.loadUrl(link);

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

   @OnClick({R.id.btn_back, R.id.btn_close})
   public void onClick(View view) {
      switch (view.getId()) {
         case R.id.btn_back:
            if (booknavWeb.canGoBack()) {
               booknavWeb.goBack();
            } else {
               finish();
            }
            break;
         case R.id.btn_close:
            finish();
            break;
      }
   }
}
