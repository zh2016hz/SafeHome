package com.example.xn069392.safehome.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import com.example.xn069392.safehome.R;

/**
 * Created by z on 2017/11/18.
 */

public abstract class ProtectPhoneBaseActivity extends AppCompatActivity implements View.OnClickListener {

    private GestureDetector mGestureDetector;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mGestureDetector = new GestureDetector(this, new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                float rawX1 = e1.getRawX();
                float rawX2 = e2.getRawX();
                float rawY1 = e1.getRawY();
                float rawY2 = e2.getRawY();
                if (Math.abs(rawX1 - rawX2) > Math.abs(rawY1 - rawY2)) {
                    if (rawX1 > rawX2) {
                        if(goin()){
                        next();
                        }
                    } else {
                        if(goout()){
                        pro();
                        }
                    }
                }

                return super.onFling(e1, e2, velocityX, velocityY);
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.next_button:
                next();
                break;
            case R.id.pro_button:
                pro();
                break;
        }
    }

    private void pro() {
        goout();
        overridePendingTransition(R.anim.pro_in, R.anim.pro_out);
    }

    private void next() {
        goin();
        overridePendingTransition(R.anim.next_in, R.anim.next_out);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(mGestureDetector == null){
            return false;
        }
        mGestureDetector.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

    protected abstract boolean goin();

    protected abstract boolean goout();
}
