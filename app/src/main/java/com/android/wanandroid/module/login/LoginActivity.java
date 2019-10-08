package com.android.wanandroid.module.login;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import androidx.core.app.ActivityOptionsCompat;

import com.android.loadingview.LoadingView;
import com.android.mymvp.base.BaseActivity;
import com.android.mymvp.base.BaseMvpActivity;
import com.android.utils.file.SPUtil;
import com.android.wanandroid.Constants;
import com.android.wanandroid.Contract;
import com.android.wanandroid.R;

/**
 * @author Mr.Li：lkx
 * @description: I brandished my keyboard and book, vowing to write the world clearly.
 * @date : 2019/10/7 19:53
 */
public class LoginActivity extends BaseMvpActivity<Contract.ILoginPresenter> implements View.OnClickListener, Contract.ILoginView {

    private ImageView mIvBack;
    private EditText mEtUser;
    private EditText mEtPas;
    private Button mBtnLogin;
    private Button mBtnRegister;
    private String mUserName;
    private String mPassWord;

    @Override
    public int initLayout() {
        return R.layout.activity_login;
    }

    @Override
    protected void initView() {
        super.initView();
        mIvBack = findViewById(R.id.login_iv_back);
        mEtUser = findViewById(R.id.login_et_user);
        mEtPas = findViewById(R.id.login_et_pas);
        mBtnLogin = findViewById(R.id.login_btn_login_in);
        mBtnRegister = findViewById(R.id.login_btn_register);

    }

    @Override
    protected void initData() {
        super.initData();


    }

    @Override
    protected void initListener() {
        super.initListener();
        mIvBack.setOnClickListener(this);
        mBtnLogin.setOnClickListener(this);
        mBtnRegister.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.login_iv_back:
                finish();
                break;
            case R.id.login_btn_login_in:
                initLogin();
                break;
        }
    }

    private void initLogin() {
        mUserName = mEtUser.getText().toString().trim();
        mPassWord = mEtPas.getText().toString().trim();
        mPresenter.login(mUserName, mPassWord);
        showLoading();
    }

    public static void actionStart(Context context){
        //设置Activity 进场动画
        ActivityOptionsCompat compat = ActivityOptionsCompat.makeCustomAnimation(context,
                R.anim.activity_open, R.anim.activity_close);
        Intent intent = new Intent(context, LoginActivity.class);
        ActivityCompat.startActivity(context, intent, compat.toBundle());
    }

    @Override
    public void finish() {
        ActivityCompat.finishAfterTransition(this);
        super.finish();
    }


    @Override
    public void onSuccess(LoginData data) {
        if(data.type == 0){
            Toast.makeText(this, "登录成功", Toast.LENGTH_SHORT).show();
            SPUtil.putDefDataByApply(Constants.USER_INFO,data.toString());
            hideLoading();
            finish();
        }
    }

    @Override
    public void onFail(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
        onError(msg, new LoadingView.RetryCallBack() {
            @Override
            public void onRetry() {
                initData();
            }
        });
    }

    @Override
    public Contract.ILoginPresenter createPresenter() {
        return new LoginPresenter();
    }
}
