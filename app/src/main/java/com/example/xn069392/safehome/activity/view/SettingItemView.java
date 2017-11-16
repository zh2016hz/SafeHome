package com.example.xn069392.safehome.activity.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.xn069392.safehome.R;

/**
 * Created by z on 2017/11/16.
 */

public class SettingItemView extends RelativeLayout {
    private static final int TOP =0;
    private static final int MIDDLE =1;
    private static final int BOTTON =2;
    private ImageView mSwith;

    public SettingItemView(Context context) {
        this(context, null);
    }

    public SettingItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        View interView = View.inflate(context, R.layout.setting_layout_item, this);

        mSwith = (ImageView) interView.findViewById(R.id.setting_switch);
        TextView title = (TextView) interView.findViewById(R.id.setting_title);

        TypedArray typed = context.obtainStyledAttributes(attrs, R.styleable.settingView);
        String attrTitle = typed.getString(R.styleable.settingView_setting_title);
        boolean attrSwitch = typed.getBoolean(R.styleable.settingView_setting_showSwitch, false);
        int attrBackgtound = typed.getInt(R.styleable.settingView_setting_background, 0);

        title.setText(attrTitle);
        mSwith.setVisibility(attrSwitch?VISIBLE:INVISIBLE);
        switch (attrBackgtound){
            case TOP:
                setBackgroundResource(R.drawable.setting_background_top);
                break;
            case MIDDLE:
                setBackgroundResource(R.drawable.setting_background_middle);
                break;
            case BOTTON:
                setBackgroundResource(R.drawable.setting_background_button);
                break;
        }
        typed.recycle();
    }
    /**
    *点击开关
    * */
    public void setSwitchChange(boolean flag) {
        mSwith.setImageResource(flag?R.mipmap.on:R.mipmap.off);
    }
}
