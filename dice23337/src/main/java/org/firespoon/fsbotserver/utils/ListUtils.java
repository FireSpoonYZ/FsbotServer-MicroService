package org.firespoon.fsbotserver.utils;

import java.util.List;

abstract public class ListUtils {
    public static Integer sum(List<Integer> list) {
        int sum = 0;
        for (int num : list) {
            sum += num;
        }
        return sum;
    }

    public static String join(List<Integer> list, String separator) {
        StringBuilder res = new StringBuilder();
        boolean flag = false;
        for (Integer num : list) {
            if (flag) {
                res.append(separator);
            } else {
                flag = true;
            }
            res.append(num);
        }
        return res.toString();
    }
}
