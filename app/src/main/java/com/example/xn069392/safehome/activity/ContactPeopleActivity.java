package com.example.xn069392.safehome.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.xn069392.safehome.R;

import java.lang.reflect.Method;
import java.util.ArrayList;

public class ContactPeopleActivity extends AppCompatActivity {

    private ListView mListView;
    private ArrayList<String> mAl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_people_actcivity);
        mListView = (ListView) findViewById(R.id.list_view);
        initData();
//        reflect();

        ContactAdapter contactAdapter = new ContactAdapter();
        mListView.setAdapter(contactAdapter);
    }

    /**
     * 反射
     */
    private void reflect() {
        try {
            Class<?> aClass = Class.forName("java.util.ArrayList");
            Method add = aClass.getMethod("add", Object.class);
            add.invoke(mAl, 12134);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 造点假数据
     */
    private void initData() {
        mAl = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            mAl.add("我是第 " + i + " 个联系人");
        }
    }

    class ContactAdapter extends BaseAdapter {

        private View mView;
        private TextView mPeopleName;

        @Override
        public int getCount() {
            return mAl.size();
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
        public View getView(final int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if (convertView == null) {
                holder = new ViewHolder();
                mView = View.inflate(ContactPeopleActivity.this, R.layout.contactitem, null);
                holder.peopleName = (TextView) mView.findViewById(R.id.people_name);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            holder.peopleName.setText(mAl.get(position)+"");


            mPeopleName.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String s = mAl.get(position);
                    Intent intent = new Intent();
                    intent.putExtra("contactInfo", s);
                    setResult(RESULT_OK, intent);
                    finish();
                }
            });

            return mView;
        }
    }
    static  class ViewHolder{
        ImageView iamgeView;
        TextView  peopleName;
    }
}
