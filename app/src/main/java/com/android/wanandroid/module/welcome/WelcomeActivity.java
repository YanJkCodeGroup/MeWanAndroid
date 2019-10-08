package com.android.wanandroid.module.welcome;

import android.content.Intent;
import android.os.CountDownTimer;

import com.android.mymvp.base.BaseActivity;
import com.android.wanandroid.R;
import com.android.wanandroid.module.activity.HomeActivity;
import com.android.wanandroid.test.MainActivity;

public class WelcomeActivity extends BaseActivity {

    @Override
    public int initLayout() {
        return R.layout.activity_welcome;
    }

    @Override
    protected void initView() {
        super.initView();
        CountDownTimer countDownTimer = new CountDownTimer(3000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
            }

            @Override
            public void onFinish() {
                startActivity(new Intent(WelcomeActivity.this, HomeActivity.class));
                finish();
            }
        };
        countDownTimer.start();
    }
}
