package com.opencc.LambdaTest;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Test1 {
    public static void main(String[] args) {
        List<String> items = new ArrayList<>();
        items.add("Ama");
        items.add("justin");
        items.add("Jan");
        items.add("Wdd");
        List<String> result = items.stream().filter(s -> s.length()>3).collect(Collectors.toList());
        System.out.println(result);
    }
}
