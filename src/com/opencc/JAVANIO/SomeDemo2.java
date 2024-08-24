package com.opencc.JAVANIO;

class Some2 {
    static {
        System.out.println("执行静态代码块");
    }
}

public class SomeDemo2 {
    public static void main(String[] args) throws Exception {
        Class<?> clz = Class.forName("com.opencc.JAVANIO.Some2", false, SomeDemo2.class.getClassLoader());
        System.out.println("已加载 Some2.class ");
        Some2 s;
        System.out.println("声明 Some 参考名称");
        clz.getDeclaredConstructor().newInstance();
        System.out.println("生成 Some 实例");
    }
}