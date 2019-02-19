package com.zhuandian.smartwork.business.homefragment;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zhuandian.smartwork.R;
import com.zhuandian.smartwork.adapter.CommentAdapter;
import com.zhuandian.smartwork.base.BaseFragment;
import com.zhuandian.smartwork.entity.CommentEntity;
import com.zhuandian.smartwork.entity.PostEntity;
import com.zhuandian.smartwork.entity.UserEntity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;

/**
 * desc :
 * author：xiedong
 * date：2019/2/19
 */
public class DiscussFragment extends BaseFragment {


    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_content)
    TextView tvContent;
    @BindView(R.id.rv_common_list)
    RecyclerView rvCommonList;
    @BindView(R.id.post_container)
    LinearLayout postContainer;
    @BindView(R.id.et_comment)
    EditText etComment;
    @BindView(R.id.tv_submit)
    TextView tvSubmit;
    @BindView(R.id.ll_submit_comment)
    LinearLayout llSubmitComment;
    @BindView(R.id.tv_new_commont)
    TextView tvNewCommont;
    private String postObjectId;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_discuss;
    }

    @Override
    protected void initView() {
        getPostInfo();
        getAllCommentList();
    }

    private void getAllCommentList() {
        BmobQuery<CommentEntity> query = new BmobQuery<>();
        query.order("-createdAt")
                .findObjects(new FindListener<CommentEntity>() {
                    @Override
                    public void done(List<CommentEntity> list, BmobException e) {
                        rvCommonList.setAdapter(new CommentAdapter(list));
                        rvCommonList.setLayoutManager(new LinearLayoutManager(actitity));
                        rvCommonList.setVisibility(View.VISIBLE);
                        tvNewCommont.setVisibility(View.VISIBLE);
                    }
                });
    }

    private void getPostInfo() {
        BmobQuery<PostEntity> query = new BmobQuery<>();

        query.order("-createdAt")
                .findObjects(new FindListener<PostEntity>() {
                    @Override
                    public void done(List<PostEntity> list, BmobException e) {
                        if (e == null) {
                            tvContent.setText(list.get(0).getContent());
                            tvTitle.setText(list.get(0).getTitle());
                            postObjectId = list.get(0).getObjectId();

                        }
                    }
                });
    }


    @OnClick({R.id.tv_submit, R.id.tv_new_commont})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_submit:
                addNewComment();
                break;
            case R.id.tv_new_commont:
                llSubmitComment.setVisibility(View.VISIBLE);
                tvNewCommont.setVisibility(View.GONE);
                rvCommonList.setVisibility(View.GONE);
                break;
        }
    }

    private void addNewComment() {
        PostEntity postEntity = new PostEntity();
        postEntity.setObjectId(postObjectId);

        if (etComment.getText().toString().length() > 0) {
            CommentEntity commentEntity = new CommentEntity();
            commentEntity.setContent(etComment.getText().toString());
            commentEntity.setPost(postEntity);
            commentEntity.setUser(BmobUser.getCurrentUser(UserEntity.class));
            commentEntity.save(new SaveListener<String>() {
                @Override
                public void done(String objectId, BmobException e) {
                    if (e == null) {
                        Snackbar.make(tvTitle, "评论成功", Snackbar.LENGTH_LONG).show();
                        llSubmitComment.setVisibility(View.GONE);
                        etComment.setText(""); //清空输入框内容
                        getAllCommentList();
                    }
                }
            });
        } else {
            Snackbar.make(tvTitle, "评论内容不允许为空", Snackbar.LENGTH_LONG).show();
        }

    }
}
