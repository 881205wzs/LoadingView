package com.davis.loading.demo;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.Gravity;
import android.view.KeyEvent;

import com.davis.loadingview.LoadingView;

/**
 * Created by davis on 19/7/9.
 */

public class CDialog extends Dialog {

    private static CDialog cDialog = null;
    private LoadingView loadView;

    public CDialog(Context context){
        this(context, 0);
    }

    public CDialog(Context context, int theme) {
        super(context, R.style.CustomProgressDialog);
        setContentView(R.layout.loadingdialog);

        getWindow().getAttributes().gravity = Gravity.CENTER;
        setCancelable(true);
        setOnKeyListener(new DialogInterface.OnKeyListener(){

            @Override
            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                // TODO Auto-generated method stub
                if(keyCode == event.KEYCODE_BACK)
                {
                    //true-屏蔽返回键
                    cancelDialog();
                    return false;
                }
                return false;
            }

        });

        loadView = (LoadingView)findViewById(R.id.loadView);
    }

    public static CDialog createDialog(Context context){
        cDialog = new CDialog(context, R.style.CustomProgressDialog);
        cDialog.setContentView(R.layout.loadingdialog);
        cDialog.getWindow().getAttributes().gravity = Gravity.CENTER;
        //禁止点击屏幕消失
        cDialog.setCancelable(false);
        cDialog.setOnKeyListener(new DialogInterface.OnKeyListener(){

            @Override
            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                // TODO Auto-generated method stub
                if(keyCode == event.KEYCODE_BACK)
                {
                    //true-屏蔽返回键
                    //cancelDialog();
                    return true;
                }
                return false;
            }

        });
        return cDialog;
    }

    private static void cancelDialog(){
        cDialog.loadView.cancel();
    }

    public void showDialog(){
        cDialog.loadView.start();
        cDialog.show();
    }

    /**
     *
     * [Summary]
     *       setTitile 标题
     * @param strTitle
     * @return
     *
     */
    public CDialog setTitile(String strTitle){
        return cDialog;
    }

    /**
     *
     * [Summary]
     *       setMessage 提示内容
     * @param strMessage
     * @return
     *
     */
    public void setMessage(String strMessage){
        cDialog.loadView.setLoadingText(strMessage);
    }
}
