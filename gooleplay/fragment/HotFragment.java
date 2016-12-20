package com.itheima95.gooleplay.fragment;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import android.widget.TextView;

import com.google.gson.reflect.TypeToken;
import com.itheima95.gooleplay.R;
import com.itheima95.gooleplay.http.HttpHelper;
import com.itheima95.gooleplay.http.Url;
import com.itheima95.gooleplay.util.DimenUtil;
import com.itheima95.gooleplay.util.DrawableUtil;
import com.itheima95.gooleplay.util.GsonUtil;
import com.itheima95.gooleplay.util.ToastUtil;
import com.itheima95.gooleplay.view.FlowLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 99448 on 2016/11/14.
 */

public class HotFragment extends BaseFragment {

    private ScrollView scrollView;
    private FlowLayout flowLayout ;
    ArrayList<String> list ;

    int padding;

    @Override
    public View getSuccessView() {
        scrollView = new ScrollView(getContext());
        flowLayout = new FlowLayout(getContext());

        padding = DimenUtil.getDimens(R.dimen.dp12);
        scrollView.setPadding(padding,padding,padding,padding);
        flowLayout.setPadding(padding,padding,padding,padding);
        scrollView.addView(flowLayout);
        return scrollView;
    }

    @Override
    public void loadData() {
        HttpHelper.create().get(Url.Hot, new HttpHelper.HttpCallback() {
            @Override
            public void onSuccess(String result) {
                stateLayout.showSuccessView();
                list= (ArrayList<String>) GsonUtil
                        .parseJsonToList(result,new TypeToken<List<String>>(){}.getType());
                for (int i=0 ; i<list.size() ;i++){
                    final TextView textView = new TextView(getContext());
                    textView.setText(list.get(i));
                    textView.setTextColor(Color.WHITE);
                    textView.setPadding(padding,padding,padding,padding);

                    //设置背景
                    GradientDrawable press =DrawableUtil.generateDrawable();
                    GradientDrawable normal = DrawableUtil.generateDrawable();
                    textView.setBackgroundDrawable(DrawableUtil.generateSelector(press , normal));



                    textView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            ToastUtil.showToast(textView.getText().toString());
                        }
                    });

                    flowLayout.addView(textView);

                }
            }

            @Override
            public void onFail(Exception e) {
                stateLayout.showErrorView();
            }
        });
    }
}
