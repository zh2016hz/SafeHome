package com.example.xn069392.safehome.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.xn069392.safehome.R;
import com.example.xn069392.safehome.activity.utils.SharedPreferencesUtils;

public class ProtectPhoneActivity3 extends ProtectPhoneBaseActivity {

    private Button mMore;
    private EditText mEt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_protect_phone3);
        Button next = (Button) findViewById(R.id.next_button);
        Button  pre = (Button) findViewById(R.id.pro_button);
        mMore = (Button) findViewById(R.id.bind_SIM_card_btn);
        mEt = (EditText) findViewById(R.id.edit_layout);
        next.setOnClickListener(this);
        pre.setOnClickListener(this);
        mMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(ProtectPhoneActivity3.this,ContactPeopleActivity.class),666);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
            if(requestCode == 666){
                if(resultCode == RESULT_OK){
                    final String contactInfo = data.getStringExtra("contactInfo");
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                    mEt.setText(contactInfo);

                        }
                    });
                }
            }
    }



//    @Override
//    protected void onRestart() {
//        super.onRestart();
//        String number = SharedPreferencesUtils.getNumber(this, Constants.STORENUMBER);
//        if(TextUtils.isEmpty(number)){
//            return;
//        }
//        mEt.setText(number);
//        mEt.setSelection(number.length());
//    }

    @Override
    protected boolean goin() {
        String trim = mEt.getText().toString().trim();
        if(TextUtils.isEmpty(trim)){
            Toast.makeText(this,"先绑定了在搞",Toast.LENGTH_LONG).show();
        }else {
            SharedPreferencesUtils.setNumber(this,trim);
        startActivity(new Intent(ProtectPhoneActivity3.this,ProtectPhoneActivity4.class));
        }
        return true;
    }
    @Override
    protected boolean goout() {
        startActivity(new Intent(ProtectPhoneActivity3.this,ProtectPhoneActivity2.class));
        return true;
    }
}
