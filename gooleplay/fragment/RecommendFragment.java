package com.itheima95.gooleplay.fragment;

import android.graphics.Color;
import android.view.View;
import android.widget.TextView;

import com.google.gson.reflect.TypeToken;
import com.itheima95.gooleplay.R;
import com.itheima95.gooleplay.http.HttpHelper;
import com.itheima95.gooleplay.http.Url;
import com.itheima95.gooleplay.util.DimenUtil;
import com.itheima95.gooleplay.util.GsonUtil;
import com.itheima95.gooleplay.util.ToastUtil;
import com.itheima95.gooleplay.view.randomlayout.StellarMap;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by 99448 on 2016/11/14.
 */

public class RecommendFragment extends BaseFragment {

    private StellarMap stellarMap ;
    private ArrayList<String> list;



    @Override
    public View getSuccessView() {
        stellarMap = new StellarMap(getContext());
        int padding = DimenUtil.getDimens(R.dimen.dp15);
        //距离周围最小距离15dp
        stellarMap.setInnerPadding(padding,padding,padding,padding);
        return stellarMap;
    }

    @Override
    public void loadData() {
        HttpHelper.create().get(Url.Recommend, new HttpHelper.HttpCallback() {
            @Override
            public void onSuccess(String result) {
                stateLayout.showSuccessView();
                list= (ArrayList<String>) GsonUtil
                        .parseJsonToList(result,new TypeToken<List<String>>(){}.getType());
                stellarMap.setAdapter(new MyAdapter());
                //设置刚进来第几组的数据
                stellarMap.setGroup(0,true);
                //设置xy方向的显示密度，一般情况x*y大于每组的数量
                stellarMap.setRegularity(4,4);

            }

            @Override
            public void onFail(Exception e) {

            }
        });
    }

    class MyAdapter implements StellarMap.Adapter{

        //返回有多少组
        @Override
        public int getGroupCount() {
            return list.size()/getCount(0);
        }

        //这个组有多少数据
        @Override
        public int getCount(int group) {
            return 11;
        }


        //返回每个子view对象
        //当前是第几组， 当前组中的位置
        @Override
        public View getView(int group, int position, View convertView) {
            final TextView textView = new TextView(getContext());
            //1设置文字
            int listPosition = group*getCount(group)+position ;
            textView.setText(list.get(listPosition));
            //2设置随机的文字大小
            Random random = new Random();
            textView.setTextSize(random.nextInt(12)+12);
            //3设置文字随机颜色
            int red = random.nextInt(200);
            int green=random.nextInt(200);
            int blue=random.nextInt(200);
            textView.setTextColor(Color.rgb(red,green,blue));
            //4设置点击事件
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ToastUtil.showToast(textView.getText().toString());
                }
            });
            return textView;
        }

        @Override
        public int getNextGroupOnPan(int group, float degree) {
            return 0;
        }

        @Override
        public int getNextGroupOnZoom(int group, boolean isZoomIn) {
            return (group+1)%getGroupCount();
        }
    }











}
