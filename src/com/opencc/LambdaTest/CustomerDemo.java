package com.opencc.LambdaTest;

import java.util.*;

import static java.lang.System.out;
import static java.util.Arrays.asList;
import static java.util.Comparator.comparing;

public class CustomerDemo {
    public static void main(String[] args) {
        List<Customer> customers = asList(
                new Customer("Justin", "Lin", 804),
                new Customer("Monica", "Huang", 804),
                new Customer("Irene", "Lin", 804)
        );
        customers.iterator();
        Comparator<Customer> byLastName = comparing(Customer::getLastName);

        customers.sort(byLastName
                .thenComparing(Customer::getFirstName)
                .thenComparing(Customer::getZipCode));

        customers.forEach(out::println);
    }


}

class Customer {
    private String firstName;
    private String lastName;
    private Integer zipCode;

    public Customer(String firstName, String lastName, Integer zipCode) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.zipCode = zipCode;
    }

    @Override
    public String toString() {
        return String.format("Customer(%s %s, %d)", firstName, lastName, zipCode); // 添加了toString方法
    }

    public String getFirstName() { // 添加了getFirstName方法
        return firstName;
    }

    public String getLastName() { // 添加了getLastName方法
        return lastName;
    }

    public Integer getZipCode() { // 添加了getZipCode方法
        return zipCode;
    }
}