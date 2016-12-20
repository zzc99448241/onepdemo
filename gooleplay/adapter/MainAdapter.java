package com.itheima95.gooleplay.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Created by 99448 on 2016/11/14.
 */

public class MainAdapter  extends FragmentPagerAdapter{

    private ArrayList<Fragment> fragments ;
    String [] titles ;

    public MainAdapter(FragmentManager fm, ArrayList<Fragment> fragments,String [] titles) {
        super(fm);
        this.fragments=fragments;
        this.titles= titles;

    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }
}
