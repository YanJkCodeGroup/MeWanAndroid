package com.android.wanandroid.module.book.booktabfragments;


import android.util.Log;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.mymvp.base.BaseMvpFragment;
import com.android.wanandroid.Contract;
import com.android.wanandroid.R;
import com.android.wanandroid.module.book.bookadapers.BookSystemAdaper;
import com.android.wanandroid.module.book.bookbeans.BookSystemBean;

import java.util.List;

import butterknife.BindView;
import qdx.stickyheaderdecoration.NormalDecoration;

/**
 * A simple {@link Fragment} subclass.
 */
public class BookSystemFragment extends BaseMvpFragment<Contract.BookSystemPresenter> implements Contract.BookSystemView {

    private static final String TAG = "BookSystemFragment";
    @BindView(R.id.fragment_booksys_rec)
    RecyclerView fragmentBooksysRec;

    public BookSystemFragment() {
        // Required empty public constructor
    }

    @Override
    public int initLayout() {
        return R.layout.fragment_book_system;
    }


    @Override
    public void succeed(List<BookSystemBean> sysList) {
        //成功返回数据后,初始化recyclerview
        //把每个条目的名字给这个recycleview的头部
        NormalDecoration normalDecoration = new NormalDecoration() {
            @Override
            public String getHeaderName(int i) {
                return sysList.get(i).getName();
            }
        };
        LinearLayoutManager lm = new LinearLayoutManager(mContext);
        fragmentBooksysRec.setLayoutManager(lm);
        BookSystemAdaper bookSystemAdaper = new BookSystemAdaper(mContext);
        fragmentBooksysRec.setAdapter(bookSystemAdaper);
        fragmentBooksysRec.addItemDecoration(normalDecoration);
        bookSystemAdaper.initDate(sysList);
    }

    @Override
    public void fail(String error) {
        Log.d(TAG, "fail: " + error);
        Toast.makeText(mContext, "错误为:" + error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public Contract.BookSystemPresenter createPresenter() {
        return new com.android.wanandroid.module.book.bookpresenter.BookSystemPresenter();
    }

    @Override
    protected void initData() {
        super.initData();
        mPresenter.initSystemPresenter();
    }


}
