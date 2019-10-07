package com.android.wanandroid.module.book.booktabfragments;


import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.widget.LinearLayout;

import com.android.mymvp.base.BaseMvpFragment;
import com.android.wanandroid.Contract;
import com.android.wanandroid.R;
import com.android.wanandroid.module.book.bookadapers.SystemDetailsAdaper;
import com.android.wanandroid.module.book.bookadapers.SystemTabAndVpAdaper;
import com.android.wanandroid.module.book.bookbeans.SystemDetailsBean;
import com.android.wanandroid.module.book.bookpresenter.SystemDetailsPresenter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.List;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class SystemDetailsFragment extends BaseMvpFragment<Contract.SystemDetailsPresenter> implements Contract.SystemDetailsView {
    @BindView(R.id.fragment_system_rec)
    RecyclerView fragmentSystemRec;
    @BindView(R.id.fragment_system_sm)
    SmartRefreshLayout fragmentSystemSm;
    private static final String TAG = "SystemDetailsFragment";
    private int id;
    private int page = 0;
    private SystemDetailsAdaper systemDetailsAdaper;

    public SystemDetailsFragment(int id) {
        this.id = id;
    }

    @Override
    protected void initView() {
        LinearLayoutManager lm = new LinearLayoutManager(mContext);
        fragmentSystemRec.setLayoutManager(lm);
        DividerItemDecoration dd = new DividerItemDecoration(mContext, LinearLayout.VERTICAL);
        dd.setDrawable(ContextCompat.getDrawable(mContext, R.drawable.system_details_line_shap));
        fragmentSystemRec.addItemDecoration(dd);
        systemDetailsAdaper = new SystemDetailsAdaper();
        fragmentSystemRec.setAdapter(systemDetailsAdaper);
        fragmentSystemSm.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                page++;
                mPresenter.initSystemDetailsPresenter(page, id);
            }
        });

        fragmentSystemSm.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                page = 1;
                mPresenter.initSystemDetailsPresenter(page, id);
            }
        });
    }

    @Override
    protected void initData() {
        super.initData();
        mPresenter.initSystemDetailsPresenter(page, id);
    }

    @Override
    public int initLayout() {
        return R.layout.fragment_system_details;
    }


    @Override
    public void succeed(SystemDetailsBean systemDetails) {
        List<SystemDetailsBean.DatasBean> systemDetailsList = systemDetails.getDatas();
        if (page == 1) {
            systemDetailsAdaper.initRefresh(systemDetailsList);
            fragmentSystemSm.finishRefresh();
        } else {
            systemDetailsAdaper.initLoadmore(systemDetailsList);
            fragmentSystemSm.finishLoadMore();
        }
    }

    @Override
    public void fail(String error) {
        Log.d(TAG, "fail: " + error);
    }

    @Override
    public Contract.SystemDetailsPresenter createPresenter() {
        return new SystemDetailsPresenter();
    }
}
