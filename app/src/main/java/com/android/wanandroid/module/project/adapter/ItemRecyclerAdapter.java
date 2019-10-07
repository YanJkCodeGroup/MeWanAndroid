package com.android.wanandroid.module.project.adapter;

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
import com.android.wanandroid.module.project.entity.ProjectItemData;
import com.android.wanandroid.widget.CollectView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ItemRecyclerAdapter extends RecyclerView.Adapter<ItemRecyclerAdapter.ViewHolder> {


   private List<ProjectItemData.DatasBean> mList;

   public void initData(List<ProjectItemData.DatasBean> mProjectLists) {
      if (mList != null) {
         mList.clear();
      }
      mList = mProjectLists;
      notifyDataSetChanged();
   }

   public void addData(List<ProjectItemData.DatasBean> mProjectLists) {
      mList.addAll(mProjectLists);
      notifyDataSetChanged();
   }

   @NonNull
   @Override
   public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
      return new ViewHolder(LayoutInflater.from(parent.getContext())
              .inflate(R.layout.item_project_item_layout, parent, false));
   }

   @Override
   public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
      ProjectItemData.DatasBean datasBean = mList.get(position);
      holder.projectAuthor.setText(datasBean.getAuthor());
      holder.projectDate.setText(datasBean.getNiceDate());
      holder.projectSuperChapterName.setText(datasBean.getSuperChapterName());
      holder.projectName.setText(datasBean.getChapterName());
      holder.projectTitle.setText(datasBean.getTitle());

      if (!TextUtils.isEmpty(datasBean.getDesc())) {
         holder.projectContent.setText(datasBean.getDesc());
         holder.projectContent.setVisibility(View.VISIBLE);
      } else {
         holder.projectContent.setVisibility(View.GONE);
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

         Glide.with(holder.projectIcon.getContext())
                 .applyDefaultRequestOptions(requestOptions)
                 .load(datasBean.getEnvelopePic())
                 .into(holder.projectIcon);
         holder.projectIcon.setVisibility(View.VISIBLE);
      } else {
         holder.projectIcon.setVisibility(View.GONE);
      }

      //是否被收藏
      if (datasBean.isCollect()) {
         holder.projectCollect.setChecked(true);
      } else {
         holder.projectCollect.setChecked(false);
      }
      //设置tag
      if (datasBean.getTags() != null && datasBean.getTags().size() > 0) {
         holder.projectTag.setText(datasBean.getTags().get(0).getName());
         holder.projectTag.setVisibility(View.VISIBLE);
      } else {
         holder.projectTag.setVisibility(View.GONE);
      }
      holder.projectAuthor.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {
            //todo 作者点击事件
         }
      });
      holder.projectCollect.setOnClickListener(new CollectView.OnClickListener() {
         @Override
         public void onClick(CollectView v) {
            datasBean.setCollect(!datasBean.isCollect());
            //todo 收藏点击事件
         }
      });
   }

   @Override
   public int getItemCount() {
      return mList != null ? mList.size() : 0;
   }

   static class ViewHolder extends RecyclerView.ViewHolder {
      @BindView(R.id.project_new)
      TextView projectNew;
      @BindView(R.id.project_author)
      TextView projectAuthor;
      @BindView(R.id.project_tag)
      TextView projectTag;
      @BindView(R.id.project_date)
      TextView projectDate;
      @BindView(R.id.project_icon)
      ImageView projectIcon;
      @BindView(R.id.project_title)
      TextView projectTitle;
      @BindView(R.id.project_content)
      TextView projectContent;
      @BindView(R.id.project_super_chapter_name)
      TextView projectSuperChapterName;
      @BindView(R.id.add_symbols)
      TextView addSymbols;
      @BindView(R.id.project_name)
      TextView projectName;
      @BindView(R.id.project_collect)
      CollectView projectCollect;
      @BindView(R.id.new_group)
      Group newGroup;

      public ViewHolder(@NonNull View itemView) {
         super(itemView);
         ButterKnife.bind(this, itemView);
      }
   }

}
