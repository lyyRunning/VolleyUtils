package com.function.luo.volleyutils;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;


import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.android.volley.Response.*;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.bt_get)
    Button btGet;
    @BindView(R.id.bt_post)
    Button btPost;
    @BindView(R.id.bt_single_photo)
    Button btSinglePhoto;
    @BindView(R.id.bt_multiple_photo)
    Button btMultiplePhoto;
    @BindView(R.id.bt_json)
    Button bt_json;
    @BindView(R.id.iv_icon)
    ImageView ivIcon;
    private String tag = "get";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.bt_get, R.id.bt_post, R.id.bt_single_photo, R.id.bt_multiple_photo,R.id.bt_json})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_get:
                getRequest();
                break;
            case R.id.bt_post:
                postRequest();
                break;
            case R.id.bt_single_photo:
                singlePhoto();
                break;
            case R.id.bt_json:
                jsonRequest();
                break;
            case R.id.bt_multiple_photo:
               TwoActivity.launch(MainActivity.this);
                break;
            default:
        }
    }

    /**
     * jsonRequest
     */
    private void jsonRequest() {
        String postUrl = "http://apis.juhe.cn/mobile/get";
        Map<String, String> map = new HashMap<>();
        map.put("phone", "18856907654");
        map.put("key", "5778e9d9cf089fc3b093b162036fc0e1");

        String json = new Gson().toJson(map);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST,
                postUrl,json, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d("LUO", "========" + response);
            }
        },new Response.ErrorListener(){

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("LUO", "========" + error.getMessage());
            }
        });

        jsonObjectRequest.setTag(tag);
        MyApplication.getHttpQueues().add(jsonObjectRequest);
    }


    /**
     * get 请求
     */
    private void getRequest() {

        String url = "http://apis.juhe.cn/mobile/get?phone=18856907654&key=5778e9d9cf089fc3b093b162036fc0e1";


        /**
         * 1. int 类型 用于指定请求的方式（如GET或者POST）
         * 2. String类型 用于指定网络请求要连接的网址
         * 3. Listener类型 ,接收网络响应的接口，即只要得到本次请求对应的返回结果
         * 就会运行此接口中的onResponse方法
         * 4： ErrorListener类型， 用于接收当网络请求的过程中一旦发生了什么错误，
         * 就会调用本接口中的onErrorResponse方法
         * */
        StringRequest stringRequest = new StringRequest(StringRequest.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("LUO", "========" + response);
            }
        }, new ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("LUO", "========" + error.getMessage());
            }
        });

        //三，给请求对象设置tag标识
        stringRequest.setTag(tag);
        //四，将请求添加到请求队列中，执行网络请求
        MyApplication.getHttpQueues().add(stringRequest);
       // MyApplication.getHttpQueues().start();
    }


    /**
     * post 请求
     */
    private void postRequest() {

        RequestQueue queue = Volley.newRequestQueue(this);
        String postUrl = "http://apis.juhe.cn/mobile/get";

        StringRequest stringRequest = new StringRequest(StringRequest.Method.POST, postUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("LUO", "========" + response);

            }
        }, new ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("LUO", "========" + error.getMessage());
            }
        }) {
            /**
             * Post请求和Get请求的使用步骤上的区别在于请求条件的指定
             * 必须在StringRequest对象的后面添加{}，并且
             * 在{}内重写getParams方法，该方法的返回值就是所有的请求条件
             * */
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                //将请求条件封装到map对象中
                Map<String, String> map = new HashMap<>();
                map.put("phone", "18856907654");
                map.put("key", "5778e9d9cf089fc3b093b162036fc0e1");
                return map;
            }
        };

        //三，给请求对象设置tag标识
        stringRequest.setTag(tag);
        //四，将请求添加到请求队列中，执行网络请求
        MyApplication.getHttpQueues().add(stringRequest);
    }


    /**
     * 下载单张图片
     */
    private void singlePhoto() {

        ImageRequest imageRequest = new ImageRequest(
                "https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=2019270811,1269730008&fm=27&gp=0.jpg", //对应图片的下载地址
                new Response.Listener<Bitmap>() {
                    @Override
                    public void onResponse(Bitmap response) {
                        Log.d("LUO", "========" + response);
                        ivIcon.setImageBitmap(response);
                    }
                }, 200, 200,// 指定下载后图片的最大宽高
                ImageView.ScaleType.FIT_XY,//指定图片的缩放模式
                Bitmap.Config.ARGB_8888,//指定图片的编码格式
                new ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("LUO", "========" + error.getMessage());
                    }
                });
        MyApplication.getHttpQueues().add(imageRequest);

    }


    /**
     * 与Avctivity生命周期联动
     * 其实就是在Activity退出时候或销毁时候，取消对应的网络请求，避免网络请求在后台浪费资源，
     * 所以，我们一般在onStop()方法中通过之前设置的Tag取消网络请求：
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        //通过Tag标签取消请求队列中对应的全部请求
        MyApplication.getHttpQueues().cancelAll(tag);
    }
}
