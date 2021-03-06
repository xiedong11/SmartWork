package com.zhuandian.smartwork.business.homefragment;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zhuandian.smartwork.R;
import com.zhuandian.smartwork.base.BaseFragment;
import com.zhuandian.smartwork.entity.WorkEntity;
import com.zhuandian.smartwork.view.SelectItemView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * desc :
 * author：xiedong
 * date：2019/2/20
 */
public class WorkTestFragment extends BaseFragment {

    private WorkEntity workEntity;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.ll_options_container)
    LinearLayout optionsContainer;


    public static WorkTestFragment newInstance(WorkEntity workEntity) {
        WorkTestFragment fragment = new WorkTestFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("data", workEntity);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_work_test;
    }

    @Override
    protected void initView() {
        workEntity = ((WorkEntity) getArguments().getSerializable("data"));
        tvTitle.setText(workEntity.getTitle());
        String[] options = (workEntity.getOptions()).split("-");
        optionsContainer.removeAllViews();
        for (int i = 0; i < options.length; i++) {
            SelectItemView textView = new SelectItemView(actitity);
            textView.setOptionsName(i)
                    .setOptionsContent(options[i]);
            optionsContainer.addView(textView);
        }
    }


}
