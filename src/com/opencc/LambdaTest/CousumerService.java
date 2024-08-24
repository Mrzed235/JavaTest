package com.opencc.LambdaTest;

public interface CousumerService {

    default void getName() {
        System.out.println("fdafdfds");
    }
}
