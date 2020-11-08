package com.sys.appstudent;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;


import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.sys.appstudent.appupdate.UpdateService;
import com.sys.appstudent.fragments.FragmentFour;
import com.sys.appstudent.fragments.FragmentOne;
import com.sys.appstudent.fragments.FragmentThree;
import com.sys.appstudent.fragments.FragmentTwo;
import com.sys.appstudent.views.CustomDialog;

import java.util.ArrayList;
public class MainActivity extends FragmentActivity {
    private Button update_btn;

    private BottomNavigationView bottomNavigationView;
    FragmentOne fragmentOne;
    FragmentTwo fragmentTwo;
    FragmentThree fragmentThree;
    FragmentFour fragmentFour;
    private Fragment[] fragments;
    private int lastfragment;//用于记录上个选择的Fragment
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        checkVersion();
        initDataView();
    }

    private void checkVersion(){

        //这里不发送检测新版本网络请求，直接进入下载新版本安装
        CustomDialog builder = new CustomDialog(this);
        builder.setTitle("升级提示");
        builder.setMessage("发现新版本，请及时更新");
        builder.setCancelable(false);
        builder.setCancleBtn(true);
        builder.setConfirmListener(new CustomDialog.ConfirmListener() {
            @Override
            public void onConfirmClick() {

            }
        });
        builder.show();
    }


    @SuppressLint("WrongConstant")
    private void initDataView() {
        ArrayList<Integer> images = new ArrayList<Integer>();
        fragmentOne = FragmentOne.newInstance("ONE");
        fragmentTwo=FragmentTwo.newInstance("TOW");
        fragmentThree =FragmentThree.newInstance("three");
        fragmentFour =FragmentFour.newInstance("four");
        fragments = new Fragment[]{fragmentOne,fragmentTwo,fragmentThree,fragmentFour};
        lastfragment=0;

        bottomNavigationView = findViewById(R.id.navigation);
        bottomNavigationView.setLabelVisibilityMode(1);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.FramePage,fragmentOne).show(fragmentOne).commit();//初始页面显示
        bottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    if(lastfragment!=0){
                        switchFragment(lastfragment,0);
                        lastfragment=0;
                    }
                    return true;
                case R.id.navigation_dashboard:
                    if(lastfragment!=1){
                        switchFragment(lastfragment,1);
                        lastfragment=1;
                    }

                    return true;
                case R.id.navigation_notifications:
                    if(lastfragment!=2){
                        switchFragment(lastfragment,2);
                        lastfragment=2;
                    }

                    return true;
                case R.id.navigation_donut:
                    if(lastfragment!=3){
                        switchFragment(lastfragment,3);
                        lastfragment=3;
                    }
                    return true;
            }
            return false;
        }
    };
    //切换Fragment
    private void switchFragment(int lastfragment,int index)
    {
        FragmentTransaction transaction =getSupportFragmentManager().beginTransaction();
        transaction.hide(fragments[lastfragment]);//隐藏上个Fragment
        if(fragments[index].isAdded()==false) {
            transaction.add(R.id.FramePage,fragments[index]);
        }
        transaction.show(fragments[index]).commitAllowingStateLoss();
    }



}
