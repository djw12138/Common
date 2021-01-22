package com.djw.controller;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

/**
 * @Author: dai jiawei
 * @Date: 2020/12/25 13:15
 */
@RestController(value = "/wx")
public class CmhkController {
    public static String SHA1(String str) {
        try {
            MessageDigest digest = MessageDigest
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

    //签名算法
    public static void main(String[] args) {
        String nonce="10000005001";
        String timestamp ="987654321";
        String appId  ="a0000001";
        String callerId  ="123456";
        String secret  ="07fe1dd804764f19b77d7b419f87090e";
        String[] signs= new String[5];
        signs[0]=nonce;
        signs[1]=timestamp;
        signs[2]=appId;
        signs[3]=callerId;
        signs[4]=secret;
        Arrays.sort(signs);
        System.out.println(SHA1(String.join("", signs)).toUpperCase());
    }
}
