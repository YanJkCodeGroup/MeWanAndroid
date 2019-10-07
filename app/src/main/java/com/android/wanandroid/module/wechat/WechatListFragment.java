package com.android.wanandroid.module.wechat;

import android.util.Log;
import android.widget.Toast;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.mymvp.base.BaseMvpFragment;
import com.android.wanandroid.Contract;
import com.android.wanandroid.R;
import com.android.wanandroid.module.wechat.wechatadapter.WechatListsAdapter;
import com.android.wanandroid.module.wechat.wehcatpresetner.WechatListPresenter;

import java.util.List;

import butterknife.BindView;

public class WechatListFragment extends BaseMvpFragment<Contract.WechatlistPresenter> implements Contract.WechatlistView {
    @BindView(R.id.wechatlist_rec)
    RecyclerView wechatlistRec;
    private int id;
    private int page=1;

    private WechatListsAdapter wechatListsAdapter;

    @Override
    public int initLayout() {
        return R.layout.fragment_wechatlist;
    }

    @Override
    public void succeed(WechatListBean wechatList) {
        List<WechatListBean.DatasBean> datas = wechatList.getDatas();
        wechatListsAdapter = new WechatListsAdapter(datas, getActivity());
        wechatlistRec.setAdapter(wechatListsAdapter);
        wechatListsAdapter.notifyDataSetChanged();
    }

    @Override
    public void fail(String error) {
        Log.d("booknav", "fail: " + error);
        Toast.makeText(mContext, "错误为:" + error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public Contract.WechatlistPresenter createPresenter() {
        return new WechatListPresenter();
    }

    @Override
    protected void initData() {
        super.initData();
        id = Integer.parseInt(getArguments().getString("id"));
        mPresenter.initwechatlistPresenter(id,page);
        wechatlistRec.setLayoutManager(new LinearLayoutManager(getActivity()));
        DividerItemDecoration divider = new DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL);
        divider.setDrawable(ContextCompat.getDrawable(getActivity(),R.drawable.wechat_shape));
        wechatlistRec.addItemDecoration(divider);

    }
}
