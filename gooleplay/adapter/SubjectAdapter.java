package com.itheima95.gooleplay.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.itheima95.gooleplay.R;
import com.itheima95.gooleplay.bean.Subject;
import com.itheima95.gooleplay.global.UILOption;
import com.itheima95.gooleplay.http.Url;
import com.itheima95.gooleplay.util.LogUtil;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by 99448 on 2016/11/17.
 */
public class SubjectAdapter extends MyBaseAdapter<Subject> {

    public SubjectAdapter(ArrayList<Subject> list) {
        super(list);
    }

    @Override
    public int getItemLayoutId(int position) {
        return R.layout.adapter_subject;
    }

    @Override
    protected Object createViewHolder(View convertView, int position) {
        return new SubjectHolder(convertView);
    }


    @Override
    protected void bindViewHolder(Subject subject, Object holder, int position) {
        SubjectHolder subjectHolder = (SubjectHolder) holder;
        subjectHolder. tvTitle.setText(subject.des);
        ImageLoader.getInstance().displayImage(Url.IMG_PREFIX+subject.url,
                subjectHolder.ivImage, UILOption.options);

    }

    static class SubjectHolder {
        @InjectView(R.id.iv_image)
        ImageView ivImage;
        @InjectView(R.id.tv_title)
        TextView tvTitle;

        SubjectHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }
}
