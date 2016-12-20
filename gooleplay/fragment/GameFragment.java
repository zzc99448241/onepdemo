package com.itheima95.gooleplay.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.gson.reflect.TypeToken;
import com.itheima95.gooleplay.adapter.HomeAdapter;
import com.itheima95.gooleplay.adapter.MyBaseAdapter;
import com.itheima95.gooleplay.bean.AppInfo;
import com.itheima95.gooleplay.http.Url;
import com.itheima95.gooleplay.util.GsonUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 99448 on 2016/11/14.
 */

public class GameFragment extends PtrListFragment {


    @Override
    public String getUrl() {
        return Url.Game+list.size();
    }

    @Override
    public MyBaseAdapter getAdapter() {
        return new HomeAdapter(list);
    }

    @Override
    protected void parseDataAndUpdate(String result) {
        ArrayList<AppInfo> appInfos = (ArrayList<AppInfo>) GsonUtil
                .parseJsonToList(result,new TypeToken<List<AppInfo>>(){}.getType());
        if (appInfos!=null){
            list.addAll(appInfos);
            baseAdapter.notifyDataSetChanged();
        }
    }
}
