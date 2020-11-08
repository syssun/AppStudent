package com.sys.appstudent.views;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.view.ViewGroup.LayoutParams;

import com.sys.appstudent.R;


public class CustomDialog extends Dialog implements View.OnClickListener {
    private Context mContext;
    private TextView tv_cancel,tv_sure,tv_content,tv_title;
    private ConfirmListener confirmListener; //确定监听事件
    private boolean cancleBtn ; //是否显示取消按钮

    public CustomDialog(Context context) {
        super(context,R.style.DialogTheme);
        mContext = context;
        setContentView(R.layout.dialog_layout);
        Window window = getWindow();
        WindowManager.LayoutParams params = window.getAttributes();
        params.gravity = Gravity.CENTER;
        window.setAttributes(params);
        initView();
    }

    private void initView(){
        tv_sure = (TextView)findViewById(R.id.dialog_sure);
        tv_title = (TextView)findViewById(R.id.dialog_title);
        tv_cancel = (TextView) findViewById(R.id.dialog_cancel);
        tv_content = (TextView)findViewById(R.id.dialog_content);
        tv_sure.setOnClickListener(this);
        tv_cancel.setOnClickListener(this);
    }

    public void setCancleBtn(boolean cancleBtn){
        if(cancleBtn){
            tv_cancel.setVisibility(View.GONE);
        }
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.dialog_cancel:
                this.dismiss();
                break;
            case R.id.dialog_sure:
                confirmListener.onConfirmClick();
                this.dismiss();
                break;
        }
    }

    //设置确定事件的监听
    public void setConfirmListener(ConfirmListener confirmListener){
        this.confirmListener = confirmListener;
    }

    //确定事件的监听接口
    public interface ConfirmListener{
        void onConfirmClick();
    }

    //设置提示头部
    public void setTitle(String title){
        tv_title.setText(title);
    }

    //设置提示内容
    public void setMessage(String msg){
        tv_content.setText(msg);
    }
}