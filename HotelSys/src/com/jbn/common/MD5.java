package com.jbn.common;

import java.util.Base64;
import java.util.Base64.Encoder;
import java.util.Base64.Decoder;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5 {

    public String getMD5(String str){
        String strMD5 = null;
        //确定计算方法
        MessageDigest md5 = null;
        try {
            md5 =MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        //jdk 9以上的版本 已经删除了base64n了
        Encoder encoder = Base64.getEncoder();
        Decoder decoder = Base64.getDecoder();

        try {
            strMD5=encoder.encode(md5.digest(str.getBytes("utf-8"))).toString();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return strMD5;
    }

    public boolean checkMD5(String newStr, String oldStr){return getMD5(newStr).equals(oldStr);}




}
