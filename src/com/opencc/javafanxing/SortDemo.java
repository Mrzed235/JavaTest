package com.opencc.javafanxing;

import java.util.ArrayList;
import java.util.List;

public class SortDemo {
    public static void main(String[] args) {
        Integer[] strs = {3, 2, 5, 1, 12, 6};
        for (Integer s : Sort.sorted(strs)) {
            System.out.println(s);
        }
    }
}
