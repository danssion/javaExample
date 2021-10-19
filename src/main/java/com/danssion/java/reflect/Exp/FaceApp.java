package com.danssion.java.reflect.Exp;

/**
 * @author danssion danssion@sina.com
 * @version 1.0
 * @date 2020/7/10 21:02
 * @desc
 */
public class FaceApp {
    public static void main(String[] args) {
//        String configStr = "读取配置，走阿里云还是腾讯云";
        String configStr = "com.danssion.java.reflect.Exp.TXFaceRecognition";
        FaceRecognitionInterface faceRe = null;
//        try {
//            faceRe = Class.forName("com.danssion.java.reflect.Exp.TXFaceRecognition").newInstance();
//        } catch (InstantiationException e) {
//            e.printStackTrace();
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
        faceRe.faceRecognition("faceImg");
    }
}
