package com.itheima95.gooleplay.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

/**
 * Created by 99448 on 2016/11/16.
 */

public abstract class MyBaseAdapter<T> extends BaseAdapter {
    ArrayList<T> list;

    public MyBaseAdapter(ArrayList<T> list){
        this.list =list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Object holder ;
        if (convertView==null){
            convertView=View.inflate(parent.getContext(),getItemLayoutId(position),null);
            holder=createViewHolder(convertView,position);
            convertView.setTag(holder);
        }else{
            holder=convertView.getTag();
        }

        T t = list.get(position);

        bindViewHolder(t,holder,position);

        return convertView;

    }


    public abstract int getItemLayoutId(int position) ;

    protected abstract Object createViewHolder(View convertView, int position) ;

    protected abstract void bindViewHolder(T t, Object holder, int position) ;



}
