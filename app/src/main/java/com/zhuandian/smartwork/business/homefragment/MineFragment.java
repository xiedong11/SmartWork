package com.zhuandian.smartwork.business.homefragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zhuandian.smartwork.R;
import com.zhuandian.smartwork.base.BaseFragment;
import com.zhuandian.smartwork.business.UpdataInfoActivity;
import com.zhuandian.smartwork.entity.UserEntity;
import com.zhuandian.smartwork.view.CircleImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import cn.bmob.v3.BmobUser;

/**
 * desc :
 * author：xiedong
 * date：2019/2/19
 */
public class MineFragment extends BaseFragment {


    @BindView(R.id.civ_header)
    CircleImageView civHeader;
    @BindView(R.id.tv_user_name)
    TextView tvUserName;
    @BindView(R.id.tv_user_major)
    TextView tvUserMajor;
    private UserEntity currentUser;
    public static final int UPDATE_NICK_NAME = 1;
    public static final int UPDATE_USER_INFO = 2;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_mine;
    }

    @Override
    protected void initView() {
        currentUser = BmobUser.getCurrentUser(UserEntity.class);
        tvUserName.setText(currentUser.getNikeName() == null ? "暂未设置" : currentUser.getNikeName());
        tvUserMajor.setText(currentUser.getUserInfo() == null ? "暂未设置" : currentUser.getUserInfo());
    }


    @OnClick({R.id.civ_header, R.id.tv_user_name, R.id.tv_user_major})
    public void onClick(View view) {
        Intent intent = new Intent(actitity,UpdataInfoActivity.class);
        switch (view.getId()) {
            case R.id.civ_header:
                break;
            case R.id.tv_user_name:
                intent.putExtra("data", currentUser.getNikeName() == null ? "暂未设置" : currentUser.getNikeName());
                intent.putExtra("type",UPDATE_NICK_NAME);
                startActivityForResult(intent, UPDATE_NICK_NAME);
                break;
            case R.id.tv_user_major:
                intent.putExtra("data", currentUser.getUserInfo() == null ? "暂未设置" : currentUser.getUserInfo());
                intent.putExtra("type",UPDATE_USER_INFO);
                startActivityForResult(intent, UPDATE_USER_INFO);
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (resultCode) {
            case UPDATE_NICK_NAME:
                tvUserName.setText(((String) data.getExtras().get("data")));
                break;
            case UPDATE_USER_INFO:
                tvUserMajor.setText(((String) data.getExtras().get("data")));
                break;
        }


    }
}
