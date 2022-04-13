package com.danssion.java.serialize;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

/**
 * @author duanxiang  duanxiang@haodelian.com
 * @version 1.0
 * @date 2022/3/20 下午5:24
 * @desc JavaExample-DdsignPattern
 */
public class XmlSerialize {
    public static void main(String[] args) {
        User user = new User();
        user.setName("Dx");
        user.setAge(19);

        String xml = serialToXml(user);
        System.out.println(xml);

        User unu = deserialFromXml(xml);
        System.out.println(unu);
    }


    private static String serialToXml(User u) {
        return new XStream(new DomDriver()).toXML(u);
    }

    private static <T> T deserialFromXml(String xml) {
        return (T) new XStream(new DomDriver()).fromXML(xml);
    }
}
