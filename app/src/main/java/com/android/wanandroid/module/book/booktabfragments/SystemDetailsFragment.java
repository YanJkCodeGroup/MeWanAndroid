package com.android.wanandroid.module.book.booktabfragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.wanandroid.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class SystemDetailsFragment extends Fragment {


    public SystemDetailsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_system_details, container, false);
    }

}
