package com.opencc.LeetCodeTest;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class RandomTest {
    public static void main(String[] args) throws InterruptedException {
        Random random = new Random();
//        System.out.println(random.nextInt(5) + 1);
        while (true) {
            System.out.println("result=" + generateRandom1To10(random));
            Thread.sleep(1000);
            Thread a = new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println("");
                }
            });
            a.join();
        }
//        int randomNumber = generateRandom1To7(random);
//        System.out.println("Generated number: " + randomNumber);
    }

    private static int generateRandom1To9(Random random) {
        while (true) {
            int num = random.nextInt(5) + 1; // 生成1到5之间的随机数
            if (num <= 4) {
                return num; // 直接返回1到4的随机数
            } else {
                // 生成1到2的随机数，与5结合形成6到7
                int extraNum = random.nextInt(2) + 1; // 生成1到2之间的随机数
                return 5 + extraNum; // 返回6到7
            }
        }
    }

    private static int generateRandom1To7(Random random) {
        int rand1, rand2, p;
        // 生成第一个随机数
        rand1 = random.nextInt(5) + 1;
        // 生成第二个随机数
        rand2 = random.nextInt(5) + 1;

        p = (rand1 - 1) * 5 + rand2 - 1;
        System.out.println("p = " + p);
        if (p / 3 <= 6) {
            return p / 3 + 1;
        } else return 7;
    }

    private static int generateRandom1To10(Random random) {
        int rand1, rand2, rand3, p;
        rand1 = random.nextInt(5) + 1;
        rand2 = random.nextInt(5) + 1;
        rand3 = random.nextInt(5) + 1;

        //生成0-124的随机数
        p = (rand1 - 1) * 25 + (rand2 - 1) * 5 + rand3;
        return p / 13 + 1;

    }
}

