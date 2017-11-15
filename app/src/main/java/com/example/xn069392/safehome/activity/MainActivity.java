package com.example.xn069392.safehome.activity;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.xn069392.safehome.R;

public class MainActivity extends AppCompatActivity {

    private ImageView mImage;
    private ImageView mSettingImage;
    private final static String[] TITLES =
            new String[]{"手机防盗", "骚扰拦截", "软件管家", "进程管理", "流量统计", "手机杀毒", "缓存清理", "常用工具"};
    private final static String[] DESCS = new String[]{"远程定位手机", "全面拦截骚扰", "管理您的软件", "管理运行进程",
            "流量一目了然", "病毒无处藏身", "系统快如火箭", "工具大全"};
    private final static int[] ICONS = new int[] {R.mipmap.sjfd, R.mipmap.srlj, R.mipmap.rjgj,
            R.mipmap.jcgl, R.mipmap.lltj, R.mipmap.sjsd, R.mipmap.sjsd, R.mipmap.cygj};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mImage = (ImageView) findViewById(R.id.home_head_image);
        mSettingImage = (ImageView) findViewById(R.id.home_head_setting);
        GridView gridView = (GridView) findViewById(R.id.gradView);


        headerHorseAnimal();
        /** 设置*/
        mSettingImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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
                mTitle = (TextView)mItemView.findViewById(R.id.item_main_grid_tv_title);
                mDes = (TextView)mItemView.findViewById(R.id.item_main_grid_tv_desc);
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
