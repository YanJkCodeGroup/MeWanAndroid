package com.android.wanandroid.module.wechat.wechatadapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.wanandroid.R;

import com.android.wanandroid.module.wechat.WechatListBean;

import java.util.List;

public class WechatListsAdapter extends RecyclerView.Adapter<WechatListsAdapter.ViewHolder> {

    private List<WechatListBean.DatasBean> list;
    private Context context;

    public WechatListsAdapter(List<WechatListBean.DatasBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = View.inflate(context, R.layout.wechat_item, null);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.wechatlist_title.setText(list.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView wechatlist_title;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            wechatlist_title = itemView.findViewById(R.id.wechatlist_title);
        }
    }
}
