package com.zhuandian.smartwork.business.homefragment;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zhuandian.smartwork.R;
import com.zhuandian.smartwork.adapter.HomeAdapter;
import com.zhuandian.smartwork.base.BaseFragment;
import com.zhuandian.smartwork.entity.WorkEntity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

/**
 * desc :
 * author：xiedong
 * date：2019/2/19
 */
public class WorkFragment extends BaseFragment {


    @BindView(R.id.vp_work)
    ViewPager vpWork;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_work;
    }

    @Override
    protected void initView() {
        final List<BaseFragment> fragmentList = new ArrayList<>();
        BmobQuery<WorkEntity> query = new BmobQuery<>();
        query.order("-createdAt")
                .findObjects(new FindListener<WorkEntity>() {
                    @Override
                    public void done(List<WorkEntity> list, BmobException e) {
                        if (e == null) {
                            for (WorkEntity data : list) {
                                fragmentList.add(WorkTestFragment.newInstance(data));
                                vpWork.setAdapter(new HomeAdapter(getChildFragmentManager(), fragmentList));
                                vpWork.setOffscreenPageLimit(3);
                            }
                        }
                    }
                });

    }


}
