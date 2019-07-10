package com.function.luo.volleyutils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Map;

/**
 * Created by luo on 2019/7/10.
 */

public class TwoActivity extends Activity{

    private ImageView img;
    String urlStr = "http://apis.juhe.cn/mobile/get";

    String imgUrl = "https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=1263067444,118499721&fm=27&gp=0.jpg";
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two);
        img = findViewById(R.id.img);
        textView = findViewById(R.id.text);
    }

    public void btnGet(View view) {

        VolleyUtils.create(this)
                .get(urlStr, PhoneBean.class, new VolleyUtils.OnResponse<PhoneBean>() {
                    @Override
                    public void OnMap(Map<String, String> map) {
                        map.put("phone", "18856907654");
                        map.put("key", "5778e9d9cf089fc3b093b162036fc0e1");
                    }

                    @Override
                    public void onSuccess(PhoneBean response) {
                        Log.e("TAG", "response---->" + response);
                        textView.setText("get  :" + response.getResultcode() + " " + response.getResult().getCity());
                    }

                    @Override
                    public void onError(String error) {
                        Log.e("TAG", "error---->" + error);
                        textView.setText("error--->" + error);
                    }
                });
    }

    public void btnPost(View view) {
        VolleyUtils.create(this)
                .post(urlStr, PhoneBean.class, new VolleyUtils.OnResponse<PhoneBean>() {
                    @Override
                    public void OnMap(Map<String, String> map) {
                        map.put("phone", "18856907654");
                        map.put("key", "5778e9d9cf089fc3b093b162036fc0e1");
                    }

                    @Override
                    public void onSuccess(PhoneBean response) {
                        Log.e("TAG", "response---->" + response);
                        textView.setText("post  :" + response.getResultcode() + " " + response.getResult().getCity());
                    }

                    @Override
                    public void onError(String error) {
                        Log.e("TAG", "error---->" + error);
                        textView.setText("error--->" + error);
                    }
                });
    }

    public void btnImg(View view) {
        VolleyUtils.create(this)
                .loadImg(imgUrl, img);

//        VolleyUtils.create(this)
//                .loadImg(imgUrl,img,200,200,R.mipmap.ic_launcher,R.mipmap.ic_launcher);
//
//        VolleyUtils.create(this)
//                .loadImg(imgUrl,img,R.mipmap.ic_launcher,R.mipmap.ic_launcher);
    }

    public static void launch(Context mContext) {
        Intent intent = new Intent(mContext,TwoActivity.class);
        mContext.startActivity(intent);
    }
}
