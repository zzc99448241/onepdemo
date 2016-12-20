package com.itheima95.gooleplay.adapter;

import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.itheima95.gooleplay.R;
import com.itheima95.gooleplay.bean.SubCategory;
import com.itheima95.gooleplay.global.UILOption;
import com.itheima95.gooleplay.http.Url;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by 99448 on 2016/11/18.
 */
public class CategoryAdapter extends MyBaseAdapter<Object> {

    public CategoryAdapter(ArrayList<Object> list) {
        super(list);
    }

    public final int ITEM_TITLE = 0;
    public final int ITEM_SUB = 1;


    //返回条目类型的总数
    @Override
    public int getViewTypeCount() {
        return 2;
    }

    //获取制定位置的条目是什么类型
    @Override
    public int getItemViewType(int position) {
        Object obj = list.get(position);
        if (obj instanceof String) {
            return ITEM_TITLE;
        } else {
            return ITEM_SUB;
        }
        // return super.getItemViewType(position);
    }

    @Override
    public int getItemLayoutId(int position) {
        //根据条目类型 返回布局
        int itemLayoutType = getItemViewType(position);
        switch (itemLayoutType) {
            case ITEM_TITLE:
                //标题的布局
                return R.layout.adapter_category_title;
            case ITEM_SUB:
                return R.layout.adapter_category_sub;
        }
        return 0;
    }

    @Override
    protected Object createViewHolder(View convertView, int position) {
        //根据条目类型返回对应的holder对象
        int itemLayoutType = getItemViewType(position);
        switch (itemLayoutType){
            case ITEM_TITLE :
                return new TitleHolder(convertView);
            case ITEM_SUB :
                return new SubHolder(convertView);

        }
        return null;
    }

    @Override
    protected void bindViewHolder(Object o, Object holder, int position) {
        //根据条目类型来绑定对象
        int itemLayoutType = getItemViewType(position);
        switch (itemLayoutType){
            case ITEM_TITLE :
                TitleHolder titleHolder = (TitleHolder) holder;
                titleHolder.tvTitle.setText((String) o);
                break;
            case ITEM_SUB :
                SubHolder subHolder = (SubHolder) holder;
                SubCategory subCategory = (SubCategory) o;
                //设置第一个
                subHolder.tvName1.setText(subCategory.name1);
                ImageLoader.getInstance().displayImage(Url.IMG_PREFIX+subCategory.url1,
                        subHolder.ivImage1, UILOption.options);
                //设置第二个
                ViewGroup parent2 = (ViewGroup) subHolder.ivImage2.getParent();
                if (!TextUtils.isEmpty(subCategory.name2)&&!TextUtils.isEmpty(subCategory.url2)){
                    parent2.setVisibility(View.VISIBLE);
                    subHolder.tvName2.setText(subCategory.name2);
                    ImageLoader.getInstance().displayImage(Url.IMG_PREFIX+subCategory.url2,
                            subHolder.ivImage2, UILOption.options);
                }
                //设置第三个
                ViewGroup parent3 = (ViewGroup) subHolder.ivImage3.getParent();
                if (!TextUtils.isEmpty(subCategory.name3)&&!TextUtils.isEmpty(subCategory.url3)){
                    parent3.setVisibility(View.VISIBLE);
                    subHolder.tvName3.setText(subCategory.name3);
                    ImageLoader.getInstance().displayImage(Url.IMG_PREFIX+subCategory.url3,
                            subHolder.ivImage3, UILOption.options);
                }
                break;


        }


    }


    static class TitleHolder {
        @InjectView(R.id.tv_title)
        TextView tvTitle;

        TitleHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }

    class SubHolder {
        @InjectView(R.id.iv_image1)
        ImageView ivImage1;
        @InjectView(R.id.tv_name1)
        TextView tvName1;
        @InjectView(R.id.iv_image2)
        ImageView ivImage2;
        @InjectView(R.id.tv_name2)
        TextView tvName2;
        @InjectView(R.id.iv_image3)
        ImageView ivImage3;
        @InjectView(R.id.tv_name3)
        TextView tvName3;

        SubHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }
}
