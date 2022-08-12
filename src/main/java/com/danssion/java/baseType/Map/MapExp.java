package com.danssion.java.baseType.Map;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * @author duanxiang  duanxiang@haodelian.com
 * @version 1.0
 * @date 2022/7/18 14:02
 * @desc JavaExample-DdsignPattern
 */
public class MapExp {

    public static void main(String[] args) {
        testHashSet();
    }

    private static void testHashSet() {
        List<Integer> list = new ArrayList<>();
        list.add(2);list.add(4);list.add(6);list.add(7);
        HashSet<Integer>  set = new HashSet<>();
        set.add(2);
        set.add(4);
        set.add(6);
        set.add(7);

        List<Integer> newlist = new ArrayList<>();
        newlist.add(1);newlist.add(2);newlist.add(3);

        for (Integer newOne:newlist) {
            if(set.contains(newOne)) {
                System.out.println(newOne);
            } else {
                list.add(newOne);
            }
        }
        System.out.println(list);
    }
}
