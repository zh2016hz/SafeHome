package com.example.xn069392.safehome.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.example.xn069392.safehome.R;
import com.example.xn069392.safehome.activity.utils.PackageUtils;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class SplashActivity extends AppCompatActivity {

    private static final int NEED_UPDATE = 100;
    private static final int NO_NEED_UPDATE = 200;
    // 连接失败
    private static final int MSG_WHAT_CONN_FAIL = 101;
    // 标记安装新应用的标志位
    private static final int REQ_CODE_INSTALL_APP = 100;
    private TextView mVersionCode;
    private Handler mHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            switch (msg.what) {
                case NEED_UPDATE:
                    alertUpdateDialog();
                    break;
                case NO_NEED_UPDATE:

                    break;
                case MSG_WHAT_CONN_FAIL:
                    // 连接失败,弹出提示,开启主界面
                    Toast.makeText(SplashActivity.this, "连接失败, 请参考错误码101",
                            Toast.LENGTH_SHORT).show();
                    loadMainActivity();
                    break;
            }
            return false;
        }
    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_splash);
        initView();
        loadMainActivity();
        checkUpdate();
    }

    /**
     * 检查服务器版本
     */
    private void checkUpdate() {
        new Thread(new Runnable() {
            private String mDownloadURL;
            private String mUpdateTips;
            private int mVersion;
            private BufferedReader mReader;
            private InputStream mMIn;

            @Override
            public void run() {
                URL url = null;
                try {
                    url = new URL("http://blog.csdn.net/shakespeare001/article/details/7779011");
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
                try {
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    con.setReadTimeout(5000);
                    con.setConnectTimeout(5000);
                    String result = "";
                    if (con.getResponseCode() == 200) {
                        mMIn = con.getInputStream();
                        mReader = new BufferedReader(new InputStreamReader(mMIn));
                        byte[] bytes = new byte[1024];
                        String buffer;
                        while ((buffer = mReader.readLine()) != null) {
                            result += buffer;
                        }
                    }

//                    JSONObject jsonObject = null;
//                    try {
//                        jsonObject = new JSONObject(result);
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                    }
                    try {
//                        mVersion = jsonObject.getInt("version");
                        mVersion = 1;
                        // 更新提示内容
//                        mUpdateTips = jsonObject.getString("desc");
                        // 新APK下载路径
//                        mDownloadURL = jsonObject.getString("url");

                        if (mVersion > PackageUtils.getPackageVersion(SplashActivity.this)) {
                            //需要会更新
                            Message message = new Message();
                            message.what = NEED_UPDATE;
                            mHandler.sendMessage(message);
                        } else {
                            //不要更新
                            loadMainActivity();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    closeStream(mReader);
                    closeStream(mMIn);
                }
            }
        }).start();

    }

    // 弹出一个更新的提示框
    private void alertUpdateDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(
                SplashActivity.this);
        builder.setTitle("更新提示");
//        builder.setMessage(mUpdateTips);
        builder.setNegativeButton("稍后再说", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // 跳至主界面
                loadMainActivity();
                // 隐藏掉提示框
                dialog.dismiss();
            }
        });
        builder.setPositiveButton("立刻升级", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // 创建一个进度条
                ProgressDialog pd = new ProgressDialog(SplashActivity.this);
                pd.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                // 不可取消
                pd.setCancelable(false);
                pd.setCanceledOnTouchOutside(false);
                // 显示进度条
                pd.show();
                // 开始下载
                new Thread(new DownloadRunnable(pd)).start();
            }
        });

        AlertDialog dialog = builder.show();
        // 不可取消
        dialog.setCancelable(false);
        // 点击Dialog外部的时候也不可取消
        dialog.setCanceledOnTouchOutside(false);
    }

    class DownloadRunnable implements Runnable {
        private ProgressDialog pd;

        public DownloadRunnable(ProgressDialog pd) {
            this.pd = pd;
        }

        @Override
        public void run() {
            FileOutputStream fos = null;
            InputStream inputStream = null;
            try {
                URL url = new URL("XXXXXX");
                HttpURLConnection connection = (HttpURLConnection) url
                        .openConnection();
                connection.setConnectTimeout(5000);
                connection.setReadTimeout(5000);
                connection.connect();
                // 返回的结果的长度
                int contentLength = connection.getContentLength();
                // 设置进度条的最大值
                pd.setMax(contentLength);
                // 当前进度
                int current = 0;
                // 创建下载的文件
                File file = new File(Environment.getExternalStorageDirectory(),
                        "safe.apk");
                if (connection.getResponseCode() == 200) {
                    // 获取输入流
                    inputStream = connection.getInputStream();
                    // 创建文件输出流
                    fos = new FileOutputStream(file);
                    // 缓存对象
                    byte[] buffer = new byte[1024];
                    // 读取到的长度
                    int len;
                    // 读取数据
                    while ((len = inputStream.read(buffer)) != -1) {
                        fos.write(buffer, 0, len);
                        current += len;
                        // 设置进度条的当前值
                        pd.setProgress(current);
                    }
                    // 隐藏进度条
                    pd.dismiss();
                    // 下载完成后,激活隐式意图,安装APK
                    Intent intent = new Intent();
                    intent.setAction("android.intent.action.VIEW");
                    intent.addCategory("android.intent.category.DEFAULT");
                    intent.setDataAndType(
                            Uri.parse("file:" + file.getAbsolutePath()),
                            "application/vnd.android.package-archive");
                    // 启动下一个页面, 并获取他的返回值
                    // requestCode : 标记谁发起的强求
                    startActivityForResult(intent, REQ_CODE_INSTALL_APP);
                } else {
                    mHandler.sendEmptyMessage(MSG_WHAT_CONN_FAIL);
                }
            } catch (MalformedURLException e) {
                mHandler.sendEmptyMessage(MSG_WHAT_CONN_FAIL);
                e.printStackTrace();
            } catch (IOException e) {
                mHandler.sendEmptyMessage(MSG_WHAT_CONN_FAIL);
                e.printStackTrace();
            } finally {
                // 关流
                closeStream(fos);
                closeStream(inputStream);
            }
        }

    }

    // 关流
    private void closeStream(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            closeable = null;
        }
    }

    /**
     * 初始化
     */
    private void initView() {
        mVersionCode = (TextView) findViewById(R.id.version_code);
        mVersionCode.setText("版本：" + PackageUtils.getPackageVersion(this));
    }

    private void loadMainActivity() {
        // 延时进入主界面
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        }, 1500);
    }

    /**
     * @param requestCode : 请求码
     * @param resultCode  : 结果码.定义请求是否成功.Activity.RESULT_OK:代表请求成功.Activity.
     *                    RESULT_CANCELED:请求失败了.
     * @param data        : 返回的其他信息
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        switch (requestCode) {
            case REQ_CODE_INSTALL_APP:
                if (resultCode == Activity.RESULT_CANCELED) {
                    // 如果用户取消了安装,直接进入主界面
                    loadMainActivity();
                }
                break;
        }
    }
}
