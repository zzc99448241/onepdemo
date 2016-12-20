package com.itheima95.gooleplay.fragment;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.itheima95.gooleplay.R;
import com.itheima95.gooleplay.adapter.MyBaseAdapter;
import com.itheima95.gooleplay.http.HttpHelper;

import java.util.ArrayList;

/**
 * Created by 99448 on 2016/11/17.
 */

public abstract class PtrListFragment<T> extends BaseFragment implements AdapterView.OnItemClickListener{

    ArrayList<T> list = new ArrayList<>();
    MyBaseAdapter<T> baseAdapter;

    PullToRefreshListView ptrView ;
    ListView listView ;


    @Override
    public View getSuccessView() {
        ptrView  = (PullToRefreshListView) View.inflate(getContext(), R.layout.ptr_listview,null);
        //设置刷新模式
        ptrView.setMode(PullToRefreshListView.Mode.BOTH);
        //设置刷新器
        ptrView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {

            //下拉刷新和加载都会执行
            @Override
            public void onRefresh(PullToRefreshBase<ListView> refreshView) {
                loadData();

            }
        });

        listView =  ptrView.getRefreshableView();
        //设置去掉自带的divider
        listView.setDividerHeight(0);
        //设置去掉自带的selector
        listView.setSelector(android.R.color.transparent);

        addHeaderView();
        //设置数据适配器
        baseAdapter = getAdapter();
        listView.setAdapter(baseAdapter);

        listView.setOnItemClickListener(this);

        return ptrView;
    }

    protected void addHeaderView() {}

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void loadData() {
        if (ptrView.getCurrentMode()==PullToRefreshListView.Mode.PULL_FROM_START){
            list.clear();
        }

        HttpHelper.create().get(getUrl(),new HttpHelper.HttpCallback() {
            @Override
            public void onSuccess(String result) {
                //显示成功界面
                stateLayout.showSuccessView();
                //解析数据 更新UI
                parseDataAndUpdate(result);
                //完成刷新
                ptrView.onRefreshComplete();
            }

            @Override
            public void onFail(Exception e) {
                if (list.size() == 0) {
                    stateLayout.showErrorView();
                }
            }
        });
    }

    public abstract String getUrl();

    public abstract MyBaseAdapter<T> getAdapter();

    protected abstract void parseDataAndUpdate(String result);
}
