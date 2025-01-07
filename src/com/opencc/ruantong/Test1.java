package com.opencc.ruantong;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;

class Employee {
    private String name;
    private double salary;

    private int age;

    private String department;

    public Employee(String name, double salary) {
        this.name = name;
        this.salary = salary;
    }

    public Employee(String name, double salary, String department) {
        this.name = name;
        this.salary = salary;
        this.department = department;
    }

    public Employee(String name, int age, String department) {
        this.name = name;
        this.age = age;
        this.department = department;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Employee{name= " + name + ", age = " + age + ", department = " + department;
    }
}

class Order {
    private int customerId;
    private double amount;

    public Order(int customerId, double amount) {
        this.customerId = customerId;
        this.amount = amount;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}

class Person {
    private String name;
    private int age;
    private String city;
    private double salary;

    public Person(String name, int age, String city) {
        this.name = name;
        this.age = age;
        this.city = city;
    }

    public Person(String name, int age, String city, double salary) {
        this.name = name;
        this.age = age;
        this.city = city;
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Person{name=" + name + ", age=" + age + ", city=" + city + "}";
    }
}

class NameAndSalary {
    private String name;
    private double salary;

    public NameAndSalary(String name, double salary) {
        this.name = name;
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "NameAndSalary{name="+ name + ",  salary=" + salary+"}";
    }
}
class Transaction {
    private int id;
    private double amount;
    private String type;

    public Transaction(int id, double amount, String type) {
        this.id = id;
        this.amount = amount;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}

public class Test1 {
    public static void main(String[] args) {
        test1();
        test2();
        test3();
        test4();
        test5();
        test6();
        test7();
        test8();
        test9();
    }

    /**
     * 过滤并转换列表
     * 给定一个 List<String>，你需要移除所有长度小于等于3的字符串，并将剩余的字符串转换为大写形式。
     */
    private static void test1() {
        List<String> words = Arrays.asList("apple", "cat", "dog", "banana", "cup");
        List<String> res = words.stream().filter(o -> o.length() > 3).map(String::toUpperCase).collect(Collectors.toList());
        System.out.println(res);
    }

    /**
     * 统计员工工资总和
     * 给定一个包含员工信息的 List<Employee>，其中每个 Employee 对象有两个属性：name（姓名）和 salary（薪水）。请计算所有员工的薪水总和。
     */
    private static void test2() {
        List<Employee> employees = Arrays.asList(
                new Employee("Alice", 7000.5),
                new Employee("Bob", 8500.0),
                new Employee("Charlie", 6800.2));
        double res = employees.stream().mapToDouble(Employee::getSalary).sum();
        System.out.println(res);
    }

    /**
     * 查找最年长的员工
     * 给定一个包含员工信息的 List<Employee>，其中每个 Employee 对象有三个属性：name（姓名）、age（年龄）和 department（部门）。请找出最年长的员工。
     * 如果存在多个最年长的员工，返回任意一个。
     * 使用流处理完成任务。
     * 返回找到的 Employee 对象或 Optional<Employee>。
     */
    private static void test3() {
        List<Employee> employees = Arrays.asList(
                new Employee("Alice", 34, "HR"),
                new Employee("Bob", 45, "Engineering"),
                new Employee("Charlie", 45, "Marketing"));

        Optional<Employee> max = employees.stream().max(Comparator.comparingInt(Employee::getAge));
        max.ifPresent(System.out::println);
    }

    /**
     * 分组统计
     * 描述：
     * 给定一个包含订单信息的 List<Order>，其中每个 Order 对象有两个属性：customerId（客户ID）和 amount（订单金额）。请根据 customerId 对订单进行分组，并计算每个客户的订单总额。
     * 使用流处理完成任务。
     * 返回一个 Map<Integer, Double>，键为客户ID，值为该客户的订单总额。
     */
    public static void test4() {
        List<Order> orders = Arrays.asList(
                new Order(1, 150.0),
                new Order(2, 200.0),
                new Order(1, 100.0),
                new Order(2, 250.0));
        Map<Integer, Double> res = orders.stream().collect(Collectors.groupingBy(Order::getCustomerId, Collectors.summingDouble(Order::getAmount)));
        System.out.println(res);
    }

    /**
     * 扁平化嵌套列表
     * 给定一个 List<List<Integer>>，你需要将其扁平化成一个单一的 List<Integer>。
     * 要求：
     * 使用流处理完成任务。
     * 返回一个包含所有元素的新 List<Integer>。
     */
    public static void test5() {
        List<List<Integer>> nestedList = Arrays.asList(
                Arrays.asList(1, 4, 5),
                Arrays.asList(2, 3),
                Arrays.asList(7, 6, 9, 8));
        List<Integer> res = nestedList.stream().flatMap(List::stream).sorted(Integer::compareTo).collect(Collectors.toList());
        System.out.println(res);
    }

