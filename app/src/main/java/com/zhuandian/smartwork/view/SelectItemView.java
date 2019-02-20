package com.zhuandian.smartwork.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.zhuandian.smartwork.R;

import butterknife.ButterKnife;

/**
 * desc :
 * author：xiedong
 * date：2019/2/20
 */
public class SelectItemView extends LinearLayout {
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
}
