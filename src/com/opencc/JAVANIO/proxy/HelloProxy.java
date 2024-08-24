package com.opencc.JAVANIO.proxy;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 静态代理
 */
public class HelloProxy implements Hello {
    private Hello hello;

    public HelloProxy(Hello hello) {
        this.hello = hello;
    }

    @Override
    public void hello(String name) {
        log("hello()方法开始....");
        hello.hello(name);
        log("hello()方法结束....");
    }

    public void log(String msg) {
        Logger.getLogger(HelloProxy.class.getName()).log(Level.INFO, msg);
    }

    public static void main(String[] args) throws Exception{
        Class<?> clzProxy = Class.forName("com.opencc.JAVANIO.proxy.HelloProxy");
        Class<?> clzSpeaker = Class.forName("com.opencc.JAVANIO.proxy.HelloSpeaker");
        Constructor<?> speakerCons = clzSpeaker.getDeclaredConstructor();
        Constructor<?> proxyCons = clzProxy.getDeclaredConstructor(Hello.class);
        Object speakerIns = speakerCons.newInstance();
        Object proxyIns = proxyCons.newInstance(speakerIns);
        Method helloMethod = clzProxy.getMethod("hello", String.class);
        helloMethod.invoke(proxyIns, "duanjie");
    }
}
