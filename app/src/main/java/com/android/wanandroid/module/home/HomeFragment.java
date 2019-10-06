package com.android.wanandroid.module.home;


import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.android.mymvp.base.BaseMvpFragment;
import com.android.mymvp.base.util.AppUtils;
import com.android.wanandroid.Contract;
import com.android.wanandroid.R;

import java.util.List;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends BaseMvpFragment<Contract.homePresenter> implements Contract.homeView {


    @BindView(R.id.home_toolbar)
    Toolbar homeToolbar;
    @BindView(R.id.home_rv)
    RecyclerView homeRv;

    public HomeFragment() {
    }

    @Override
    public int initLayout() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initView() {
        homeToolbar.setPadding(homeToolbar.getPaddingLeft(),
                AppUtils.getStateBar2(getContext()),
                homeToolbar.getPaddingRight(),
                homeToolbar.getPaddingBottom());

    }

    @Override
    protected void initData() {
        super.initData();
        mPresenter.initHomePresenter();
    }



    @Override
    public Contract.homePresenter createPresenter() {
        return new HomePresenter();
    }

    @Override
    public void homeBeanSucceed(List<HomeBean> homeList) {

    }

    @Override
    public void homeFail(String error) {

    }

    @Override
    public void homeBannerSucceed(List<HomeBannerBean> homeBannerList) {

    }

    @Override
    public void homeBannerFail(String error) {

    }
}
