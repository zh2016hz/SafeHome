package com.example.xn069392.safehome.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import com.example.xn069392.safehome.R;
import com.example.xn069392.safehome.activity.utils.Constants;
import com.example.xn069392.safehome.activity.utils.SharedPreferencesUtils;

public class ProtectPhoneActivity5 extends ProtectPhoneBaseActivity {

    private CheckBox mCheckBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_protect_phone5);
        Button next = (Button) findViewById(R.id.next_button);
        Button pre = (Button) findViewById(R.id.pro_button);
        mCheckBox = (CheckBox) findViewById(R.id.check);
        next.setOnClickListener(this);
        pre.setOnClickListener(this);
        boolean checkout = SharedPreferencesUtils.getCheckout(this, Constants.CHECKSTATE);
        if (checkout) {
            mCheckBox.setChecked(true);
        }

    }


    @Override
    protected boolean goin() {
//        startActivity(new Intent(ProtectPhoneActivity5.this,ProtectPhoneActivity2.class));
//        finish();
        boolean checked = mCheckBox.isChecked();
        if (checked) {
            SharedPreferencesUtils.setCheckout(ProtectPhoneActivity5.this, true);
            startActivity(new Intent(ProtectPhoneActivity5.this,PhoneSafeActivity.class));
        } else {
            Toast.makeText(this, "先绑定了在搞", Toast.LENGTH_LONG).show();
        }

        return false;
    }

    @Override
    protected boolean goout() {
        startActivity(new Intent(ProtectPhoneActivity5.this, ProtectPhoneActivity4.class));
        return true;
    }
}
