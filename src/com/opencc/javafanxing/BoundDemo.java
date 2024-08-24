package com.opencc.javafanxing;

class Animal{}
class Human extends Animal{}
class Toy{}
class Duck<T extends Animal> {}
public class BoundDemo {
    public static void main(String[] args) {
        Duck<Animal> ad = new Duck<>();
        Duck<Human> hd = new Duck<>();
//        Duck<Toy> hd = new Duck<Toy>();
    }
}
