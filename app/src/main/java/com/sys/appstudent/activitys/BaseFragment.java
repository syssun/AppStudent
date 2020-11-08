package com.sys.appstudent.activitys;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.IdRes;
import androidx.fragment.app.Fragment;

import com.sys.appstudent.R;

/**
 * @ProjectName: AppStudent
 * @Package: com.sys.appstudent.activitys
 * @ClassName: BaseFragment
 * @Description: 描述
 * @Author: ©sys
 * @CreateDate: 2020/8/13 14:08
 * @Version: v1.0
 */
public class BaseFragment extends Fragment {

    ImageView navback,navuser;
    TextView titlev;
    protected void initNavBar(View view, boolean ishowback, String title, boolean isuser){
        navback = fd(view, R.id.nav_back);
        navuser = fd(view,R.id.nav_user);
        titlev = fd(view,R.id.nav_title);
        navback.setVisibility(ishowback? View.VISIBLE:View.GONE);
        navuser.setVisibility(isuser?View.VISIBLE:View.GONE);
        titlev.setText(title);
    }
    protected <T extends View> T fd(View view,@IdRes int id){
        return view.findViewById(id);
    }

}
