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


    public static String getPasswordString(Context context) {
        SharedPreferences sp =  context.getSharedPreferences(Constants.PASSWORD,MODE_PRIVATE);
        String passWord = sp.getString(Constants.PASSWORDKEY, null);
        return passWord;
    }
    public static void setPasswordString(Context context,String password) {
        SharedPreferences sp =  context.getSharedPreferences(Constants.PASSWORD,MODE_PRIVATE);
        SharedPreferences.Editor edit = sp.edit();
        edit.putString(Constants.PASSWORDKEY,password);
        edit.apply();
    }

    public static String getString(Context context,String key) {
        SharedPreferences sp =  context.getSharedPreferences(Constants.PASSWORD,MODE_PRIVATE);
        String result = sp.getString(key, null);

        return result;
    }
    public static void setString(Context context,String password) {
        SharedPreferences sp =  context.getSharedPreferences(Constants.PASSWORD,MODE_PRIVATE);
        SharedPreferences.Editor edit = sp.edit();
        edit.putString(Constants.SIMINFO,password);
        edit.apply();
    }


    public static void setNumber(Context context,String password) {
        SharedPreferences sp =  context.getSharedPreferences(Constants.PASSWORD,MODE_PRIVATE);
        SharedPreferences.Editor edit = sp.edit();
        edit.putString(Constants.STORENUMBER,password);
        edit.apply();
    }
    public static String getNumber(Context context,String key) {
        SharedPreferences sp =  context.getSharedPreferences(Constants.PASSWORD,MODE_PRIVATE);
        String result = sp.getString(key, null);
        return result;
    }




    public static void setCheckout(Context context,boolean password) {
        SharedPreferences sp =  context.getSharedPreferences(Constants.PASSWORD,MODE_PRIVATE);
        SharedPreferences.Editor edit = sp.edit();
        edit.putBoolean(Constants.CHECKSTATE,password);
        edit.apply();
    }
    public static boolean getCheckout(Context context,String key) {
        SharedPreferences sp =  context.getSharedPreferences(Constants.PASSWORD,MODE_PRIVATE);
        boolean result = sp.getBoolean(key, false);

        return result;
    }
}
