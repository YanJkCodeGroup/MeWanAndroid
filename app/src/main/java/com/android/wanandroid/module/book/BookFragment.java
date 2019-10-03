package com.android.wanandroid.module.book;


import androidx.fragment.app.Fragment;

import com.android.mymvp.base.BaseFragment;
import com.android.wanandroid.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class BookFragment extends BaseFragment {


   public BookFragment() {
      // Required empty public constructor
   }

   @Override
   public int initLayout() {
      return R.layout.fragment_book;
   }
}
