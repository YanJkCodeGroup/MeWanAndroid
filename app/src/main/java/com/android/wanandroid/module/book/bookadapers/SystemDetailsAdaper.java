package com.android.wanandroid.module.book.bookadapers;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.wanandroid.R;
import com.android.wanandroid.module.book.bookbeans.SystemDetailsBean;

import java.util.ArrayList;
import java.util.List;

public class SystemDetailsAdaper extends RecyclerView.Adapter<SystemDetailsAdaper.ViewHolder> {
    Context context;
    List<SystemDetailsBean.DatasBean> systemDetailsList = new ArrayList<>();


    public SystemDetailsAdaper(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_systemdetails, null);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.author.setText(systemDetailsList.get(position).getAuthor());
        holder.title.setText(systemDetailsList.get(position).getTitle());
        holder.chapterName.setText(systemDetailsList.get(position).getSuperChapterName() + "." + systemDetailsList.get(position).getChapterName());
        holder.niceDate.setText(systemDetailsList.get(position).getNiceDate());
    }

    @Override
    public int getItemCount() {
        return systemDetailsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView author;
        TextView title;
        TextView chapterName;
        TextView niceDate;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            author = itemView.findViewById(R.id.sysitem_author);
            title = itemView.findViewById(R.id.sysitem_title);
            chapterName = itemView.findViewById(R.id.sysitem_chapterName);
            niceDate = itemView.findViewById(R.id.sysitem_niceDate);
        }
    }

    public void initRefresh(List<SystemDetailsBean.DatasBean> systemDetailsList) {
        if (this.systemDetailsList != null) {
            this.systemDetailsList.clear();
            this.systemDetailsList.addAll(systemDetailsList);
            notifyDataSetChanged();
        }
    }

    public void initLoadmore(List<SystemDetailsBean.DatasBean> systemDetailsList) {
        if (this.systemDetailsList != null) {
            this.systemDetailsList.addAll(systemDetailsList);
            notifyDataSetChanged();
        }
    }

}
