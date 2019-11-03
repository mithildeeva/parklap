package com.parklap.util;

import java.util.List;

public class Utility {

    public static int[] integerListToInt(List<Integer> list) {
        int[] intArr = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            intArr[i] = list.get(i);
        }
        return intArr;
    }
}
