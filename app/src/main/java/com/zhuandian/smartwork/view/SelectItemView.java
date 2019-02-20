package com.zhuandian.smartwork.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zhuandian.smartwork.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * desc :
 * author：xiedong
 * date：2019/2/20
 */
public class SelectItemView extends RelativeLayout {
    @BindView(R.id.tv_optinos_name)
    TextView tvOptinosName;
    @BindView(R.id.tv_optinos_content)
    TextView tvOptinosContent;

    public SelectItemView(Context context) {
        this(context, null);
    }

    public SelectItemView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SelectItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        View view = LayoutInflater.from(context).inflate(R.layout.view_select_item, this);
        ButterKnife.bind(this, view);
    }

    public SelectItemView setOptionsName(int optionsName) {
        char options = (char) (optionsName + 65);
        tvOptinosName.setText(Character.toString(options));
        return this;
    }

    public SelectItemView setOptionsContent(String optionsContent) {
        tvOptinosContent.setText(optionsContent);
        return this;
    }
}
