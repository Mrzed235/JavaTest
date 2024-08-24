package com.opencc.LambdaTest;

public interface QueryService extends UserService, CousumerService{
    @Override
    default void getName() {
        UserService.super.getName();
    }
}
