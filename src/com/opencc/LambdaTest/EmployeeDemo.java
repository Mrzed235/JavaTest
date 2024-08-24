package com.opencc.LambdaTest;

import java.util.Arrays;
import java.util.List;
import java.util.function.IntBinaryOperator;

import static java.lang.System.out;

public class EmployeeDemo {
    public static void main(String[] args) {
        List<Employee> employees = Arrays.asList(
                new Employee("Justin", 39, Gender.MALE),
                new Employee("Monica", 36, Gender.FEMALE),
                new Employee("Irene", 6, Gender.FEMALE));

//        // 计算男性员工的年龄总和
//        int sum = employees.stream()
//                .filter(employee -> employee.getGender() == Gender.MALE)
//                .mapToInt(Employee::getAge)
//                .sum();

        // 计算男性员工的年龄总和
        int sum = employees.stream()
                .filter(employee -> employee.getGender() == Gender.FEMALE)
                .mapToInt(Employee::getAge)
                .reduce((total, age) -> total + age).getAsInt();

// 计算男性员工的平均年龄
        int average = (int) employees.stream()
                .filter(employee -> employee.getGender() == Gender.MALE)
                .mapToInt(Employee::getAge)
                .average()
                .getAsDouble();

//// 计算男性员工的最大年龄
//        int max = employees.stream()
//                .filter(employee -> employee.getGender() == Gender.MALE)
//                .mapToInt(Employee::getAge)
//                .max()
//                .getAsInt();
// 计算男性员工的最大年龄
        int max = employees.stream()
                .filter(employee -> employee.getGender() == Gender.MALE)
                .mapToInt(Employee::getAge)
                .reduce(0, (currMax, age) -> age > currMax ? age : currMax);
        Arrays.asList(sum, average, max).forEach(out::println);
    }
}

enum Gender {MALE, FEMALE}

class Employee {
    private String name;
    private int age;
    private Gender gender;

    public Employee(String name, int age, Gender gender) {
        this.name = name;
        this.age = age;
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public Gender getGender() {
        return gender;
    }
}
