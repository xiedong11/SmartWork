package com.zhuandian.smartwork.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zhuandian.smartwork.R;
import com.zhuandian.smartwork.entity.CommentEntity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * desc :
 * author：xiedong
 * date：2019/2/19
 */
public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.MyViewHolder> {
    List<CommentEntity> commentEntities;


    public CommentAdapter(List<CommentEntity> commentEntities) {
        this.commentEntities = commentEntities;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new MyViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_comment, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        myViewHolder.tvComment.setText(commentEntities.get(i).getContent());
        myViewHolder.tvName.setText((commentEntities.get(i).getUser() == null ? "无名" : commentEntities.get(i).getUser().getUsername()) + " 说：");
        myViewHolder.tvTime.setText(commentEntities.get(i).getCreatedAt());
    }

    @Override
    public int getItemCount() {
        return commentEntities.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_time)
        TextView tvTime;
        @BindView(R.id.tv_comment)
        TextView tvComment;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
