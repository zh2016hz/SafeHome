package com.example.xn069392.safehome.activity;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.xn069392.safehome.R;
import com.example.xn069392.safehome.activity.utils.SharedPreferencesUtils;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private ImageView mImage;
    private ImageView mSettingImage;
    private final static String[] TITLES =
            new String[]{"手机防盗", "骚扰拦截", "软件管家", "进程管理", "流量统计", "手机杀毒", "缓存清理", "常用工具"};
    private final static String[] DESCS = new String[]{"远程定位手机", "全面拦截骚扰", "管理您的软件", "管理运行进程",
            "流量一目了然", "病毒无处藏身", "系统快如火箭", "工具大全"};
    private final static int[] ICONS = new int[]{R.mipmap.sjfd, R.mipmap.srlj, R.mipmap.rjgj,
            R.mipmap.jcgl, R.mipmap.lltj, R.mipmap.sjsd, R.mipmap.sjsd, R.mipmap.cygj};
    private AlertDialog.Builder mBuilder;
    private AlertDialog mShow;
    private EditText mPass1;
    private EditText mPass;
    private String mPasswordString1;
    private EditText mViewById;
    private AlertDialog mShow1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mImage = (ImageView) findViewById(R.id.home_head_image);
        mSettingImage = (ImageView) findViewById(R.id.home_head_setting);
        GridView gridView = (GridView) findViewById(R.id.gradView);


        headerHorseAnimal();
        gridView.setOnItemClickListener(this);
        /** 设置*/
        mSettingImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, SettingActivity.class));
            }
        });
        GridViewAdapter mAdapter = new GridViewAdapter();
        gridView.setAdapter(mAdapter);

    }

    /**
     * 马儿的动画
     */
    private void headerHorseAnimal() {
        //动画
        ObjectAnimator rotationY = ObjectAnimator.ofFloat(mImage, "rotationY", 0, 45, 90, 135);
        rotationY.setDuration(500);
        rotationY.setRepeatCount(-1);
        rotationY.setRepeatMode(ValueAnimator.REVERSE);
//        rotationY.start();

        ObjectAnimator rotationX = ObjectAnimator.ofFloat(mImage, "rotationX", 0, 45, 90, 135);
        rotationX.setDuration(500);
        rotationX.setRepeatCount(-1);
        rotationX.setRepeatMode(ValueAnimator.REVERSE);
//        rotationX.start();

        AnimatorSet set = new AnimatorSet();
        set.playTogether(rotationX, rotationY);
        set.start();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
    switch (position){
        case 0:
            checkPassWord();
            break;
        case 1:
            break;
        case 2:
            break;

    }
    }

    /**
     * 检测是否设置密码
     */
    private void checkPassWord() {
        String passwordString = SharedPreferencesUtils.getPasswordString(this);
        if(TextUtils.isEmpty(passwordString)){
            //设置密码
            mBuilder = new AlertDialog.Builder(this);
            View inflate = View.inflate(MainActivity.this, R.layout.setting_password_dialog, null);
            mBuilder.setView(inflate);
            mShow = mBuilder.show();
            mPass = (EditText) inflate.findViewById(R.id.pass_word);
            mPass1 = (EditText) inflate.findViewById(R.id.pass_word1);
            Button  comfirBtn = (Button) inflate.findViewById(R.id.comfirm_byn_yes);
            Button  cancelBtn = (Button) inflate.findViewById(R.id.comfirm_byn_no);
            cancelBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mShow.dismiss();
                }
            });
            comfirBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Editable text = mPass.getText();
                    Editable text1 = mPass1.getText();
                    if(TextUtils.isEmpty(text)||TextUtils.isEmpty(text1)){
                        Toast.makeText(MainActivity.this,"不能为空",Toast.LENGTH_LONG).show();

                    }else if(!(text1.toString().equals(text.toString()))) {
                        Toast.makeText(MainActivity.this,"密码不同",Toast.LENGTH_LONG).show();
                    }else {
                        SharedPreferencesUtils.setPasswordString(MainActivity.this,text1.toString());
                        startActivity(new Intent(MainActivity.this,ProtectPhoneActivity1.class));
                    }
                }
            });




        }else{
            //输入密码
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            View inflate = View.inflate(MainActivity.this, R.layout.input_password_dialog1, null);
            builder.setView(inflate);
            mShow1 = builder.show();

            mViewById = (EditText) inflate.findViewById(R.id.pass_word_input);
            Button bt = (Button) inflate.findViewById(R.id.comfirm_byn_yes);
            mPasswordString1 = SharedPreferencesUtils.getPasswordString(MainActivity.this);
            bt.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   if(TextUtils.isEmpty(mPasswordString1)){
                       return;
                   }
                   if(mPasswordString1.equals(mViewById.getText().toString())){
                       startActivity(new Intent(MainActivity.this,ProtectPhoneActivity1.class));
                       mShow1.dismiss();
                   }else{
                       Toast.makeText(MainActivity.this,"密码错误",Toast.LENGTH_LONG).show();
                   }
               }
           });


        }
    }

    class GridViewAdapter extends BaseAdapter {

        private View mItemView;
        private ImageView mImages;
        private TextView mTitle;
        private TextView mDes;

        @Override
        public int getCount() {
            return TITLES.length;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                mItemView = View.inflate(MainActivity.this, R.layout.grid_view_item_layout, null);
                mImages = (ImageView) mItemView.findViewById(R.id.item_main_grid_iv_icon);
                mTitle = (TextView) mItemView.findViewById(R.id.item_main_grid_tv_title);
                mDes = (TextView) mItemView.findViewById(R.id.item_main_grid_tv_desc);
            } else {
                mItemView = convertView;
            }
            mImages.setImageResource(ICONS[position]);
            mTitle.setText(TITLES[position]);
            mDes.setText(DESCS[position]);

            return mItemView;
        }
    }
}
