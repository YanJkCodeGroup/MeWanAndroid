package com.android.wanandroid.module.book.bookadapers;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.wanandroid.R;
import com.android.wanandroid.module.activity.DetailsWebActivity;
import com.android.wanandroid.module.book.bookbeans.BookNavigationBean;
import com.android.wanandroid.module.book.bookui.FlowLayout;

import java.util.ArrayList;
import java.util.List;

public class BookNavRecAdaper extends RecyclerView.Adapter<BookNavRecAdaper.ViewHolder> {
    Context context;

    List<BookNavigationBean> data = new ArrayList<>();

    public BookNavRecAdaper(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.fragment_navigation_item, null);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //List<NodeNaviBean.DataBean.ArticlesBean> articles = data.get(i).getArticles();
        List<BookNavigationBean.ArticlesBean> articles = data.get(position).getArticles();
        for (int j = 0; j < articles.size(); j++) {
            TextView view = (TextView) LayoutInflater.from(context).inflate(R.layout.item_navbook, null);
            view.setText(articles.get(j).getTitle());
            final int finalJ = j;
            int finalJ1 = j;
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, DetailsWebActivity.class);
                    String link = articles.get(finalJ1).getLink();
                    intent.putExtra("link", link);
                    intent.putExtra("title", articles.get(finalJ1).getTitle());
                    context.startActivity(intent);
                }
            });
            holder.fl.addView(view);
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        FlowLayout fl;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            fl = itemView.findViewById(R.id.fragment_booknav_itemflow);
        }
    }


    public void initDate(List<BookNavigationBean> data) {
        if (this.data != null) {
            this.data.clear();
            this.data.addAll(data);
            notifyDataSetChanged();
        }
    }


}
