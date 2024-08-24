package com.opencc.JAVANIO.proxy;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HelloSpeaker implements Hello{
    @Override
    public void hello(String name) {
//        Logger.getLogger(HelloSpeaker.class.getName()).log(Level.INFO,"hello()方法开始....");

        System.out.printf("halo，%s%n, doSomething", name);

//        Logger.getLogger(HelloSpeaker.class.getName()).log(Level.INFO,"hello()方法结束....");
    }

}
