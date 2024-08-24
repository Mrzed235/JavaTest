package com.opencc.LeetCodeTest;

import java.lang.reflect.Method;
import java.util.*;

class  RandomizedSet {

    List<Integer> nums;
    Map<Integer, Integer> indices;
    Random random;

    public RandomizedSet() {
        nums = new ArrayList<>();
        indices = new HashMap<>();
        random = new Random();
    }

    public boolean insert(int val) {
        if (indices.containsKey(val)) {
            return false;
        }
        int index = nums.size();
        nums.add(val);
        indices.put(val, index);
        return true;
    }

    public boolean remove(int val) {
        if (!indices.containsKey(val)) {
            return false;
        }
        int index = indices.get(val);
        int last = nums.get(nums.size() - 1);
        nums.set(index, last);
        indices.put(last, index);
        nums.remove(nums.size() - 1);
        indices.remove(val);
        return true;
    }

    public int getRandom() {
        int randomIndex = random.nextInt(nums.size());
        return nums.get(randomIndex);
    }
}

public class LeetCode380 {
    public static void main(String[] args) {
        String[] signals = {"RandomizedSet","insert","remove","insert","getRandom","remove","insert","getRandom"};
        int[][] values = {{},{1},{2},{2},{},{1},{2},{}};
        try {
            // 创建 RandomizedSet 对象
            Class<?> randomizedSetClass = RandomizedSet.class;
            //获取RandomizedSet的实例
            Object randomizedSetObject = randomizedSetClass.getDeclaredConstructor().newInstance();
            // 获取 RandomizedSet 类的所有方法包括私有方法
            Method[] methods = randomizedSetClass.getDeclaredMethods();
            // 反射调用方法并传递对应位置的参数
            for (int i = 0; i < signals.length; i++) {
                String methodName = signals[i];
                int[] methodParams = values[i];
                // 根据方法名查找对应的方法
                Method method = null;
                for (Method m : methods) {
                    if (m.getName().equals(methodName)) {
                        method = m;
                        break;
                    }
                }
                if (method != null) {
                    // 根据方法参数类型构造参数数组
                    Object[] params = new Object[methodParams.length];
                    for (int j = 0; j < methodParams.length; j++) {
                        Class<?> paramType = method.getParameterTypes()[j];
                        if (paramType == int.class) {
                            params[j] = methodParams[j];
                        } else if (paramType == int[].class) {
                            params[j] = methodParams;
                        }
                    }
                    // 调用方法
                    Object result = method.invoke(randomizedSetObject, params);
                    System.out.println(result);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
