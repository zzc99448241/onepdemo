package com.itheima95.gooleplay.module;

import android.support.v4.view.ViewCompat;
import android.text.format.Formatter;
import android.view.ViewTreeObserver;
import android.view.animation.BounceInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.itheima95.gooleplay.R;
import com.itheima95.gooleplay.bean.AppInfo;
import com.itheima95.gooleplay.global.MyApp;
import com.itheima95.gooleplay.global.UILOption;
import com.itheima95.gooleplay.http.Url;
import com.nostra13.universalimageloader.core.ImageLoader;

import butterknife.InjectView;

/**
 * Created by 99448 on 2016/11/21.
 */

public class DetailInfoModule extends BaseModule<AppInfo> {
    @InjectView(R.id.iv_image)
    ImageView ivImage;
    @InjectView(R.id.tv_name)
    TextView tvName;
    @InjectView(R.id.rb_star)
    RatingBar rbStar;
    @InjectView(R.id.tv_download_num)
    TextView tvDownloadNum;
    @InjectView(R.id.tv_version)
    TextView tvVersion;
    @InjectView(R.id.tv_date)
    TextView tvDate;
    @InjectView(R.id.tv_size)
    TextView tvSize;
    @InjectView(R.id.ll_info)
    LinearLayout llInfo;


    @Override
    public int getLayoutId() {
        return R.layout.layout_detail_info;
    }


    //绑定数据
    @Override
    public void bindData(AppInfo appInfo) {
        ImageLoader.getInstance().displayImage(Url.IMG_PREFIX+appInfo.iconUrl,ivImage, UILOption.options);
        tvName.setText(appInfo.name);
        rbStar.setRating(appInfo.stars);
        tvDownloadNum.setText("下载："+appInfo.downloadNum);
        tvVersion.setText("版本："+appInfo.version);
        tvDate.setText("日期："+appInfo.date);
        tvSize.setText("大小："+ Formatter.formatFileSize(MyApp.context,appInfo.size));

        //执行掉落动画
        //添加一个布局完成的监听器
        llInfo.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                llInfo.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                llInfo.setTranslationY(-llInfo.getHeight());
                //通过属性动画移动下来
                ViewCompat.animate(llInfo).translationY(0)
                        .setDuration(800).setStartDelay(400)
                        .setInterpolator(new BounceInterpolator())//像球落地一样的感觉
                        .start();
            }
        });



    }






















}

