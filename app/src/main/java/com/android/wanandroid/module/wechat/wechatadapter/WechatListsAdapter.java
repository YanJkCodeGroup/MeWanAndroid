package com.android.wanandroid.module.wechat.wechatadapter;

import android.content.Context;
import android.view.LayoutInflater;
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
        View inflate1 = LayoutInflater.from(context).inflate(R.layout.wechat_item, parent,false);
//        View inflate = View.inflate(context, R.layout.wechat_item, parent);
        return new ViewHolder(inflate1);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.wechatlist_title.setText(list.get(position).getTitle());
        holder.wechatlist_author.setText(list.get(position).getAuthor());
        holder.wechatlist_niceDate.setText(list.get(position).getNiceDate());
        holder.wechatlist_superChapterName.setText(list.get(position).getSuperChapterName()+"_"+list.get(position).getAuthor());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView wechatlist_title;
        private TextView wechatlist_author;
        private TextView wechatlist_niceDate;
        private TextView wechatlist_superChapterName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            wechatlist_title = itemView.findViewById(R.id.wechatlist_title);
            wechatlist_author = itemView.findViewById(R.id.wechatlist_author);
            wechatlist_niceDate = itemView.findViewById(R.id.wechatlist_niceDate);
            wechatlist_superChapterName = itemView.findViewById(R.id.wechatlist_superChapterName);
        }
    }
}
