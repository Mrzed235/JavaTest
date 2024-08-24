package com.opencc.JAVANIO;

import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class JavaReflect {
    public static void main(String[] args) throws Exception {
//        Class<?> stringClass = String.class;
//        Class<?> NIOclass = NIOTest.class;
//        Object randomizedSetObject = NIOclass.getDeclaredConstructor().newInstance();
//        Method[] declaredMethods = stringClass.getDeclaredMethods();
//        Method[] methods = stringClass.getMethods();
//        Constructor<?>[] declaredConstructors = stringClass.getDeclaredConstructors();
//        for (Method declaredMethod : declaredMethods) {
//            System.out.println(declaredMethod.getName());
//        }
//        for (Method method : methods) {
//            System.out.println(method.getName());
//        }
//        System.out.println("构造函数");
//        for (Constructor<?> declaredConstructor : declaredConstructors) {
//            System.out.println(declaredConstructor);
//        }
//        Constructor<?> constructor = stringClass.getConstructor(String.class);
//        Object instance = constructor.newInstance("Hello, world!");
//        System.out.println(instance);
//        Method toStringMethod = stringClass.getMethod("toString");
//        Method valueOfMethod = stringClass.getMethod("valueOf", char[].class);
//        System.out.println(toStringMethod.invoke(instance));
//        System.out.println(valueOfMethod);
//        System.out.println(String.class.getPackage());
        Class<?> clz = Class.forName("java.util.ArrayList");
        Constructor<?> constructor1 = clz.getDeclaredConstructor();
        List list = (List) constructor1.newInstance();
        list.add(1);
        list.add(2);
        list.add(231902);
        list.add(23);
        System.out.println(list);
        Class<?> clz2 = ArrayList.class;
        Object[] obj = (Object[]) Array.newInstance(clz2, 10);
        obj[0] = new ArrayList<>();
        ArrayList list1 = (ArrayList) obj[0];
        System.out.println(obj);
        System.out.println(list1);
        URL url = new URL("http://example.com/mypackage/MyClass.class");
        URLClassLoader loader = new URLClassLoader(new URL[]{url});
        Class<?> clazz = loader.loadClass("mypackage.MyClass");
    }
}
