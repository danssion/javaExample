package com.danssion.java.serialize;

import com.caucho.hessian.io.HessianInput;
import com.caucho.hessian.io.HessianOutput;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * @author duanxiang  duanxiang@haodelian.com
 * @version 1.0
 * @date 2022/3/20 下午5:50
 * @desc JavaExample-DdsignPattern
 */
public class HessionSerializer {

    public static void main(String[] args) throws IOException {
        User user = new User();
        user.setName("Dx");
        user.setAge(19);

        byte[] xml = serialToHS(user);
        System.out.println(xml);
        System.out.println(xml.length);

        User unu = deserialFromHS(xml);
        System.out.println(unu);
    }


    private static byte[] serialToHS(User u) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        HessianOutput ho = new HessianOutput(baos);
        ho.writeObject(u);
        return baos.toByteArray();
    }

    private static <T> T deserialFromHS(byte[] data) throws IOException {
        ByteArrayInputStream bais = new ByteArrayInputStream(data);
        HessianInput hi = new HessianInput(bais);
        return (T) hi.readObject();
    }
}
