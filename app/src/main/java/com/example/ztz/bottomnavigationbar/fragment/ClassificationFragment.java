package com.example.ztz.bottomnavigationbar.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ztz.bottomnavigationbar.R;

/**
 * Created by ztz on 2017/12/26.
 */

public class ClassificationFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.classificationfragment_layout, container, false);
        return view;
    }
}
