package com.itheima95.gooleplay.adapter;

import android.text.format.Formatter;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.itheima95.gooleplay.R;
import com.itheima95.gooleplay.bean.AppInfo;
import com.itheima95.gooleplay.global.MyApp;
import com.itheima95.gooleplay.global.UILOption;
import com.itheima95.gooleplay.http.Url;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by 99448 on 2016/11/16.
 */

public class HomeAdapter extends MyBaseAdapter<AppInfo> {


    public HomeAdapter(ArrayList<AppInfo> list) {
        super(list);
    }

    @Override
    public int getItemLayoutId(int position) {
        return R.layout.adapter_home;
    }

    @Override
    protected Object createViewHolder(View convertView, int position) {
        return new HomeHolder(convertView);
    }

    @Override
    protected void bindViewHolder(AppInfo appInfo, Object holder, int position) {
        HomeHolder homeHolder = (HomeHolder) holder;
        homeHolder.tvName.setText(appInfo.name);
        homeHolder.rbStar.setRating(appInfo.stars);
        homeHolder.tvSize.setText(Formatter.formatFileSize(MyApp.context,appInfo.size));
        homeHolder.tvDes.setText(appInfo.des);

        //加载图片
        ImageLoader.getInstance().displayImage(Url.IMG_PREFIX+appInfo.iconUrl, homeHolder.ivImage,
                UILOption.options);
    }

    static class HomeHolder {
        @InjectView(R.id.iv_image)
        ImageView ivImage;
        @InjectView(R.id.tv_name)
        TextView tvName;
        @InjectView(R.id.rb_star)
        RatingBar rbStar;
        @InjectView(R.id.tv_size)
        TextView tvSize;
        @InjectView(R.id.tv_des)
        TextView tvDes;

        HomeHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }
}
