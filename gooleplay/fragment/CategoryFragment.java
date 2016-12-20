package com.itheima95.gooleplay.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.reflect.TypeToken;
import com.itheima95.gooleplay.R;
import com.itheima95.gooleplay.adapter.CategoryAdapter;
import com.itheima95.gooleplay.bean.Category;
import com.itheima95.gooleplay.http.HttpHelper;
import com.itheima95.gooleplay.http.Url;
import com.itheima95.gooleplay.util.GsonUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 99448 on 2016/11/14.
 */

public class CategoryFragment extends BaseFragment {
    private ListView listView ;
    ArrayList<Object> list = new ArrayList<>();

    @Override
    public View getSuccessView() {
        listView = (ListView) View.inflate(getContext(), R.layout.listview, null);
        return listView;
    }

    @Override
    public void loadData() {
        HttpHelper.create().get(Url.Category, new HttpHelper.HttpCallback() {
            @Override
            public void onSuccess(String result) {
                stateLayout.showSuccessView();
                ArrayList<Category> categories = (ArrayList<Category>) GsonUtil.parseJsonToList(result,new TypeToken<List<Category>>(){}.getType());
                for(Category cate:categories) {
                    list.add(cate.title);
                    list.addAll(cate.infos);
                }
                //设置adapter
                listView.setAdapter(new CategoryAdapter(list));
            }

            @Override
            public void onFail(Exception e) {

            }
        });

    }
}
