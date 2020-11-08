package com.sys.appstudent.utils.okhttp;

import android.util.Log;

import com.alibaba.fastjson.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * @ProjectName: StudentApp
 * @Package: com.sys.studentapp.okhttp
 * @ClassName: OkHttpUtil
 * @Description: 请求封装
 * @Author: ©sys
 * @CreateDate: 2020/8/11 19:11
 * @Version: v1.0
 */
public class OkHttpUtil {
    public static final String MEDIA_TYPE = "application/json;charset=utf-8";
    private static final String METHOD_GET = "GET";
    private static final String METHOD_POST = "POST";
    private static OkHttpClient okHttpClient = null;
    /*
     * 私有化构造方法
     * 使用单例模式
     * */
    private OkHttpUtil() {
        System.out.println("init---okhttp");
        init();
    }
    public static void init() {
        if(okHttpClient == null) {
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            builder.readTimeout(3000, TimeUnit.MILLISECONDS);
            builder.writeTimeout(3000, TimeUnit.MILLISECONDS);
            builder.connectTimeout(3000, TimeUnit.MILLISECONDS);
            okHttpClient = builder.build();
        }
    }

    //处理传给的参数 并指明方法
    private static Request createRequest(String url, String method,Map<String, String> param) {
        // 拼接请求参数
        if (param != null && METHOD_GET.equals(method) && !param.isEmpty()) {
            StringBuffer buffer = new StringBuffer(url);
            if(buffer.toString().contains("?")){
                buffer.append("&") ;
            }else{
                buffer.append('?');
            }
            for (Map.Entry<String,String> entry: param.entrySet()) {
                buffer.append(entry.getKey());
                buffer.append('=');
                buffer.append(entry.getValue());
                buffer.append('&');
            }
            buffer.deleteCharAt(buffer.length()-1);
            url = buffer.toString();
        }

        RequestBody requestBody = null;
        if (METHOD_POST.equals(method)  && param != null) {
            FormBody.Builder formBody = new FormBody.Builder();
            if (!param.isEmpty()) {
                for (Map.Entry<String, String> entry : param.entrySet()) {
                    formBody.add(entry.getKey(), entry.getValue());
                }
            }
            requestBody = formBody.build();
        }
        Request.Builder builder = new Request.Builder().url(url);
        Request request = null;
        switch (method) {
            case METHOD_GET:
                request = builder.get().build();
                break;
            case METHOD_POST:
                request = builder.post(requestBody).build();
                break;
        }
        return request;
    }

    /**
     * 异步get请求
     *
     * @param url
     * @param callback
     */
    public static JSONObject getRequestSyn(String url, Map<String, String> param, Callback callback) {
        Request request = createRequest(url, METHOD_GET, param);
        Response response = null;
        JSONObject jsonObject = null;
        try {
            response = okHttpClient.newCall(request).execute();
            jsonObject = JSONObject.parseObject(response.body().string());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }
    public static void getRequestAsy(String url,Map<String, String> param,Callback callback) {
        Request request = createRequest(url, METHOD_GET, param);
        Call call = okHttpClient.newCall(request);
        call.enqueue(callback);
    }
    /**
     * 异步post请求
     *
     * @param url
     * @param param
     * @param callback
     */
    public static void postRequestAsy(String url,Map<String, String> param, Callback callback) {
        Request request = createRequest(url, METHOD_POST, param);
        Call call = okHttpClient.newCall(request);
        call.enqueue(callback);
    }
    public static JSONObject postRequestSyn(String url,Map<String, String> param, Callback callback) {
        Request request = createRequest(url, METHOD_POST, param);
        Response response = null;
        JSONObject jsonObject = null;
        try {
            response = okHttpClient.newCall(request).execute();
            jsonObject = JSONObject.parseObject(response.body().string());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }



    /**
     * 上传图片
     * @param url
     * @param imagePath 图片路径
     * @return 新图片的路径
     * @throws IOException
     */
    public static JSONObject uploadImage(String url, String imagePath) throws IOException {
        Log.d("imagePath", imagePath);
        File file = new File(imagePath);
        RequestBody image = RequestBody.create(MediaType.parse("image/png"), file);
        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("file", imagePath, image)
                .build();
        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();
        Response response = okHttpClient.newCall(request).execute();
        JSONObject jsonObject = JSONObject.parseObject(response.body().string());
        return jsonObject;
    }
}
