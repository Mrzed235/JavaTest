package com.opencc.ruantong;


import java.util.*;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;

public class Tets {

    /**
     * //给定一个List<User>，其中User类具有以下属性：id（用户唯一标识）、name（用户名）、age（年龄）、city（所在城市）、salary（工资）。
     * //要求按照以下规则对用户列表进行处理：
     * //首先按照城市进行分组，每个城市中的用户形成一个子列表。
     * //在每个城市分组内，按照年龄从大到小进行排序。
     * //如果年龄相同，则按照工资从高到低进行排序。
     * //最后将所有城市分组后的结果合并成一个新的List<List<User>>，并打印输出每个分组中的用户信息，格式为：城市名称：[用户 1 信息, 用户 2 信息,...]。
     * @param args
     */
    public static void main(String[] args) {
        List<User> userList = new ArrayList<>();
        userList.add(new User(1, "Alice", 25, "New York", 5000.0));
        userList.add(new User(2, "Bob", 30, "New York", 6000.0));
        userList.add(new User(3, "Ami", 30, "New York", 4000.0));
        userList.add(new User(4, "Charlie", 25, "Los Angeles", 4500.0));
        userList.add(new User(5, "David", 35, "Los Angeles", 7000.0));
        userList.add(new User(5, "Jan", 35, "Los Angeles", 7200.0));
        userList.add(new User(6, "Eve", 30, "Chicago", 5500.0));

        Map<String, List<User>> res1 = userList.stream().collect(Collectors.groupingBy(User::getCity));
        res1.forEach((s, users) -> {
            List<User> cols = users.stream().sorted(Comparator.comparing(User::getAge, Comparator.reverseOrder()).thenComparing(User::getSalary, Comparator.reverseOrder())).collect(Collectors.toList());
            System.out.println(s + ": " + cols);
        });

        res1.forEach((city, users) -> {
            List<User> sortedUsers = users.stream()
                    .sorted(Comparator.comparing(User::getAge,Comparator.reverseOrder()).thenComparing(User::getSalary, Comparator.reverseOrder()))
                    .collect(Collectors.toList());
            System.out.println(city + ": " + sortedUsers);
        });

//        for (Map.Entry<String, List<User>> res : res1.entrySet()) {
//            System.out.println(res.getKey()+ res.getValue());
//        }

//        Map<String, List<User>> map = new HashMap<>();
//        for (User user : userList) {
//            if (!map.containsKey(user.getCity())) {
//                List<User> list = new ArrayList<>();
//                list.add(user);
//                map.put(user.getCity(), list);
//            } else {
//                map.get(user.getCity()).add(user);
//            }
//        }
//        for (Map.Entry<String, List<User>> res : map.entrySet()) {
//            res.getValue().sort(new Comparator<User>() {
//                @Override
//                public int compare(User o1, User o2) {
//                    //先按照年龄排序
//                    int ageCompare = Integer.compare(o2.getAge(), o1.getAge());
//                    if (ageCompare != 0) {
//                        return ageCompare;
//                    }
//                    //如果年龄一样，按照工资排序
//                    return Double.compare(o2.getSalary(), o1.getSalary());
//                }
//            });
//        }
//        for (Map.Entry<String, List<User>> res : map.entrySet()) {
//            String city = res.getKey();
//            List<User> list = res.getValue();
//            System.out.println(city + " :" + list);
//        }
//
//        System.out.println();
    }
}


class User {
    private int id;
    private String name;
    private int age;
    private String city;
    private double salary;

    public User(int id, String name, int age, String city, double salary) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.city = city;
        this.salary = salary;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getCity() {
        return city;
    }

    public double getSalary() {
        return salary;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", name='" + name + '\'' + ", age=" + age + ", city='" + city + '\'' + ", salary=" + salary + '}';
    }
}
