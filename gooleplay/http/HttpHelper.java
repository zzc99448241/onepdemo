package com.itheima95.gooleplay.http;

import android.text.TextUtils;

import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;

/**
 * Created by 99448 on 2016/11/15.
 */

public class HttpHelper {
    private HttpUtils httpUtils;
    private  static  HttpHelper mInstance = new HttpHelper();
    private  HttpHelper(){
        httpUtils=new HttpUtils();
    }
    public static HttpHelper create(){
        return  mInstance;
    }



    //执行get请求的方法
    public void get(final String url, final HttpCallback callback){
        String data = CacheManager.create().getCacheData(url);
        if (TextUtils.isEmpty(data)){
            //从网络请求数据
            requestDataFromNet(url, callback);
        }else{
            callback.onSuccess(data);
        }


    }
    //从网络请求数据
    private void requestDataFromNet(final String url, final HttpCallback callback) {

        httpUtils.send(HttpRequest.HttpMethod.GET, url, new RequestCallBack<String>() {


            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                String result = responseInfo.result;
                CacheManager.create().saveCacheData(url,result);
                callback.onSuccess(result);
            }

            @Override
            public void onFailure(HttpException error, String msg) {
                //将异常带给外界
                callback.onFail(error);
            }
        });



    }


    public interface HttpCallback{
        void onSuccess(String result);
        void onFail(Exception e);
    }












}
