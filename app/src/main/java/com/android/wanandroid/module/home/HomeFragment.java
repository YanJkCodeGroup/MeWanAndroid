package com.android.wanandroid.module.home;


import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.mymvp.base.BaseMvpFragment;
import com.android.mymvp.base.util.AppUtils;
import com.android.wanandroid.Contract;
import com.android.wanandroid.R;
import com.android.wanandroid.module.activity.DetailsWebActivity;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends BaseMvpFragment<Contract.HomePresenter> implements Contract.HomeView {


   private static final String TAG = "HomeFragment";
   @BindView(R.id.home_toolbar)
   Toolbar homeToolbar;
   @BindView(R.id.home_rv)
   RecyclerView homeRv;
   @BindView(R.id.home_smart)
   SmartRefreshLayout home_smart;
   private List<HomeBean.DatasBean> homeBeanDatas;
   private List<HomeBannerBean> homeBannerList;
   private List<HomeTopBean> homeTopBeanList;
   private int page;
   private HomeAdapter homeAdapter;

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
      homeAdapter = new HomeAdapter();
      homeRv.setLayoutManager(new LinearLayoutManager(mContext));
      homeRv.setAdapter(homeAdapter);
      DividerItemDecoration divider = new DividerItemDecoration(mContext,
              DividerItemDecoration.VERTICAL);
      divider.setDrawable(ContextCompat.getDrawable(mContext, R.drawable.project_shape));
      homeRv.addItemDecoration(divider);
   }

   @Override
   protected void initData() {
      super.initData();
      if (mPresenter != null) {
         mPresenter.initHomePresenter(page);
         mPresenter.initHomeBannerPresenter();
         mPresenter.initHomeTopPresenter();
      }
   }


   @Override
   public Contract.HomePresenter createPresenter() {
      return new HomePresenter();
   }

   @Override
   protected void initListener() {
      super.initListener();
      home_smart.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
         @Override
         public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
            page++;
            mPresenter.initHomePresenter(page);
         }

         @Override
         public void onRefresh(@NonNull RefreshLayout refreshLayout) {
            page = 0;
            initData();
         }
      });
      homeAdapter.setRvItemClick(new HomeAdapter.RvItemClick() {

         @Override
         public void OnClick(String link, String title) {
            Intent intent = new Intent(mContext, DetailsWebActivity.class);
            intent.putExtra("link", link);
            intent.putExtra("title", title);
            startActivity(intent);
         }
      });
   }


   @Override
   public void homeBeanSucceed(HomeBean homeBean) {
      this.homeBeanDatas = homeBean.getDatas();
      if (homeBannerList != null && homeBannerList.size() > 0) {
         initReclcerData();
      }
   }

   @Override
   public void homeFail(String error) {
      Log.d(TAG, "fail: " + error);
      Toast.makeText(mContext, "错误为:" + error, Toast.LENGTH_SHORT).show();
   }

   @Override
   public void homeBannerSucceed(List<HomeBannerBean> homeBannerList) {
      this.homeBannerList = homeBannerList;
      if (homeBeanDatas != null && homeBeanDatas.size() > 0) {
         initReclcerData();
      }
   }

   @Override
   public void homeBannerFail(String error) {
      Log.d(TAG, "fail: " + error);
      Toast.makeText(mContext, "错误为:" + error, Toast.LENGTH_SHORT).show();
   }

   @Override
   public void HomeTopSucceed(List<HomeTopBean> homeTopList) {
      this.homeTopBeanList = homeTopList;
      if (homeTopBeanList.size() > 0 && homeTopBeanList != null) {
         initReclcerData();
      }
   }

   @Override
   public void homeTopFail(String error) {
      Log.d(TAG, "fail: " + error);
      Toast.makeText(mContext, "错误为:" + error, Toast.LENGTH_SHORT).show();
   }

   private void initReclcerData() {
      if (page == 0) {
         homeAdapter.initData(homeBeanDatas, homeBannerList, homeTopBeanList);
      } else {
         homeAdapter.addData(homeBeanDatas);
      }
      home_smart.finishLoadMore();
      home_smart.finishRefresh();
   }

   @Override
   public void setUserVisibleHint(boolean isVisibleToUser) {
      super.setUserVisibleHint(isVisibleToUser);
      if (rootView == null) {
         return;
      }
      if (isVisibleToUser) {
         unbinder = ButterKnife.bind(this, rootView);
         initMvp();
         initData();
      }
   }
}
