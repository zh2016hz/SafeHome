package com.example.xn069392.safehome.activity.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.util.Log;

import static android.content.ContentValues.TAG;

/**
 * Created by z on 2017/11/14.
 */

public class PackageUtils {

    private static int sVersionCode;

    /**
     * 获取版本信息
     *
     * @param context
     * @return
     */
    public static int getPackageVersion(Context context) {
        PackageManager packageManager = context.getPackageManager();
        PackageInfo packageInfo = null;
        try {
            packageInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
            Log.e(TAG, "getPackageVersion: " + packageInfo.applicationInfo);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        if (packageInfo != null) {
            sVersionCode = packageInfo.versionCode;
        }


        return sVersionCode;
    }
}
