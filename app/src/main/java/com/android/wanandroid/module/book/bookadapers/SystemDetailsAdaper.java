package com.android.wanandroid.module.book.bookadapers;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.Group;
import androidx.recyclerview.widget.RecyclerView;

import com.android.wanandroid.R;
import com.android.wanandroid.module.book.bookbeans.SystemDetailsBean;
import com.android.wanandroid.widget.CollectView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SystemDetailsAdaper extends RecyclerView.Adapter<SystemDetailsAdaper.ViewHolder> {
    Context context;
    List<SystemDetailsBean.DatasBean> systemDetailsList = new ArrayList<>();


    @NonNull
    @Override
    public SystemDetailsAdaper.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SystemDetailsAdaper.ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_book_item_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull SystemDetailsAdaper.ViewHolder holder, int position) {
        SystemDetailsBean.DatasBean datasBean = systemDetailsList.get(position);
        holder.bookAuthor.setText(datasBean.getAuthor());
        holder.bookDate.setText(datasBean.getNiceDate());
        holder.bookSuperChapterName.setText(datasBean.getSuperChapterName());
        holder.bookName.setText(datasBean.getChapterName());
        holder.bookTitle.setText(datasBean.getTitle());

        if (!TextUtils.isEmpty(datasBean.getDesc())) {
            holder.bookContent.setText(datasBean.getDesc());
            holder.bookContent.setVisibility(View.VISIBLE);
        } else {
            holder.bookContent.setVisibility(View.GONE);
        }
        //判断是否显示下方tag
        if (TextUtils.isEmpty(datasBean.getSuperChapterName()) || TextUtils.isEmpty(datasBean.getChapterName())) {
            holder.addSymbols.setVisibility(View.VISIBLE);
        } else {
            holder.addSymbols.setVisibility(View.GONE);
        }
        //判断是否显示作者前面的标题
        if (datasBean.isFresh()) {
            holder.newGroup.setVisibility(View.VISIBLE);
        } else {
            holder.newGroup.setVisibility(View.GONE);
        }
        //判断是否加载图片
        if (!TextUtils.isEmpty(datasBean.getEnvelopePic())) {
            RequestOptions requestOptions = new RequestOptions();
            //错误占位图
            requestOptions.error(R.drawable.image_holder);
            //默认占位图
            requestOptions.placeholder(R.drawable.image_holder);
            //全部缓存
            requestOptions.diskCacheStrategy(DiskCacheStrategy.ALL);

            Glide.with(holder.bookIcon.getContext())
                    .applyDefaultRequestOptions(requestOptions)
                    .load(datasBean.getEnvelopePic())
                    .into(holder.bookIcon);
            holder.bookIcon.setVisibility(View.VISIBLE);
        } else {
            holder.bookIcon.setVisibility(View.GONE);
        }

        //是否被收藏
        if (datasBean.isCollect()) {
            holder.bookCollect.setChecked(true);
        } else {
            holder.bookCollect.setChecked(false);
        }
        //设置tag
        if (datasBean.getTags() != null && datasBean.getTags().size() > 0) {
            holder.bookTag.setText(datasBean.getChapterName());
            holder.bookTag.setVisibility(View.VISIBLE);
        } else {
            holder.bookTag.setVisibility(View.GONE);
        }
        holder.bookAuthor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //todo 作者点击事件
            }
        });
        holder.bookCollect.setOnClickListener(new CollectView.OnClickListener() {
            @Override
            public void onClick(CollectView v) {
                datasBean.setCollect(!datasBean.isCollect());
                //todo 收藏点击事件
            }
        });
    }


    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.book_new)
        TextView bookNew;
        @BindView(R.id.book_author)
        TextView bookAuthor;
        @BindView(R.id.book_tag)
        TextView bookTag;
        @BindView(R.id.book_date)
        TextView bookDate;
        @BindView(R.id.book_icon)
        ImageView bookIcon;
        @BindView(R.id.book_title)
        TextView bookTitle;
        @BindView(R.id.book_content)
        TextView bookContent;
        @BindView(R.id.book_super_chapter_name)
        TextView bookSuperChapterName;
        @BindView(R.id.add_symbols)
        TextView addSymbols;
        @BindView(R.id.book_name)
        TextView bookName;
        @BindView(R.id.book_collect)
        CollectView bookCollect;
        @BindView(R.id.new_group)
        Group newGroup;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }


    @Override
    public int getItemCount() {
        return systemDetailsList.size();
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
