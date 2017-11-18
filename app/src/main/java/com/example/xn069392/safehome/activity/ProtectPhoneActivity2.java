package com.example.xn069392.safehome.activity;

import android.content.Intent;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.xn069392.safehome.R;
import com.example.xn069392.safehome.activity.utils.Constants;
import com.example.xn069392.safehome.activity.utils.SharedPreferencesUtils;

public class ProtectPhoneActivity2 extends ProtectPhoneBaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_protect_phone2);
        Button next = (Button) findViewById(R.id.next_button);
        Button  pre = (Button) findViewById(R.id.pro_button);
        Button  bind = (Button) findViewById(R.id.bind_SIM_card);
        next.setOnClickListener(this);
        pre.setOnClickListener(this);
        bind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TelephonyManager  tm = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
                String simSerialNumber = tm.getSimSerialNumber();
                SharedPreferencesUtils.setString(ProtectPhoneActivity2.this,simSerialNumber);
            }
        });
    }

    @Override
    protected boolean goin() {
        String passwordString = SharedPreferencesUtils.getString(this, Constants.SIMINFO);
        if(TextUtils.isEmpty(passwordString)){
            Toast.makeText(this,"先绑定了在搞",Toast.LENGTH_LONG).show();
        }else {
        }
        startActivity(new Intent(ProtectPhoneActivity2.this,ProtectPhoneActivity3.class));
        return true;
    }

    @Override
    protected boolean goout() {
        startActivity(new Intent(ProtectPhoneActivity2.this,ProtectPhoneActivity1.class));
        return true;
    }
}
