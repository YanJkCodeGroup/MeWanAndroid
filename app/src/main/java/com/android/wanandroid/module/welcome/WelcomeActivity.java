package com.android.wanandroid.module.welcome;

import android.content.Intent;
import android.os.CountDownTimer;

import com.android.mymvp.base.BaseActivity;
import com.android.utils.system.ImmersionModeUtil;
import com.android.wanandroid.R;
import com.android.wanandroid.module.activity.HomeActivity;

public class WelcomeActivity extends BaseActivity {

    @Override
    public int initLayout() {
        return R.layout.activity_welcome;
    }

    @Override
    protected void initView() {
        ImmersionModeUtil.setStatusBar(this, false);
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
