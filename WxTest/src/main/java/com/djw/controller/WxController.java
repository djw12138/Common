package com.djw.controller;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.springframework.web.bind.annotation.*;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

/**
 * @Author: dai jiawei
 * @Date: 2020/12/25 13:15
 */
@RestController(value = "/wx")
public class WxController {
    private static final String APP_ID="wxe446729b758305e1";//微信公众号的appid
    private static final String APP_SECRET="a6dd833db3b6f77c0c8ff6f0af9a9388";//微信公众号的appsecret
    public String getAccessToken(String appId , String appSecret){
        // 网页授权接口
        String GetPageAccessTokenUrl = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="+appId+"&secret="+appSecret;

        HttpClient client = null;
        String access_token = null;
        int expires_in = 0;
        try {
            client = new DefaultHttpClient();
            HttpGet httpget = new HttpGet(GetPageAccessTokenUrl);
            ResponseHandler<String> responseHandler = new BasicResponseHandler();
            String response = client.execute(httpget, responseHandler);
            JSONObject OpenidJSONO = JSONObject.parseObject(response);
            access_token = String.valueOf(OpenidJSONO.get("access_token"));//获取access_token
            expires_in = Integer.parseInt(String.valueOf(OpenidJSONO.get("expires_in")));//获取时间
        } catch (Exception e) {
            throw new RuntimeException("获取AccessToken出错！");
        } finally {
            client.getConnectionManager().shutdown();
        }
        return access_token;
    }

    public String getTicket(String accessToken) {
        // 网页授权接口
        String GetPageAccessTokenUrl = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token="+accessToken+"&type=jsapi";
        HttpClient client = null;
        String ticket = "";
        int expires_in = 0;
        try {
            client = new DefaultHttpClient();
            HttpGet httpget = new HttpGet(GetPageAccessTokenUrl);
            ResponseHandler<String> responseHandler = new BasicResponseHandler();
            String response = client.execute(httpget, responseHandler);
            JSONObject OpenidJSONO = JSONObject.parseObject(response);
            ticket = String.valueOf(OpenidJSONO.get("ticket"));//获取ticket
            expires_in = Integer.parseInt(String.valueOf(OpenidJSONO.get("expires_in")));//获取时间
        } catch (Exception e) {
            throw new RuntimeException("获取Ticket出错！");
        } finally {
            client.getConnectionManager().shutdown();
        }

        return ticket;
    }

    public String SHA1(String str) {
        try {
            MessageDigest digest = java.security.MessageDigest
                    .getInstance("SHA-1"); //如果是SHA加密只需要将"SHA-1"改成"SHA"即可
            digest.update(str.getBytes());
            byte messageDigest[] = digest.digest();
            // Create Hex String
            StringBuffer hexStr = new StringBuffer();
            // 字节数组转换为 十六进制 数
            for (int i = 0; i < messageDigest.length; i++) {
                String shaHex = Integer.toHexString(messageDigest[i] & 0xFF);
                if (shaHex.length() < 2) {
                    hexStr.append(0);
                }
                hexStr.append(shaHex);
            }
            return hexStr.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    @ResponseBody
    @PostMapping(value = "/getSignature")
    public JSONObject getSignature(@RequestParam(value = "url") String url) {
        //获取noncestr
        String nonceStr = UUID.randomUUID().toString();
       //获取timestamp
        String timestamp = Long.toString(System.currentTimeMillis() / 1000);
        //获取access_token
        String access_token = getAccessToken(APP_ID , APP_SECRET);
        //获取jspai_ticket
        String jsapi_ticket = getTicket(access_token);
        //将四个数据进行组合，传给SHA1进行加密
        String str = "jsapi_ticket=" + jsapi_ticket +
                "&noncestr=" + nonceStr +
                "&timestamp=" + timestamp +
                "&url=" + url;

        //sha1加密
        String signature = SHA1(str);

        JSONObject wxConfig = new JSONObject();
        wxConfig.put("appId",APP_ID);
        wxConfig.put("nonceStr",nonceStr);
        wxConfig.put("timestamp",timestamp);
        wxConfig.put("signature",signature);
        return wxConfig;
    }
}
