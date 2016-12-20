package com.itheima95.gooleplay.fragment;

import com.google.gson.reflect.TypeToken;
import com.itheima95.gooleplay.adapter.MyBaseAdapter;
import com.itheima95.gooleplay.adapter.SubjectAdapter;
import com.itheima95.gooleplay.bean.Subject;
import com.itheima95.gooleplay.http.Url;
import com.itheima95.gooleplay.util.GsonUtil;
import com.itheima95.gooleplay.util.LogUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 99448 on 2016/11/14.
 */

public class SubjectFragment extends PtrListFragment<Subject> {


    @Override
    public String getUrl() {
        return Url.Subject+list.size();
    }

    @Override
    public MyBaseAdapter getAdapter() {
        return new SubjectAdapter(list);
    }

    @Override
    protected void parseDataAndUpdate(String result) {

        LogUtil.e(result);
        ArrayList<Subject> subjects = (ArrayList<Subject>) GsonUtil
                .parseJsonToList(result,new TypeToken<List<Subject>>(){}.getType());
        if (subjects!=null){
            list.addAll(subjects);
            LogUtil.e("你好");
            baseAdapter.notifyDataSetChanged();
        }
    }
}
