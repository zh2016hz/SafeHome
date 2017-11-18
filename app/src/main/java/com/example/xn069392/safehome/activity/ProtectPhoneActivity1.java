package com.example.xn069392.safehome.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.example.xn069392.safehome.R;

public class ProtectPhoneActivity1 extends ProtectPhoneBaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_protect_phone);
        Button next = (Button) findViewById(R.id.next_button);
        Button pre = (Button) findViewById(R.id.pro_button);
        next.setOnClickListener(this);
        pre.setOnClickListener(this);
    }


    @Override
    protected boolean goin() {
        startActivity(new Intent(ProtectPhoneActivity1.this, ProtectPhoneActivity2.class));
        return true;
    }

    @Override
    protected boolean goout() {
        finish();
        return false;
    }
}
