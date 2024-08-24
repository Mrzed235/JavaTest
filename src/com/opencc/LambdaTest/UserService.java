package com.opencc.LambdaTest;

public interface UserService {
    void getNumber();

    default void getName() {
        System.out.println("fdafdfds");
    }
}
