package com.function.luo.volleyutils;

import android.app.Application;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;



/**
 * Created by luo on 2019/7/10.
 */

public class MyApplication extends Application {
    public static RequestQueue queue;


    @Override
    public void onCreate() {
        super.onCreate();
        queue = Volley.newRequestQueue(getApplicationContext());
    }

    /**
     * 提供全局变量，如果使用 VolleyUtils 类，这里就不用写了
     * 创建请求队列对象，通常情况下，一个类中（或者说一个工程中）保持 始终使用同一个RequestQueue对象即可
     * @return
     */
    public static RequestQueue getHttpQueues() {
        return queue;
    }
}
