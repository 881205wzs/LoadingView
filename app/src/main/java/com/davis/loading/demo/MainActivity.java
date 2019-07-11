package com.davis.loading.demo;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.davis.loadingview.ShapeLoadingView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btn;
    private ShapeLoadingView loadingView;

    /** 加载等待提示框 */
    private CDialog progressDialog = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }

    private void init(){
        loadingView = (ShapeLoadingView)findViewById(R.id.loadView);

        btn = (Button)findViewById(R.id.btn);
        btn.setOnClickListener(this);

        btn = (Button)findViewById(R.id.btn_2);
        btn.setOnClickListener(this);
    }

    /**
     * 显示加载框
     */
    private void startProgressDialog(String msg){
        if (progressDialog == null){
            progressDialog = CDialog.createDialog(this);
            progressDialog.setMessage(msg);
        }
        progressDialog.showDialog();
    }
    /**
     * 结束加载框
     */
    private void stopProgressDialog(){
        if (progressDialog != null){
            progressDialog.dismiss();
            progressDialog = null;
        }
    }

    private void start(){
        startProgressDialog("");
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                stopProgressDialog();
            }
        }, 3000);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn:
                start();
                break;
            case R.id.btn_2:
                loadingView.changeShape();
                break;
        }
    }
}
