package com.itheima95.gooleplay.fragment;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.itheima95.gooleplay.R;
import com.itheima95.gooleplay.activity.DetailActivity;
import com.itheima95.gooleplay.adapter.HomeAdapter;
import com.itheima95.gooleplay.adapter.HomePagerAdapter;
import com.itheima95.gooleplay.adapter.MyBaseAdapter;
import com.itheima95.gooleplay.bean.AppInfo;
import com.itheima95.gooleplay.bean.Home;
import com.itheima95.gooleplay.http.HttpHelper;
import com.itheima95.gooleplay.http.Url;
import com.itheima95.gooleplay.util.GsonUtil;

import java.util.ArrayList;

/**
 * Created by 99448 on 2016/11/14.
 */

public class HomeFragment extends PtrListFragment<AppInfo> {
    private ViewPager viewPager;
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
        }
    };

    @Override
    public void onStart() {
        super.onStart();
        handler.sendEmptyMessageDelayed(0,2000);
    }

    @Override
    public void onStop() {
        super.onStop();
        handler.removeMessages(0);
    }

    @Override
    public void onItemClick(AdapterView adapterView, View view, int position, long l) {
        Intent intent = new Intent(getActivity(),DetailActivity.class);
        intent.putExtra("packageName",list.get(position-2).packageName);
        startActivity(intent);
    }

    @Override
    protected void addHeaderView() {
        View headerView = View.inflate(getContext(),R.layout.layout_home_header,null);

        viewPager = (ViewPager) headerView.findViewById(R.id.pager);
        listView.addHeaderView(headerView);
    }

    @Override
    public String getUrl() {
        return Url.Home+list.size();
    }

    @Override
    public MyBaseAdapter getAdapter() {
        return new HomeAdapter(list);
    }

    @Override
    protected void parseDataAndUpdate(String result) {
        Home home = GsonUtil.parseJsonToBean(result,Home.class);
        if (home!=null){
            if (home.picture!=null&&home.picture.size()>0){
                viewPager.setAdapter(new HomePagerAdapter(home.picture));
            }
            //添加数据
            list.addAll(home.list);
            baseAdapter.notifyDataSetChanged();

        }
    }
}
