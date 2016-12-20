package com.itheima95.gooleplay.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.itheima95.gooleplay.view.StateLayout;

/**
 * Created by 99448 on 2016/11/15.
 */

public abstract class BaseFragment extends Fragment implements StateLayout.OnReloadListener {

    StateLayout stateLayout;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if (stateLayout==null){
            stateLayout = new StateLayout(getContext());
            //设置成功的successview
            stateLayout.bindSuccessView(getSuccessView());
            //一开始显示loadingview界面
            stateLayout.showLoadingView();
            //设置重新加载按钮被点击的监听器
            stateLayout.setOnReloadListener(this);

            //加载数据
            loadData();
        }

        return stateLayout;
    }

    @Override
    public void onReload() {
        loadData();
    }

    public abstract View getSuccessView();

    public abstract void loadData();






}
