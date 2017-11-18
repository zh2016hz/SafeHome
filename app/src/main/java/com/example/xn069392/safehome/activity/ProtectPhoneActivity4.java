package com.example.xn069392.safehome.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.xn069392.safehome.R;

public class ProtectPhoneActivity4 extends ProtectPhoneBaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_protect_phone4);
        Button next = (Button) findViewById(R.id.next_button);
        Button  pre = (Button) findViewById(R.id.pro_button);
        next.setOnClickListener(this);
        pre.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.next_button:
                startActivity(new Intent(ProtectPhoneActivity4.this,ProtectPhoneActivity5.class));
                overridePendingTransition(R.anim.next_in,R.anim.next_out);
                break;
            case R.id.pro_button:
                startActivity(new Intent(ProtectPhoneActivity4.this,ProtectPhoneActivity3.class));
                overridePendingTransition(R.anim.pro_in,R.anim.pro_out);
                break;
        }
    }

    @Override
    protected boolean goin() {
        startActivity(new Intent(ProtectPhoneActivity4.this,ProtectPhoneActivity5.class));
        return true;
    }

    @Override
    protected boolean goout() {
        startActivity(new Intent(ProtectPhoneActivity4.this,ProtectPhoneActivity3.class));
        return true;
    }
}
