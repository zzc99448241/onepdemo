package com.itheima95.gooleplay.module;

import android.view.View;

import com.itheima95.gooleplay.global.MyApp;

import butterknife.ButterKnife;

/**
 * Created by 99448 on 2016/11/21.
 */

public abstract class BaseModule<T> {

    View moduleView;
    public BaseModule(){
        moduleView = View.inflate(MyApp.context,getLayoutId(),null);
        ButterKnife.inject(this,moduleView);
    }

    public View getModuleView(){
        return getModuleView();
    }

    public abstract int getLayoutId();

    public abstract void bindData(T data);

}
