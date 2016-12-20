package com.itheima95.gooleplay.util;

import com.itheima95.gooleplay.global.MyApp;

/**
 * Created by 99448 on 2016/11/15.
 */

public class DimenUtil {
    //获取dimes.xml 中定义的dp值 并会自动转为像素
    public  static  int getDimens (int resId){
        return MyApp.context.getResources().getDimensionPixelSize(resId);

    }

}
