package com.itheima95.gooleplay.global;

import android.app.Application;
import android.content.Context;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

/**
 * Created by 99448 on 2016/11/14.
 */

public class MyApp extends Application {

    public static Context context ;

    @Override
    public void onCreate() {
        super.onCreate();
        context=this;

        //初始化UniversalImageLoader
        ImageLoader.getInstance().init(ImageLoaderConfiguration.createDefault(this));

    }
}
