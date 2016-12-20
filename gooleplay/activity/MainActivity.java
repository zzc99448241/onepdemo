package com.itheima95.gooleplay.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.dance.pagerslidingtab_library.PagerSlidingTab;
import com.itheima95.gooleplay.R;
import com.itheima95.gooleplay.adapter.MainAdapter;
import com.itheima95.gooleplay.fragment.AppFragment;
import com.itheima95.gooleplay.fragment.CategoryFragment;
import com.itheima95.gooleplay.fragment.GameFragment;
import com.itheima95.gooleplay.fragment.HomeFragment;
import com.itheima95.gooleplay.fragment.HotFragment;
import com.itheima95.gooleplay.fragment.RecommendFragment;
import com.itheima95.gooleplay.fragment.SubjectFragment;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class MainActivity extends AppCompatActivity {

    @InjectView(R.id.drawerLayout)
    DrawerLayout drawerLayout;
    @InjectView(R.id.slidingTab)
    PagerSlidingTab slidingTab;
    @InjectView(R.id.viewpager)
    ViewPager viewpager;
    private ActionBarDrawerToggle drawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);

        //修改标题
        setActionBar();

        //初始化数据
        initData();

    }

    //初始化数据
    private void initData() {
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(new HomeFragment());
        fragments.add(new AppFragment());
        fragments.add(new GameFragment());
        fragments.add(new SubjectFragment());
        fragments.add(new RecommendFragment());
        fragments.add(new CategoryFragment());
        fragments.add(new HotFragment());

        String [] titles=getResources().getStringArray(R.array.tab_names);

        MainAdapter mainAdapter = new MainAdapter(getSupportFragmentManager(),fragments,titles);
        viewpager.setAdapter(mainAdapter);
        slidingTab.setViewPager(viewpager);

    }

    private void setActionBar() {
        //获取actionbar对象
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(getResources().getString(R.string.app_name));
        //让actionbar可以点击
        actionBar.setDisplayShowHomeEnabled(true); // 让home按钮可以点击
        actionBar.setDisplayHomeAsUpEnabled(true); // 显示home按钮
        //让home按钮变为汉堡包按钮
        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, 0, 0);
        drawerToggle.syncState();
        drawerLayout.addDrawerListener(drawerToggle);

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        drawerToggle.onOptionsItemSelected(item);

        return super.onOptionsItemSelected(item);
    }
}
