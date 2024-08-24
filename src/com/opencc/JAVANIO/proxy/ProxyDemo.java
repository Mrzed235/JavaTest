package com.opencc.JAVANIO.proxy;

public class ProxyDemo {
    public static void main(String[] args) {
        Hello helloproxy = (Hello) LoggingProxy.bind(new HelloSpeaker());
        helloproxy.hello("Justin");
    }
}
