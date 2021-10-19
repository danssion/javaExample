/**
 * \* Created with IntelliJ IDEA.
 * \* Name: LogicTest
 * \* User: danssion
 * \* Date: 2019/5/8
 * \* Time: 16:35
 * \*
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */


package com.danssion.java.encrypt;

import org.junit.Test;

import javax.crypto.Cipher;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

public class RSAExp {
    private static String src = "rsa security";
    private static final String base64PublicKey =
            "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAhKT9tRt5Y6WHJ3qoRy/ct0N0RsFX6R7Orj3j12EHjbEzztQYUplKiH5DZcunhzR" +
                    "DoVufgSEa+nY0tkOjB7azzMAXpB1Ju6U6DNKkbUQwkiFHzumB3AqzKabBbLgB0c4PrZrKt46CoFfrnygF/xDBL7EnxSGg2GWdn1" +
                    "JQD7Fp6cDsFr0gOZwlDA1jgXAt+jUovaoFmt1/+jzx5+otj/w4Vfk5QmyGUhyKXv78F67LtcYl3mYdEMY9727h3YfRFHOqV5xnr" +
                    "NuzV3oAU3ZtD0Fg6EFZGZ2ID8BIIt6+ml+BuafVViaNSxzy22eiRXhnuKqtZ8G083mN3ETJXIzapPB25wIDAQAB";
    private static final String base64PrivateKey = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCEpP21G3ljpYcneqhHL9y3Q3RGwVfpHs6uPePXYQeNsTPO1BhSmUqIfkNly6eHNEOhW5+BIRr6djS2Q6MHtrPMwBekHUm7pToM0qRtRDCSIUfO6YHcCrMppsFsuAHRzg+tmsq3joKgV+ufKAX/EMEvsSfFIaDYZZ2fUlAPsWnpwOwWvSA5nCUMDWOBcC36NSi9qgWa3X/6PPHn6i2P/DhV+TlCbIZSHIpe/vwXrsu1xiXeZh0Qxj3vbuHdh9EUc6pXnGes27NXegBTdm0PQWDoQVkZnYgPwEgi3r6aX4G5p9VWJo1LHPLbZ6JFeGe4qq1nwbTzeY3cRMlcjNqk8HbnAgMBAAECggEAHMuAdBdxw6sEaxKag1GllXckP9PrFKVmcrbmxjO/IwhL2eN7IDs8zeKzGN8cECLNZ7LvLmYUaDjO+p42Pw9vesxclXjyL7UPpAb6QOojrSViZE2WrkD4YdtcdBCB/JzCFIrc5Pof61UKxnM20gc+GO9ZVdcWBsQQcqy4yp9WAbNabzkQkT673Hc3w15gsibh0Q0f2fPcLcxtxfI4NZqKvFFnStzvbVmcyQFLYosJNOALkVwnF+8AboCkO1G/Co88mwAALRpkSQdNgqIqydn4Dl7EBrZEflwJvFeOmriTNlAm+VYAmiJAfxTG8srW+fv945JUhDXFp1/aQAvTxW9biQKBgQDKmJgNCkn22cVfq2LZVAMWGud3IZJ/QL+ctG7arSpcaQdjYiGYelyhMM3SZgboEKBceCwt8WHuaHrc8eZJoOEHGlM4KB0NFve5mczEIrJh3cmBfrmjMDghcUqEg1R/bqRDVlXyN6nv9cn2gXKim+U8QXEJs0yTqHYSlBtNoJRl7QKBgQCnm/zhVgVFupoioZjfZByEQDVJqd0lue2x3Pwjw1W2cllvBJUYTanP1XZg65tVfpT/13w7z7Hk3i/DkSz+Z2wAbQNP2g/UxtvyzXY+WHRnITvTQojb4gX/yK2QONm068iLgz4+egR2jlu4+yRPU75M+MAfNkYZb+8lVvRMV4+1owKBgQCL3FojrnWvR1l9mTiNZfUXfo1Kbc/Pr6hYCyv4JNF4qD0ke+tLczWdRa7hT/P2ovvBumhtcxUzW8dt3kUnkTuoBqFqxC6CCdgMtkNpJ7bT3yOsFQscwD4PwEE8R9m+e2hE4EZHB+x2Ls01MtorrcnMFjVuB+yWhxaz7rEGnxOzZQKBgDoOAa1bSkA4viK4rmbSGj6ErvBw8bSbjgVJcdxBUHL9cJTPlYTxgZwHaBVyBj+6tIlZbiKZchjADPNEWb1UvUf4emkyMsGUj6KuIALASLFgBYTLSvQ+ea17KQMHbgEImlIYh3rEcL9P191ev9jqPMQUdRdaiTVOXBsm3z+CJLjZAoGBAKkFawHZPJpTuM3kpR6kzcSX0Ol0C3vOo6qyxaUBD7f1RGvBZumhB3eDq4zIRGzph8j79Notaua3fRZxeLrMZ2ExymFVx//u2ITMu4cF2stg+g3l6XXdyLYycbnVtd1eslHo6L11DuuHuWXpu80kYZdg/nAa+zb7Ae6n5zbzgZFl";
    private static final String ALGORITHM = "RSA";
    private static final String SIGN_ALGORITHMS = "SHA1withRSA";

    public static void main(String[] args) {
//        generateKey();
        jdkRSA();
    }

    public static void jdkRSA(){
        try {
            //===============生成公钥和私钥，公钥传给客户端，私钥服务端保留==================
            String publicKeyStr = base64PublicKey;
            String privateKeyStr = base64PrivateKey;
            //=================客户端=================
            //hello, i am infi, good night!加密
            String message = "hello, i am infi, good night!";
            //将Base64编码后的公钥转换成PublicKey对象
            PublicKey publicKey = RSAUtil.string2PublicKey(publicKeyStr);
            //用公钥加密
            byte[] publicEncrypt = RSAUtil.publicEncrypt(message.getBytes(), publicKey);
            //加密后的内容Base64编码
            String byte2Base64 = RSAUtil.byte2Base64(publicEncrypt);
            System.out.println("公钥加密并Base64编码的结果：" + byte2Base64);


            //##############    网络上传输的内容有Base64编码后的公钥 和 Base64编码后的公钥加密的内容     #################

            //===================服务端================
            //将Base64编码后的私钥转换成PrivateKey对象
            PrivateKey privateKey = RSAUtil.string2PrivateKey(privateKeyStr);
            //加密后的内容Base64解码
            byte[] base642Byte = RSAUtil.base642Byte(byte2Base64);
            //用私钥解密
            byte[] privateDecrypt = RSAUtil.privateDecrypt(base642Byte, privateKey);
            //解密后的明文
            System.out.println("解密后的明文: " + new String(privateDecrypt));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void generateKey(){
        //生成RSA公钥和私钥，并Base64编码
        KeyPair keyPair = null;
        try {
            keyPair = RSAUtil.getKeyPair();
        } catch (Exception e) {
            e.printStackTrace();
        }
        String publicKeyStr = RSAUtil.getPublicKey(keyPair);
        String privateKeyStr = RSAUtil.getPrivateKey(keyPair);
        System.out.println("RSA公钥Base64编码:" + publicKeyStr);
        System.out.println("RSA私钥Base64编码:" + privateKeyStr);
    }



}
