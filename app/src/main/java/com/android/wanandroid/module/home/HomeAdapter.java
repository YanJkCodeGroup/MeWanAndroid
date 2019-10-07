package com.android.wanandroid.module.home;

import android.content.Context;
import android.graphics.Color;
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
import com.android.wanandroid.widget.CollectView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<HomeBean.DatasBean> homeBeanList;
    private List<HomeBannerBean> homeBannerBeanList;
    private List<HomeTopBean> homeTopBeanList;


    public void initData(List<HomeBean.DatasBean> homeList, List<HomeBannerBean> homeBannerList, List<HomeTopBean> homeTopBeanList) {
        if (homeBeanList != null) {
            homeBeanList.clear();
        }
        this.homeBeanList = homeList;
        this.homeTopBeanList = homeTopBeanList;
        this.homeBannerBeanList = homeBannerList;
        notifyDataSetChanged();
    }

    public void addData(List<HomeBean.DatasBean> homeList) {
        if (homeBeanList != null) {
            this.homeBeanList.addAll(homeList);
            notifyDataSetChanged();
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == 0) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_homefragment_banner_layout, parent, false);
            return new ViewHolder1(view);
        } else if (viewType == 1) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_home_item_layout, parent, false);
            return new ViewHolder2(view);
        } else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_home_item_layout, parent, false);
            return new ViewHolder3(view);
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
        } else if (type==1){
            if (homeBannerBeanList.size() > 0&&homeTopBeanList.size()>0) {
                position = position - 2;
            }
            ViewHolder2 holder2 = (ViewHolder2) holder;
            HomeBean.DatasBean datasBean = homeBeanList.get(position);
            holder2.homeAuthor.setText(datasBean.getAuthor());
            holder2.homeDate.setText(datasBean.getNiceDate());
            holder2.homeSuperChapterName.setText(datasBean.getSuperChapterName());
            holder2.homeName.setText(datasBean.getChapterName());
            holder2.homeTitle.setText(datasBean.getTitle());

            if (!TextUtils.isEmpty(datasBean.getDesc())) {
                holder2.homeContent.setText(datasBean.getDesc());
                holder2.homeContent.setVisibility(View.VISIBLE);
            } else {
                holder2.homeContent.setVisibility(View.GONE);
            }
            //判断是否显示下方tag
            if (TextUtils.isEmpty(datasBean.getSuperChapterName()) || TextUtils.isEmpty(datasBean.getChapterName())) {
                holder2.addSymbols.setVisibility(View.VISIBLE);
            } else {
                holder2.addSymbols.setVisibility(View.GONE);
            }
            //判断是否显示作者前面的标题
            if (datasBean.isFresh()) {
                holder2.newGroup.setVisibility(View.VISIBLE);
            } else {
                holder2.newGroup.setVisibility(View.GONE);
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

                Glide.with(holder2.homeIcon.getContext())
                        .applyDefaultRequestOptions(requestOptions)
                        .load(datasBean.getEnvelopePic())
                        .into(holder2.homeIcon);
                holder2.homeIcon.setVisibility(View.VISIBLE);
            } else {
                holder2.homeIcon.setVisibility(View.GONE);
            }

            //是否被收藏
            if (datasBean.isCollect()) {
                holder2.homeCollect.setChecked(true);
            } else {
                holder2.homeCollect.setChecked(false);
            }
            //设置tag
            if (datasBean.getTags() != null && datasBean.getTags().size() > 0) {
                holder2.homeTag.setText(datasBean.getTags().get(0).getName());
                holder2.homeTag.setVisibility(View.VISIBLE);
            } else {
                holder2.homeTag.setVisibility(View.GONE);
            }
            holder2.homeAuthor.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //todo 作者点击事件
                }
            });
            holder2.homeCollect.setOnClickListener(new CollectView.OnClickListener() {
                @Override
                public void onClick(CollectView v) {
                    datasBean.setCollect(!datasBean.isCollect());
                    //todo 收藏点击事件
                }
            });
            int finalPosition = position;
            holder2.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    rvItemClick.OnClick(v, finalPosition);
                }
            });
        }else{
            if (homeBannerBeanList.size()>0){
                position=position-1;
            }
            ViewHolder3 holder3 = (ViewHolder3) holder;
            HomeTopBean homeTopBean = homeTopBeanList.get(position);
            TextView textView = new TextView(holder.itemView.getContext());
            textView.setText("置顶 ·");
            textView.setTextColor(Color.RED);
            holder3.homeAuthor.setText(textView+homeTopBean.getAuthor());
            holder3.homeDate.setText(homeTopBean.getNiceDate());
            holder3.homeTitle.setText(homeTopBean.getTitle());
            holder3.homeSuperChapterName.setText(homeTopBean.getSuperChapterName()+" · ");
            holder3.homeName.setText(homeTopBean.getChapterName());
//            holder3.homeTag.setText(homeTopBean.getTags().get(0).getName());
            holder3.homeCollect.setOnClickListener(new CollectView.OnClickListener() {
                @Override
                public void onClick(CollectView v) {
                    homeTopBean.setCollect(!homeTopBean.isCollect());
                    //todo 收藏点击事件
                }
            });
            //是否被收藏
            if (homeTopBean.isCollect()) {
                holder3.homeCollect.setChecked(true);
            } else {
                holder3.homeCollect.setChecked(false);
            }
        }
    }

    @Override
    public int getItemCount() {
        /*if (homeBannerBeanList != null && homeBeanList != null) {
            if (homeBannerBeanList.size() > 0) {
                return homeBeanList.size() + 1;
            } else {
                return homeBeanList.size();
            }
        }*/
        if (homeBannerBeanList != null && homeBeanList != null && homeTopBeanList != null) {
            if (homeBannerBeanList.size() > 0 && homeTopBeanList.size() > 0) {
                return homeBeanList.size() + 2;
            } else if (homeBannerBeanList.size() > 0 || homeTopBeanList.size() > 0) {
                return homeBeanList.size() + 1;
            } else {
                return homeBeanList.size();
            }
        }
        return 0;
    }

    @Override
    public int getItemViewType(int position) {
        if (homeBannerBeanList.size() > 0 && position == 0) {
            return 0;
        } else if (homeTopBeanList.size() > 0&&homeBannerBeanList.size()>0) {
            return 2;
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
        @BindView(R.id.home_new)
        TextView homeNew;
        @BindView(R.id.home_author)
        TextView homeAuthor;
        @BindView(R.id.home_tag)
        TextView homeTag;
        @BindView(R.id.home_date)
        TextView homeDate;
        @BindView(R.id.home_icon)
        ImageView homeIcon;
        @BindView(R.id.home_title)
        TextView homeTitle;
        @BindView(R.id.home_content)
        TextView homeContent;
        @BindView(R.id.home_super_chapter_name)
        TextView homeSuperChapterName;
        @BindView(R.id.add_symbols)
        TextView addSymbols;
        @BindView(R.id.home_name)
        TextView homeName;
        @BindView(R.id.home_collect)
        CollectView homeCollect;
        @BindView(R.id.new_group)
        Group newGroup;

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

    class ViewHolder3 extends RecyclerView.ViewHolder {
        @BindView(R.id.home_new)
        TextView homeNew;
        @BindView(R.id.home_author)
        TextView homeAuthor;
        @BindView(R.id.home_tag)
        TextView homeTag;
        @BindView(R.id.home_date)
        TextView homeDate;
        @BindView(R.id.home_icon)
        ImageView homeIcon;
        @BindView(R.id.home_title)
        TextView homeTitle;
        @BindView(R.id.home_content)
        TextView homeContent;
        @BindView(R.id.home_super_chapter_name)
        TextView homeSuperChapterName;
        @BindView(R.id.add_symbols)
        TextView addSymbols;
        @BindView(R.id.home_name)
        TextView homeName;
        @BindView(R.id.home_collect)
        CollectView homeCollect;
        @BindView(R.id.new_group)
        Group newGroup;

        public ViewHolder3(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
