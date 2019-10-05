package com.android.wanandroid.module.book.booktabfragments;

import android.util.Log;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.mymvp.base.BaseMvpFragment;
import com.android.wanandroid.Contract;
import com.android.wanandroid.R;
import com.android.wanandroid.module.book.bookadapers.BookNavRecAdaper;
import com.android.wanandroid.module.book.bookbeans.BookNavigationBean;
import com.android.wanandroid.module.book.bookpresenter.BookNavigationPresenter;

import java.util.List;

import butterknife.BindView;
import qdx.stickyheaderdecoration.NormalDecoration;


/**
 * A simple {@link Fragment} subclass.
 */
public class BookNavigationFragment extends BaseMvpFragment<Contract.bookNavigationPresenter> implements Contract.bookNavigationView {

    private static final String TAG = "BookNavigationFragment";

    @BindView(R.id.fragment_booknav_rec)
    RecyclerView fragmentBooknavRec;

    public BookNavigationFragment() {
        // Required empty public constructor
    }

    @Override
    public int initLayout() {
        return R.layout.fragment_book_navigation;
    }


    @Override
    public void succeed(List<BookNavigationBean> beanList) {
        NormalDecoration normalDecoration = new NormalDecoration() {
            @Override
            public String getHeaderName(int i) {
                return beanList.get(i).getName();
            }
        };

        //数据成功返回后,加载布局管理器设置粘性头布局
        LinearLayoutManager lm = new LinearLayoutManager(getContext());
        fragmentBooknavRec.setLayoutManager(lm);
        fragmentBooknavRec.addItemDecoration(normalDecoration);
        BookNavRecAdaper bookNavRecAdaper = new BookNavRecAdaper(mContext);
        fragmentBooknavRec.setAdapter(bookNavRecAdaper);
        bookNavRecAdaper.initDate(beanList);
    }

    @Override
    public void fail(String error) {
        Log.d("booknav", "fail: " + error);
        Toast.makeText(mContext, "错误为:" + error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public Contract.bookNavigationPresenter createPresenter() {
        return new BookNavigationPresenter();
    }

    @Override
    protected void initData() {
        super.initData();
        mPresenter.initNavigationPresenter();
    }
}
