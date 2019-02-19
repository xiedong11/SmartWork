package com.zhuandian.smartwork.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.zhuandian.smartwork.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * desc :
 * author：xiedong
 * date：2019/2/19
 */
public class HomeAdapter extends FragmentPagerAdapter {
    List<BaseFragment> fragmentList;

    public HomeAdapter(FragmentManager fm, List<BaseFragment> fragments) {
        super(fm);
        this.fragmentList = fragments;
    }

    @Override
    public Fragment getItem(int i) {
        return fragmentList.get(i);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }
}
