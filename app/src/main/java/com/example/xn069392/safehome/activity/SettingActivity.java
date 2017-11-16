package com.example.xn069392.safehome.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.xn069392.safehome.R;
import com.example.xn069392.safehome.activity.utils.Constants;
import com.example.xn069392.safehome.activity.utils.SharedPreferencesUtils;
import com.example.xn069392.safehome.activity.view.SettingItemView;

public class SettingActivity extends AppCompatActivity implements View.OnClickListener {

    private SettingItemView mSaoRao;
    private SettingItemView mAutoUpdate;
    boolean flag =true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_actvity);
        initView();
        initEvent();
    }

    /**
     * 处理事件
     */
    private void initEvent() {
        mSaoRao.setOnClickListener(this);
        mAutoUpdate.setOnClickListener(this);
    }

    /**
     * 初始化View
     */
    private void initView() {
        mSaoRao = (SettingItemView) findViewById(R.id.stop_sao_rao);//自动更新
        mAutoUpdate = (SettingItemView) findViewById(R.id.auto_update);//骚扰拦截
        boolean is =SharedPreferencesUtils.getBoolean(this,Constants.AUTOUOPEN);
        mAutoUpdate.setSwitchChange(is);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.stop_sao_rao:
                break;
            case R.id.auto_update:
                setSwitch();
                break;
        }
    }
    /**
     * 更改开关状态
     */
    private void setSwitch() {
        mAutoUpdate.setSwitchChange(!flag);
        flag=!flag;
        SharedPreferencesUtils.putBoolean(this,flag);
    }
}
