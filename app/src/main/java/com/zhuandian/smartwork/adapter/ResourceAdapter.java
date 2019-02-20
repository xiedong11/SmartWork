package com.zhuandian.smartwork.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zhuandian.smartwork.R;
import com.zhuandian.smartwork.entity.ResourceEntity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * desc :
 * author：xiedong
 * date：2019/2/20
 */
public class ResourceAdapter extends RecyclerView.Adapter<ResourceAdapter.MyViewHolder> {

    List<ResourceEntity> datas;
    OnDownloadClick onDownloadClick;

    public ResourceAdapter(List<ResourceEntity> datas, OnDownloadClick downloadClick) {
        this.datas = datas;
        this.onDownloadClick = downloadClick;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new MyViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_resource, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder viewHolder, final int i) {
        viewHolder.tvTitle.setText(datas.get(i).getResourceTitle());
        viewHolder.tvContent.setText(datas.get(i).getResourceContent());
        viewHolder.tvTime.setText(datas.get(i).getCreatedAt());
        viewHolder.tvType.setText(datas.get(i).getType() == 1 ? "图片文件" : "其他文件");
        viewHolder.tvDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onDownloadClick != null) {
                    onDownloadClick.onClick(datas.get(i).getResourceUrl());
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return datas == null ? 0 : datas.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_title)
        TextView tvTitle;
        @BindView(R.id.tv_content)
        TextView tvContent;
        @BindView(R.id.tv_time)
        TextView tvTime;
        @BindView(R.id.tv_type)
        TextView tvType;
        @BindView(R.id.tv_download)
        TextView tvDownload;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public interface OnDownloadClick {
        void onClick(String url);
    }
}
