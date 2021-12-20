package com.danssion.java.generic;

import java.util.List;

public class ExtendSuper {
    public static <T> void copy(List<? extends T> src, List<? super T> dest) {
        for (int i = 0; i < src.size(); ++i) {
            dest.add(src.get(i));
        }
    }
}
