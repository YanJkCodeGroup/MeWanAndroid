package com.android.wanandroid.module.wechat.wechatadapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.wanandroid.R;

import com.android.wanandroid.module.project.entity.ProjectItemData;
import com.android.wanandroid.module.wechat.WechatListBean;

import java.util.List;

public class WechatListsAdapter extends RecyclerView.Adapter<WechatListsAdapter.ViewHolder> {

    private List<WechatListBean.DatasBean> list;
    public void initData(List<WechatListBean.DatasBean> mProjectLists) {
        if (list != null) {
            list.clear();
        }
        list = mProjectLists;
        notifyDataSetChanged();
    }

    public void addData(List<WechatListBean.DatasBean> mProjectLists) {
        list.addAll(mProjectLists);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate1 = LayoutInflater.from(parent.getContext()).inflate(R.layout.wechat_item, parent,false);
//        View inflate = View.inflate(context, R.layout.wechat_item, parent);
        return new ViewHolder(inflate1);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.wechatlist_title.setText(list.get(position).getTitle());
        holder.wechatlist_author.setText(list.get(position).getAuthor());
        holder.wechatlist_niceDate.setText(list.get(position).getNiceDate());
        holder.wechatlist_superChapterName.setText(list.get(position).getSuperChapterName()+"_"+list.get(position).getAuthor());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickListener.onClick(view,position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list != null ? list.size() : 0;
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
    private OnClickListener onClickListener;

    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public interface OnClickListener{
        void onClick(View v,int position);
    }
}
