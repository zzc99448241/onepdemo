package com.itheima95.gooleplay.util;

import android.widget.Toast;

import com.itheima95.gooleplay.global.MyApp;

/**
 * Created by 99448 on 2016/11/14.
 */

public class ToastUtil {
    private static Toast toast ;

    public static void showToast(String text){
        if (toast == null) {
           toast= Toast.makeText(MyApp.context, text, Toast.LENGTH_SHORT);
        }
        toast.setText(text);
        toast.show();

    }





}