    /**
     * 按条件筛选并排序
     * 描述：
     * 给定一个 List<Person>，其中每个 Person 对象有三个属性：name（姓名）、age（年龄）和 city（城市）。请筛选出所有来自 "New York" 的人，并按照年龄升序排序。
     * 要求：
     * 使用流处理完成任务。
     * 返回一个新的 List<Person> 包含处理后的结果。
     */
    public static void test6() {
        List<Person> people = Arrays.asList(
                new Person("Alice", 30, "New York"),
                new Person("Bob", 25, "Los Angeles"),
                new Person("Charlie", 35, "New York"),
                new Person("David", 28, "New York"));
        List<Person> res = people.stream().filter(p -> p.getCity().equals("New York")).sorted(Comparator.comparingInt(Person::getAge)).collect(Collectors.toList());
        System.out.println(res);
    }

    /**
     * 按条件分组统计
     * 描述：
     * 给定一个 List<Transaction>，其中每个 Transaction 对象有三个属性：id（交易ID）、amount（金额）和 type（类型）。请根据 type 对交易进行分组，并计算每种类型的总金额。
     * 要求：
     * 使用流处理完成任务。
     * 返回一个 Map<String, Double>，键为交易类型，值为该类型的总金额。
     */
    public static void test7() {
        List<Transaction> transactions = Arrays.asList(
                new Transaction(1, 200.5, "INCOME"),
                new Transaction(2, 150.0, "EXPENSE"),
                new Transaction(3, 100.0, "INCOME")
        );
        Map<String, Double> res = transactions.stream().collect(Collectors.groupingBy(Transaction::getType, Collectors.summingDouble(Transaction::getAmount)));
        System.out.println(res);
    }

    /**
     * 查找重复元素
     * 给定一个整数列表 List<Integer>，找出所有出现次数大于1次的元素，并返回它们及其出现次数。
     * 要求：
     * 使用流处理完成任务。
     * 返回一个 Map<Integer, Long>，键为重复元素，值为该元素出现的次数。
     */
    private static void test8() {
        List<Integer> numbers = Arrays.asList(1, 2, 2, 3, 4, 4, 4, 5);
        Map<Integer, Long> res = numbers.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet().stream().filter(o->o.getValue() > 1).collect(Collectors.toMap(Map.Entry::getKey,Map.Entry::getValue));
        System.out.println(res);
    }

    /**
     * 复杂对象筛选与转换
     * 给定一个 List<Person>，其中每个 Person 对象有四个属性：name（姓名）、age（年龄）、city（城市）和 salary（薪资）。
     * 请筛选出所有来自 "New York" 且年龄大于30岁的人，并将他们的名字和薪资转换为一个新的 Record 类型 NameAndSalary。
     * 使用流处理完成任务。
     * 返回一个新的 List<NameAndSalary> 包含处理后的结果。
     */
    private static void test9() {
        List<Person> people = Arrays.asList(
                new Person("Alice", 34, "New York", 7000.0),
                new Person("Bob", 25, "Los Angeles", 6500.0),
                new Person("Charlie", 35, "New York", 8000.0)
        );
        List<NameAndSalary> res = people.stream().filter(p -> p.getCity().equals("New York")).filter(p -> p.getAge() > 30).map(p -> new NameAndSalary(p.getName(), p.getSalary())).collect(Collectors.toList());

        System.out.println(res);
    }

    /**
     * 并行流性能测试
     * 编写一段代码来比较顺序流和并行流在处理大型数据集时的性能差异。你可以创建一个包含大量随机整数的列表，并分别用顺序流和平行流对其进行排序或执行其他耗时操作，
     * 最后打印出两种方式所花费的时间。
     * 使用流处理完成任务。
     * 测试并对比顺序流和平行流的性能。
     * 使用 System.currentTimeMillis() 或 System.nanoTime() 来测量时间。
     * 注意并行流不一定总是比顺序流快，具体取决于任务性质和硬件资源。
     */
    private static void test10() {}

    /**
     * 聚合多个文件的内容
     * 假设你有一个目录，里面存放了多个文本文件。你需要读取这些文件，并将所有文件的内容合并到一个大字符串中，同时去除每一行末尾的换行符。
     * 要求：
     * 使用流处理完成任务。
     * 返回一个包含所有文件内容的字符串。
     * 解答提示：
     * 使用 Files.lines(Path) 来读取文件内容作为流。
     * 使用 Stream.concat 或 Stream.of 来连接多个流。
     * 使用 flatMap 来展平流中的元素。
     * 使用 collect(Collectors.joining()) 来拼接字符串。
     */
    private static void test11() {}

    /**
     * 基于谓词动态过滤
     * 描述：
     * 给定一个 List<Item> 和一组谓词（Predicate<Item>），请编写一个方法，该方法接受这两个参数，并返回一个新的列表，其中只包含满足所有谓词条件的 Item。
     * 要求：
     * 使用流处理完成任务。
     * 谓词应该作为参数传递，以便可以根据不同的需求灵活地组合谓词。
     */
    private static void test12() {}
}
