package com.opencc.compratorTest;

class Hello1 {
    Runnable r1 = () -> System.out.println(this);
    Runnable r2 = () -> System.out.println(toString());

    public String toString() {
        return "Hello world!";
    }
}

public class Hello2 {
    public static void main(String[] args) {
        Hello1 hello1 = new Hello1();
        hello1.r1.run();
        hello1.r2.run();
    }
}