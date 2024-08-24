package com.opencc.compratorTest;

import java.util.*;

public class ComparatorTest1 {
    public static void main(String[] args) {
        int[] a = {1, 11, 9, 6, 7, 23, 5};
        Integer[] b = {1, 21, 13, 22, 15};
        TreeMap<Integer, Employee> treeMap = new TreeMap<>();
        treeMap.put(1, new Employee(19, "aab"));
        TreeSet<Employee> treeSet = new TreeSet<>(Comparator.comparing(Employee::getName));
        treeSet.add(new Employee(18, "fdafda"));
        treeSet.add(new Employee(12, "fdaf"));
        treeSet.stream().sorted(new Comparator<Employee>() {
            @Override
            public int compare(Employee o1, Employee o2) {
                return o1.getAge() - o2.getAge();
            }
        });
        List<Employee> items = new ArrayList<>();
        items.add(new Employee(19, "Justin"));
        items.add(new Employee(19, "Alan"));
        items.add(new Employee(20, "Caterpillar"));
        items.add(new Employee(18, "Bush"));
        items.add(new Employee(16, "Baas"));
        for (int i = 0; i < items.size(); i++) {
            System.out.println("age is " + items.get(i).getAge() + "- name is " + items.get(i).getName());
        }
        items.sort((o1, o2) -> {
            if (o1.getAge() != o2.getAge()) {
                return o1.getAge() - o2.getAge();
            } else return o1.getName().compareTo(o2.getName());
        });
        for (int i = 0; i < items.size(); i++) {
            System.out.println("age is " + items.get(i).getAge() + "- name is " + items.get(i).getName());
        }

        String[] names = {"Justin", "Caterpillar", "Bush", "Baas"};
        Arrays.sort(names, String::compareTo);
        Arrays.sort(b, (o1, o2) -> -(o1 - o2));
        System.out.println(Arrays.toString(b));
        System.out.println(Arrays.toString(a));
        System.out.println(Arrays.toString(names));
    }
}

class StringComparator implements Comparator<String> {
    @Override
    public int compare(String o1, String o2) {
        if (o1.length() != o2.length()) {
            return -(o1.length() - o2.length());
        } else return o1.compareTo(o2);
    }

}
