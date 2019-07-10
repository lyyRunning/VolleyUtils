# VolleyUtils
这是一个简单封装的 Volley 项目
封装Volley
上图

Image
如何引用

	allprojects {
		repositories {
			...
			maven { url 'https://www.jitpack.io' }
		}
	}

dependencies {
	        compile 'com.github.JiangHaiYang01:VolleyUtil:1.0.0'
	}

get

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
                        textView.setText(response.getResultcode() + " " + response.getResult().getCity());
                    }

                    @Override
                    public void onError(String error) {
                        Log.e("TAG", "error---->" + error);
                        textView.setText("error--->" + error);
                    }
                });

post

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
                        textView.setText(response.getResultcode() + " " + response.getResult().getCity());
                    }

                    @Override
                    public void onError(String error) {
                        Log.e("TAG", "error---->" + error);
                        textView.setText("error--->" + error);
                    }
                });

img

VolleyUtils.create(this)
                .loadImg(imgUrl, img);
                
                
                

  VolleyUtils.create(this)
                .loadImg(imgUrl,img,200,200,R.mipmap.ic_launcher,R.mipmap.ic_launcher);

  
        VolleyUtils.create(this)
                .loadImg(imgUrl,img,R.mipmap.ic_launcher,R.mipmap.ic_launcher);
