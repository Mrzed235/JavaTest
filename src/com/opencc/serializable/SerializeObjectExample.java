package com.opencc.serializable;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;

public class SerializeObjectExample {
    public static void main(String[] args) {
        try (FileOutputStream fileOut = new FileOutputStream("person.ser");
             ObjectOutputStream out = new ObjectOutputStream(fileOut)) {

            // 创建一个 Person 对象
            Person person = new Person("Alice", 30);

            // 序列化对象
            out.writeObject(person);

            System.out.println("Serialized data is saved in person.ser");

        } catch (IOException i) {
            i.printStackTrace();
        }
    }
}