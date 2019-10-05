package com.android.wanandroid.module.book.bookadapers;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.wanandroid.R;
import com.android.wanandroid.module.book.bookbeans.BookSystemBean;
import com.android.wanandroid.module.book.bookui.FlowLayout;

import java.util.ArrayList;
import java.util.List;

public class BookSystemAdaper extends RecyclerView.Adapter<BookSystemAdaper.ViewHolder> {
    Context context;

    List<BookSystemBean> sysList = new ArrayList<>();

    public BookSystemAdaper(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.fragment_system_item, null);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        List<BookSystemBean.ChildrenBean> children = sysList.get(position).getChildren();
        for (int j = 0; j < children.size(); j++) {
            TextView view = (TextView) LayoutInflater.from(context).inflate(R.layout.item_navbook, null);
            view.setText(children.get(j).getName());
            final int finalJ = j;
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, "你点击了" + children.get(finalJ).getName(), Toast.LENGTH_SHORT).show();
                }
            });
            holder.fl.addView(view);
        }
    }

    @Override
    public int getItemCount() {
        return sysList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        FlowLayout fl;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            fl = itemView.findViewById(R.id.fragment_booksys_itemflow);
        }
    }

    public void initDate(List<BookSystemBean> sysList) {
        if (this.sysList != null) {
            this.sysList.clear();
            this.sysList.addAll(sysList);
            notifyDataSetChanged();
        }
    }
}
