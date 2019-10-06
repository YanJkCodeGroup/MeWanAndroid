package com.android.wanandroid.module.wechat;


import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.android.mymvp.base.BaseMvpFragment;
import com.android.wanandroid.Contract;
import com.android.wanandroid.R;
import com.android.wanandroid.module.wechat.wechatadapter.WechatListAdapter;
import com.android.wanandroid.module.wechat.wehcatpresetner.WechatPresenter;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class WechatFragment extends BaseMvpFragment<Contract.wechatPresenter> implements Contract.wechatView {


    @BindView(R.id.wechat_tabLayout)
    TabLayout wechatTabLayout;
    @BindView(R.id.wechat_vp)
    ViewPager wechatVp;
    private ArrayList<Fragment> list;

    public WechatFragment() {
        // Required empty public constructor
    }

    @Override
    public int initLayout() {
        return R.layout.fragment_wechat;
    }

    @Override
    public void succeed(List<DataBean> wechatList) {
        list = new ArrayList<>();
        for (int i = 0; i < wechatList.size(); i++) {

            wechatTabLayout.addTab(wechatTabLayout.newTab().setText(wechatList.get(i).getName()));

            Bundle bundle  = new Bundle();

            WechatListFragment wechatFragment = new WechatListFragment();

            bundle.putString("id", String.valueOf(wechatList.get(i).getId()));

            wechatFragment.setArguments(bundle);

            list.add(wechatFragment);


        }
        WechatListAdapter wechatListAdapter = new WechatListAdapter(getChildFragmentManager(), list);
        wechatVp.setAdapter(wechatListAdapter);
        wechatTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                wechatVp.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        wechatVp.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(wechatTabLayout));
    }

    @Override
    public void fail(String error) {
        Log.d("booknav", "fail: " + error);
        Toast.makeText(mContext, "错误为:" + error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public Contract.wechatPresenter createPresenter() {
        return new WechatPresenter();
    }

    @Override
    protected void initData() {
        super.initData();
        mPresenter.initwechatPresenter();
    }
}
