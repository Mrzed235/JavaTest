package com.opencc.ruantong;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class TEST2 {
    public static void main(String[] args) {
        List<UserB> userList = new ArrayList<>();
        userList.add(new UserB(1, "Alice"));
        userList.add(new UserB(2, "Bob"));
        userList.add(new UserB(1, "Charlie"));

        List<UserB> distinctList = userList.stream().filter(distinctByKey(UserB::getId)).collect(Collectors.toList());
        List<UserB> distinctList1 = new ArrayList<>(userList.stream().collect(Collectors.toMap(UserB::getId, Function.identity(), (e, r) -> e)).values());

        Set<UserB> set = new HashSet<>();
        List<UserB> distinctList2 = new ArrayList<>();
        for (UserB userB : userList) {
            if (set.add(userB)) {
                distinctList2.add(userB);
            }
        }

        System.out.println(distinctList);
        System.out.println(distinctList1);
        System.out.println(distinctList2);
    }

    public static <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {
        Set<Object> seen = Collections.newSetFromMap(new ConcurrentHashMap<>());
        return t -> seen.add(keyExtractor.apply(t));
    }

}


class UserB {
    private int id;
    private String name;

    public UserB(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String name() {
        return name;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(id);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;
        UserB user = (UserB) o;
        return id == user.id;
    }

    @Override
    public String toString() {
        return "UserB{" + "id=" + id + ", name='" + name + '\'' + '}';
    }
}