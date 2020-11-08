package com.sys.appstudent.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

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
public class FragmentTwo extends BaseFragment {
    View view ;
    String title ;
    private static final String TITLE="TITLE";
    public static FragmentTwo newInstance(String title){
        Bundle bundle = new Bundle();
        bundle.putString(TITLE,title);
        FragmentTwo fragmentTwo = new FragmentTwo();
        fragmentTwo.setArguments(bundle);
        return fragmentTwo;
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
        view = inflater.inflate(R.layout.fragment_two,container,false);
        initNavBar(view,false,title,false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
