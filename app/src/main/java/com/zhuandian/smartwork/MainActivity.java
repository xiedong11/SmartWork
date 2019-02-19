package com.zhuandian.smartwork;

import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.LinearLayout;

import com.zhuandian.smartwork.adapter.HomeAdapter;
import com.zhuandian.smartwork.base.BaseActivity;
import com.zhuandian.smartwork.base.BaseFragment;
import com.zhuandian.smartwork.business.homefragment.DiscussFragment;
import com.zhuandian.smartwork.business.homefragment.MineFragment;
import com.zhuandian.smartwork.business.homefragment.ResourceFragment;
import com.zhuandian.smartwork.business.homefragment.WorkFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    @BindView(R.id.home_viewpager)
    ViewPager homeViewpager;
    @BindView(R.id.ll_discuss)
    LinearLayout llDiscuss;
    @BindView(R.id.ll_source)
    LinearLayout llSource;
    @BindView(R.id.ll_work)
    LinearLayout llWork;
    @BindView(R.id.ll_my)
    LinearLayout llMy;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void setUpView() {
        List<BaseFragment> fragmentList = new ArrayList<>();
        fragmentList.add(new DiscussFragment());
        fragmentList.add(new ResourceFragment());
        fragmentList.add(new WorkFragment());
        fragmentList.add(new MineFragment());
        homeViewpager.setAdapter(new HomeAdapter(getSupportFragmentManager(), fragmentList));
        homeViewpager.setOffscreenPageLimit(3);
    }


    @OnClick({R.id.ll_discuss, R.id.ll_source, R.id.ll_work, R.id.ll_my})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_discuss:
                homeViewpager.setCurrentItem(0);
                break;
            case R.id.ll_source:
                homeViewpager.setCurrentItem(1);
                break;
            case R.id.ll_work:
                homeViewpager.setCurrentItem(2);
                break;
            case R.id.ll_my:
                homeViewpager.setCurrentItem(3);
                break;
        }
    }
}
