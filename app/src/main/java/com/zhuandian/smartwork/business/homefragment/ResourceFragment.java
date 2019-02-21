package com.zhuandian.smartwork.business.homefragment;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.zhuandian.smartwork.R;
import com.zhuandian.smartwork.adapter.ResourceAdapter;
import com.zhuandian.smartwork.base.BaseFragment;
import com.zhuandian.smartwork.entity.ResourceEntity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

/**
 * desc :
 * author：xiedong
 * date：2019/2/19
 */
public class ResourceFragment extends BaseFragment {


    @BindView(R.id.rv_resource_list)
    RecyclerView rvResourceList;
    @BindView(R.id.tv_search)
    TextView tvSearch;
    private String classType;
    private boolean isSearch = false;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_resource;
    }

    @Override
    protected void initView() {
        getData();
    }

    private void getData() {
        BmobQuery<ResourceEntity> query = new BmobQuery();

        if (isSearch)
            query.addWhereEqualTo("classType", classType);

        query.order("-createdAt").findObjects(new FindListener<ResourceEntity>() {
            @Override
            public void done(List<ResourceEntity> list, BmobException e) {
                if (e == null) {
                    list.addAll(list);
                    rvResourceList.setAdapter(new ResourceAdapter(list, new ResourceAdapter.OnDownloadClick() {
                        @Override
                        public void onClick(String url) {
                            Uri uri = Uri.parse(url);
                            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                            startActivity(intent);
                        }
                    }));
                    rvResourceList.setLayoutManager(new LinearLayoutManager(actitity));
                }
            }
        });
    }


    @OnClick(R.id.tv_search)
    public void onClick() {
        /*@setView 装入一个EditView
         */
        final EditText editText = new EditText(actitity);
        AlertDialog.Builder inputDialog =
                new AlertDialog.Builder(actitity);
        inputDialog.setTitle("请输入要搜索的学科").setView(editText);
        inputDialog.setPositiveButton("确定",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        isSearch = true;
                        classType = editText.getText().toString();
                        if (TextUtils.isEmpty(editText.getText().toString()))
                            isSearch = false;
                        getData();

                    }
                }).show();
    }
}
