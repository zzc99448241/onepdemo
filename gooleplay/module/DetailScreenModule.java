package com.itheima95.gooleplay.module;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.itheima95.gooleplay.R;
import com.itheima95.gooleplay.activity.DetailActivity;
import com.itheima95.gooleplay.activity.ImageScaleActivity;
import com.itheima95.gooleplay.bean.AppInfo;
import com.itheima95.gooleplay.global.MyApp;
import com.itheima95.gooleplay.global.UILOption;
import com.itheima95.gooleplay.http.Url;
import com.itheima95.gooleplay.util.DimenUtil;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;

import butterknife.InjectView;

/**
 * Created by 99448 on 2016/11/22.
 */

public class DetailScreenModule extends BaseModule<AppInfo> {
    @InjectView(R.id.ll_image)
    LinearLayout llImage;

    DetailActivity activity ;
    public void setActivity(DetailActivity activity){
        this.activity= activity ;
    }

    @Override
    public int getLayoutId() {
        return R.layout.layout_detail_screen;
    }

    @Override
    public void bindData(AppInfo appInfo) {
        int width = DimenUtil.getDimens(R.dimen.dp90);
        int height = DimenUtil.getDimens(R.dimen.dp150);
        int margin = DimenUtil.getDimens(R.dimen.dp12);
        final ArrayList<String> screen = appInfo.screen ;
        for (int i = 0; i <screen.size() ; i++) {
            ImageView imageview= new ImageView(MyApp.context);
            //设置宽高以及margin
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(width,height);
            params.leftMargin= (i==0?0:margin);
            llImage.setLayoutParams(params);
            ImageLoader.getInstance().displayImage(Url.IMG_PREFIX+screen.get(i),imageview, UILOption.options);

            //添加点击事件 定义临时变量
            final int finalI = i ;
            imageview.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(activity,ImageScaleActivity.class);



                }
            });



        }








    }








}
