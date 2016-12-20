package com.itheima95.gooleplay.adapter;

import android.view.ViewGroup;
import android.widget.ImageView;

import com.itheima95.gooleplay.global.UILOption;
import com.itheima95.gooleplay.http.Url;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;

/**
 * Created by 99448 on 2016/11/17.
 */
public class HomePagerAdapter extends BasePagerAdapter {

    public HomePagerAdapter(ArrayList<String> urlList) {
        super(urlList);
    }

    @Override
    public int getCount() {
        return urlList.size()*10000;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ImageView imageView = new ImageView(container.getContext());
        //设置imageview的宽高可以铺满四边
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);

        //加载图片
        ImageLoader.getInstance().displayImage(Url.IMG_PREFIX+urlList.get(position%urlList.size())
                ,imageView, UILOption.options);

        container.addView(imageView);
        return imageView;
    }
}
