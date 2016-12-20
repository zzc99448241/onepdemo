package com.itheima95.gooleplay.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import com.itheima95.gooleplay.R;

/**
 * Created by 99448 on 2016/11/15.
 * 正在加载 加载失败 加载成功
 */

public class StateLayout extends FrameLayout {

    private View loadingView;
    private View errorView;
    private View successView ;

    public StateLayout(Context context) {
        this(context,null);
    }

    public StateLayout(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public StateLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        initView();
    }

    private void initView() {
        //加载loadingview
        loadingView = View.inflate(getContext(), R.layout.page_loading,null);
        addView(loadingView);

        //加载errorview
        errorView = View.inflate(getContext(), R.layout.page_error,null);
        //添加失败的点击事件
        Button btn_reload = (Button) errorView.findViewById(R.id.btn_reload);
        btn_reload.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                showLoadingView();
                if (listener!=null){
                   listener.onReload();
                }
            }
        });

        addView(errorView);

        //加载成功的view是动态的 提供个方法 让外界传入
        //一开始隐藏所有的view

        hideAll();

    }

    public void bindSuccessView(View view){
        successView = view ;
        if (successView!=null) {
            //隐藏
            successView.setVisibility(View.INVISIBLE);
            //添加
            addView(successView);
        }
    }

    public void showSuccessView(){
        hideAll();
        if (successView!=null){
            successView.setVisibility(View.VISIBLE);
        }

    }

    public void showErrorView(){
        hideAll();
        if (errorView!=null){
            errorView.setVisibility(View.VISIBLE);
        }

    }

    public void showLoadingView(){
        hideAll();;
        if (loadingView!=null){
            loadingView.setVisibility(View.VISIBLE);
        }

    }


    private void hideAll() {
        loadingView.setVisibility(View.INVISIBLE);
        errorView.setVisibility(View.INVISIBLE);
        if (successView!=null){
            successView.setVisibility(View.INVISIBLE);
        }


    }


    private OnReloadListener listener;
    public void setOnReloadListener(OnReloadListener listener){
        this.listener=listener;
    }


    public  interface OnReloadListener{
        //当重新加载的按钮被点击的时候调用
        void onReload();

    }


}
