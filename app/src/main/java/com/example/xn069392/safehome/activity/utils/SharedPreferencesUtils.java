package com.example.xn069392.safehome.activity.utils;

import android.content.Context;
import android.content.SharedPreferences;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by z on 2017/11/16.
 */

public  class SharedPreferencesUtils {
    private SharedPreferencesUtils() {
    }

    private static SharedPreferencesUtils mInstance;

    public static SharedPreferencesUtils getInstance() {
        if (mInstance == null) {
            synchronized (SharedPreferencesUtils.class) {  //1
                if (mInstance == null)          //2
                    mInstance = new SharedPreferencesUtils();  //3
            }
        }
        return mInstance;

    }

    public static void putBoolean(Context context,boolean bol){
        SharedPreferences sp =  context.getSharedPreferences(Constants.AUTOUPDATE,MODE_PRIVATE);
        SharedPreferences.Editor edit =  sp.edit();
        edit.putBoolean(Constants.AUTOUOPEN,bol);
        edit.apply();
    }

    public static boolean getBoolean(Context context,String bol){
        SharedPreferences sp =  context.getSharedPreferences(Constants.AUTOUPDATE,MODE_PRIVATE);
       return sp.getBoolean(bol,true);
    }
}
