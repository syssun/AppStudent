package com.sys.appstudent.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.sys.appstudent.R;
import com.sys.appstudent.activitys.BaseFragment;

import java.util.ArrayList;

/**
 * @ProjectName: AppStudent
 * @Package: com.sys.appstudent.fragments
 * @ClassName: FragmentOne
 * @Description: 描述
 * @Author: ©sys
 * @CreateDate: 2020/8/13 14:08
 * @Version: v1.0
 */
public class FragmentOne extends BaseFragment {
    View view ;
    String title ;
    private static final String TITLE="TITLE";
    public static FragmentOne newInstance(String title){
        Bundle bundle = new Bundle();
        bundle.putString(TITLE,title);
        FragmentOne fragmentOne = new FragmentOne();
        fragmentOne.setArguments(bundle);
        return fragmentOne;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args =  getArguments();
        title =  args.getString(TITLE);

    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_one,container,false);
        initNavBar(view,false,title,false);
        return view;
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
