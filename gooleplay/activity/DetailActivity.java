package com.itheima95.gooleplay.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.itheima95.gooleplay.R;
import com.itheima95.gooleplay.bean.AppInfo;
import com.itheima95.gooleplay.http.HttpHelper;
import com.itheima95.gooleplay.http.Url;
import com.itheima95.gooleplay.module.DetailInfoModule;
import com.itheima95.gooleplay.module.DetailSafeModule;
import com.itheima95.gooleplay.util.GsonUtil;
import com.itheima95.gooleplay.view.StateLayout;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by 99448 on 2016/11/21.
 */
public class DetailActivity extends AppCompatActivity {

    @InjectView(R.id.ll_container)
    LinearLayout llContainer;
    @InjectView(R.id.scrollView)
    ScrollView scrollView;

    private String packageName;
    private StateLayout stateLayout;
    private AppInfo appInfo;
    private DetailInfoModule infoModule;
    private DetailSafeModule safeModule;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        packageName =
                getIntent().getStringExtra("packageName");

        setActionBar();

        stateLayout = new StateLayout(this);
        stateLayout.bindSuccessView(getSuccessView());
        stateLayout.showLoadingView();
        loadData();


    }


    //获取成功的view
    public View getSuccessView() {
        View view = View.inflate(this, R.layout.activity_detail, null);
        ButterKnife.inject(this, view);
        //1.加入info 模块
        infoModule = new DetailInfoModule();
        llContainer.addView(infoModule.getModuleView());
        //2.加入safe 模块
        safeModule = new DetailSafeModule();
        llContainer.addView(safeModule.getModuleView());
        //3.加入screen 模块

        //4.加入des 模块


        return view;
    }

    //加载数据
    private void loadData() {
        String url=String.format(Url.Detail,packageName);
        HttpHelper.create().get(url, new HttpHelper.HttpCallback() {
            @Override
            public void onSuccess(String result) {
                stateLayout.showSuccessView();
                appInfo = GsonUtil.parseJsonToBean(result , AppInfo.class);
                if (appInfo !=null){
                    //更新UI
                    updateUI();
                }
            }

            @Override
            public void onFail(Exception e) {

            }
        });
    }

    private void updateUI() {
        //1.绑定info 模块的数据
        infoModule.bindData(appInfo);
    }


    private void setActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(getResources().getString(R.string.app_detail));
        //显示home按钮
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

    }



    //点击菜单返回之前界面
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        finish();
        return super.onOptionsItemSelected(item);
    }
}
