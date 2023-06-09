package com.example.server.util;

import net.sf.json.JSONObject;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;

public class HttpClientUtil {
    public static String doGet(String url , HashMap<String , String> map) throws IOException {
        StringBuilder urlPath = new StringBuilder(url);
        //拼接参数
        if(map != null) {
            urlPath.append("?");
            map.forEach((k,v)-> {
                        try {
                            //这里的值需要编码
                            urlPath.append(k).append("=").append(URLEncoder.encode(v,"UTF-8")).append("&");
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }
                    }
            );
            //去掉最后一个&
            urlPath.deleteCharAt(urlPath.length() - 1);
        }

        BufferedReader reader = null;
        try {
            //创建连接
            System.out.println("请求路径为：" + urlPath);
            URL urlCon = new URL(urlPath.toString());
            HttpURLConnection httpCon = (HttpURLConnection) urlCon.openConnection();
            //设置连接的基本信息
            //请求方式为GET
            httpCon.setRequestMethod("GET");
            //设置通用的请求属性
            httpCon.setRequestProperty("accept", "*/*");
            httpCon.setRequestProperty("connection", "Keep-Alive");
            httpCon.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1)");
            httpCon.setRequestProperty("Content-Type", "application/json;charset=utf-8");
            //是否可读写
            httpCon.setDoOutput(true);
            httpCon.setDoInput(true);
            //是否使用缓存
            httpCon.setUseCaches(false);
            //设置连接超时30s
            httpCon.setConnectTimeout(30000);
            //设置读取响应超时30s
            httpCon.setReadTimeout(30000);
            //正式连接
            httpCon.connect();

            //获取调用接口返回信息 等待调用接口返回
            boolean sign = true;
            while (sign) {
                System.out.println(httpCon.getInputStream().available());
                if(httpCon.getInputStream().available() > 0) {
                    sign = false;
                } else {
                    //0.5s检查一次是否有执行返回
                    Thread.sleep(500);
                }
            }

            //返回结果集
            StringBuffer result = new StringBuffer();
            //读取返回结果集
            reader = new BufferedReader(new InputStreamReader(httpCon.getInputStream(),"utf-8"));
            String line = null;
            while ((line = reader.readLine()) != null){
                result.append(line);
            }
            reader.close();

            System.out.println("返回数据为：" + result);
            return result.toString();
        }catch (IOException exception) {
            exception.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            if(reader != null) reader.close();
        }
        return "遇到未知错误";
    }

}
