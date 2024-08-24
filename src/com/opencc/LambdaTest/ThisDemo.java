package com.opencc.LambdaTest;

import java.util.Stack;
import java.util.Vector;

import static java.lang.System.out;

class Hello {
    Runnable r1 = new Runnable() {
        @Override
        public void run() {
            out.println(this);
        }
    };

    Runnable r2 = new Runnable() {
        @Override
        public void run() {
            out.println(toString());
        }
    };

    public String toString() {
        return "Hello, world!";
    }
}

class Hello2 {
    Runnable r1 = () -> out.println(this);

    Runnable r2 = () -> out.println(toString());

    public String toString() {
        return "Hello, world!";
    }
}

public class ThisDemo {
    public static void main(String[] args) {
        Hello hello = new Hello();
        hello.r1.run();
        hello.r2.run();

        Hello2 hello2 = new Hello2();
        hello2.r1.run();
        hello2.r2.run();
    }
}
