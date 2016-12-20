package com.itheima95.gooleplay.util;

import android.util.Log;

/**
 * Created by 99448 on 2016/11/15.
 */

public class LogUtil {
    private static final String TAG="LogUtil";
    //是否是开发试调环境 当项目闪现的时候 将该变量置为false即可
    private static  boolean isDebug = true ;

    public static void d(String tag,String msg){
        if(isDebug){
            Log.d(tag,msg);
        }
    }

    /**
     * 打印e级别的log
     * @param tag
     * @param msg
     */
    public static void e(String tag,String msg){
        if(isDebug){
            Log.e(tag,msg);
        }
    }

    /**
     * 打印d级别的log，不需要传tag
     * @param msg
     */
    public static void d(String msg){
        if(isDebug){
            Log.d(TAG,msg);
        }
    }

    /**
     * 打印e级别的log，不需要传tag
     * @param msg
     */
    public static void e(String msg){
        if(isDebug){
            Log.e(TAG,msg);
        }
    }















}
