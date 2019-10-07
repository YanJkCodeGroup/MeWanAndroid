package com.android.wanandroid.module.project.fragment;


import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.loadingview.LoadingView;
import com.android.mymvp.base.BaseMvpFragment;
import com.android.utils.log.LogUtil;
import com.android.wanandroid.Contract;
import com.android.wanandroid.R;
import com.android.wanandroid.module.project.adapter.ItemRecyclerAdapter;
import com.android.wanandroid.module.project.entity.ProjectItemData;
import com.android.wanandroid.module.project.presenter.ProjectItemPresenter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProjectItemFragment extends BaseMvpFragment<Contract.ProjectContract.ProjectItemPresenter> implements Contract.ProjectContract.ProjectItemView {

   @BindView(R.id.recycler)
   RecyclerView recycler;
   @BindView(R.id.smart_refresh)
   SmartRefreshLayout smartRefresh;
   private int itemId;
   private ItemRecyclerAdapter mAdapter;
   private int page = 1;

   public ProjectItemFragment(int itemId) {
      this.itemId = itemId;
   }

   @Override
   public int initLayout() {
      return R.layout.fragment_project_item;
   }


   @Override
   protected void initView() {
      LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
      recycler.setLayoutManager(layoutManager);
      mAdapter = new ItemRecyclerAdapter();
      recycler.setAdapter(mAdapter);
      DividerItemDecoration divider = new DividerItemDecoration(mContext,
              DividerItemDecoration.VERTICAL);
      divider.setDrawable(ContextCompat.getDrawable(mContext, R.drawable.project_shape));
      recycler.addItemDecoration(divider);
   }

   @Override
   protected void initData() {
      mPresenter.getProjectItemData(page, itemId);
      showLoading();
   }

   @Override
   protected void initListener() {
      smartRefresh.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
         @Override
         public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
            page++;
            initData();
         }

         @Override
         public void onRefresh(@NonNull RefreshLayout refreshLayout) {
            page = 1;
            initData();
         }
      });
   }

   @Override
   public Contract.ProjectContract.ProjectItemPresenter createPresenter() {
      return new ProjectItemPresenter();
   }

   @Override
   public void onSuccessful(ProjectItemData projectItemData) {
      if (page == 1) {
         mAdapter.initData(projectItemData.getDatas());
      } else {
         mAdapter.addData(projectItemData.getDatas());
      }
      hideLoading();
      smartRefresh.finishLoadMore();
      smartRefresh.finishRefresh();
   }

   @Override
   public void onFailed(String msg) {
      LogUtil.d(msg);
      hideLoading();
      LoadingError(msg, new LoadingView.RetryCallBack() {
         @Override
         public void onRetry() {
            initData();
            showLoading();
         }
      });
      smartRefresh.finishLoadMore();
      smartRefresh.finishRefresh();
   }
}
