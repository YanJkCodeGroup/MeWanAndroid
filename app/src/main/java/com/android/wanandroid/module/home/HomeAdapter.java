package com.android.wanandroid.module.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.wanandroid.R;
import com.bumptech.glide.Glide;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HomeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<HomeBean.DatasBean> homeBeanList;
    private List<HomeBannerBean> homeBannerBeanList;


    public void initData(List<HomeBean.DatasBean> homeList, List<HomeBannerBean> homeBannerList) {
        if (homeBeanList != null) {
            homeBeanList.clear();
        }
        this.homeBeanList = homeList;
        this.homeBannerBeanList = homeBannerList;
        notifyDataSetChanged();
    }

    public void addData(List<HomeBean.DatasBean> homeList) {
        this.homeBeanList.addAll(homeList);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == 0) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_homefragment_banner_layout, parent, false);
            return new ViewHolder1(view);
        } else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_homefragment_art_layout, parent, false);
            return new ViewHolder2(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        int type = getItemViewType(position);
        if (type == 0) {
            ViewHolder1 holder1 = (ViewHolder1) holder;
            holder1.mHomeBannerIv.setImages(homeBannerBeanList).setImageLoader(new ImageLoader() {
                @Override
                public void displayImage(Context context, Object path, ImageView imageView) {
                    HomeBannerBean path1 = (HomeBannerBean) path;
                    Glide.with(context).load(path1.getImagePath()).into(imageView);
                }
            }).setBannerStyle(BannerConfig.NOT_INDICATOR)
                    .setOnBannerListener(new OnBannerListener() {
                        @Override
                        public void OnBannerClick(int position) {

                        }
                    })
                    .start();
        } else {
            if (homeBannerBeanList.size() > 0) {
                position = position - 1;
            }
            ViewHolder2 holder2 = (ViewHolder2) holder;
            holder2.mHomeArtAuthor.setText(homeBeanList.get(position).getAuthor());
            holder2.mHomeArtChapterName.setText(homeBeanList.get(position).getChapterName());
            holder2.mHomeArtNiceDate.setText(homeBeanList.get(position).getNiceDate());
            holder2.mHomeArtSuperChapterName.setText(homeBeanList.get(position).getSuperChapterName() + "· ");
            List<HomeBean.DatasBean.TagsBean> tags = homeBeanList.get(position).getTags();
            if (tags.size() != 0) {
                holder2.mHomeArtTagsName.setText(tags.get(0).getName());
            }
            holder2.mHomeArtTitle.setText(homeBeanList.get(position).getTitle());
            String envelopePic = homeBeanList.get(position).getEnvelopePic();
            Glide.with(holder.itemView.getContext()).load(envelopePic).into(holder2.mHomeArtEnvelopePic);
            int finalPosition = position;
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    rvItemClick.OnClick(v, finalPosition);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        if (homeBannerBeanList != null && homeBeanList != null)
            if (homeBannerBeanList.size() > 0) {
                return homeBeanList.size() + 1;
            } else {
                return homeBeanList.size();
            }
        return 0;
    }

    @Override
    public int getItemViewType(int position) {
        if (homeBannerBeanList.size() > 0 && position == 0) {
            return 0;
        } else {
            return 1;
        }
    }

    class ViewHolder1 extends RecyclerView.ViewHolder {
        @BindView(R.id.home_banner_iv)
        Banner mHomeBannerIv;

        public ViewHolder1(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    class ViewHolder2 extends RecyclerView.ViewHolder {
        @BindView(R.id.home_art_author)
        TextView mHomeArtAuthor;
        @BindView(R.id.home_art_tags_name)
        TextView mHomeArtTagsName;
        @BindView(R.id.home_art_niceDate)
        TextView mHomeArtNiceDate;
        @BindView(R.id.home_art_envelopePic)
        ImageView mHomeArtEnvelopePic;
        @BindView(R.id.home_art_title)
        TextView mHomeArtTitle;
        @BindView(R.id.home_art_superChapterName)
        TextView mHomeArtSuperChapterName;
        @BindView(R.id.home_art_chapterName)
        TextView mHomeArtChapterName;
        @BindView(R.id.home_art_like)
        ImageView mHomeArtLike;

        public ViewHolder2(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    private RvItemClick rvItemClick;

    public void setRvItemClick(RvItemClick rvItemClick) {
        this.rvItemClick = rvItemClick;
    }

    public interface RvItemClick {
        void OnClick(View v, int position);
    }
}
