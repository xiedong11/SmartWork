package com.zhuandian.smartwork.business;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import com.zhuandian.smartwork.R;
import com.zhuandian.smartwork.base.BaseActivity;
import com.zhuandian.smartwork.business.homefragment.MineFragment;
import com.zhuandian.smartwork.entity.UserEntity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.UpdateListener;

/**
 * desc :
 * author：xiedong
 * date：2019/2/21
 */
public class UpdataInfoActivity extends BaseActivity {
    @BindView(R.id.et_content)
    EditText etContent;
    @BindView(R.id.tv_update)
    TextView tvUpdate;
    private String info;
    private int type;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_updata_info;
    }

    @Override
    protected void setUpView() {
        info = getIntent().getStringExtra("data");
        type = getIntent().getIntExtra("type", 0);
        etContent.setText(info);

    }


    @OnClick(R.id.tv_update)
    public void onClick() {

        UserEntity userEntity = BmobUser.getCurrentUser(UserEntity.class);
        if (type == MineFragment.UPDATE_NICK_NAME) {
            userEntity.setNikeName(etContent.getText().toString());
        } else {
            userEntity.setUserInfo(etContent.getText().toString());
        }

        userEntity.update(new UpdateListener() {
            @Override
            public void done(BmobException e) {
                if (e == null) {
                    Intent intent = new Intent();
                    intent.putExtra("data",etContent.getText().toString());
                    setResult(type == MineFragment.UPDATE_NICK_NAME ? MineFragment.UPDATE_NICK_NAME : MineFragment.UPDATE_USER_INFO,intent);
                    finish();
                }
            }
        });
    }
}
