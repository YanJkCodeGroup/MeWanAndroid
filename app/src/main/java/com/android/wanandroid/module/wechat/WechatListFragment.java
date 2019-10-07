package com.android.wanandroid.module.wechat;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.loadingview.LoadingView;
import com.android.mymvp.base.BaseMvpFragment;
import com.android.utils.log.LogUtil;
import com.android.wanandroid.Contract;
import com.android.wanandroid.R;
import com.android.wanandroid.module.book.bookactivity.BookNavigationActivity;
import com.android.wanandroid.module.wechat.wechatadapter.WechatListsAdapter;
import com.android.wanandroid.module.wechat.wehcatpresetner.WechatListPresenter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.List;

import butterknife.BindView;

public class WechatListFragment extends BaseMvpFragment<Contract.WechatlistPresenter> implements Contract.WechatlistView {
    @BindView(R.id.wechatlist_rec)
    RecyclerView wechatlistRec;
    @BindView(R.id.smart_refresh)
    SmartRefreshLayout smartRefresh;
    private int id;
    private int page = 1;

    private WechatListsAdapter wechatListsAdapter;

    @Override
    public int initLayout() {
        return R.layout.fragment_wechatlist;
    }

    @Override
    public void succeed(WechatListBean wechatList) {
        if (page == 1) {
            wechatListsAdapter.initData(wechatList.getDatas());
        } else {
            wechatListsAdapter.addData(wechatList.getDatas());
        }
        hideLoading();
        smartRefresh.finishLoadMore();
        smartRefresh.finishRefresh();
        wechatListsAdapter.setOnClickListener(new WechatListsAdapter.OnClickListener() {
            @Override
            public void onClick(View v, int position) {
                Intent intent = new Intent(mActivity, BookNavigationActivity.class);
                intent.putExtra("link",wechatList.getDatas().get(position).getLink());
                startActivity(intent);
            }
        });
    }

    @Override
    public void fail(String error) {
        Log.d("booknav", "fail: " + error);
        Toast.makeText(mContext, "错误为:" + error, Toast.LENGTH_SHORT).show();
        LogUtil.d(error);
        hideLoading();
        LoadingError(error, new LoadingView.RetryCallBack() {
            @Override
            public void onRetry() {
                initData();
                showLoading();
            }
        });
        smartRefresh.finishLoadMore();
        smartRefresh.finishRefresh();
    }

    @Override
    public Contract.WechatlistPresenter createPresenter() {
        return new WechatListPresenter();
    }

    @Override
    protected void initData() {
        super.initData();
        id = Integer.parseInt(getArguments().getString("id"));
        mPresenter.initwechatlistPresenter(id, page);
        showLoading();


    }

    @Override
    protected void initView() {

        wechatlistRec.setLayoutManager(new LinearLayoutManager(getActivity()));
        wechatListsAdapter = new WechatListsAdapter();
        wechatlistRec.setAdapter(wechatListsAdapter);
        DividerItemDecoration divider = new DividerItemDecoration(mContext, DividerItemDecoration.VERTICAL);
        divider.setDrawable(ContextCompat.getDrawable(mContext, R.drawable.wechat_shape));
        wechatlistRec.addItemDecoration(divider);
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
             page=1;
             initData();
         }
     });
    }

}
