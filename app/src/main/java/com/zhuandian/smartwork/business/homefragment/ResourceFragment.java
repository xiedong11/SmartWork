package com.zhuandian.smartwork.business.homefragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zhuandian.smartwork.R;
import com.zhuandian.smartwork.adapter.CommentAdapter;
import com.zhuandian.smartwork.adapter.ResourceAdapter;
import com.zhuandian.smartwork.base.BaseFragment;
import com.zhuandian.smartwork.entity.ResourceEntity;

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
public class ResourceFragment extends BaseFragment {


    @BindView(R.id.rv_resource_list)
    RecyclerView rvResourceList;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_resource;
    }

    @Override
    protected void initView() {
        BmobQuery<ResourceEntity> query = new BmobQuery();
        query.order("-createdAt")
                .findObjects(new FindListener<ResourceEntity>() {
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


}
