package com.opencc.LambdaTest;

import java.util.Arrays;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.function.Predicate;
import java.util.stream.Stream;

import static java.lang.System.in;
import static java.lang.System.out;

public class FunctionTest {
    public static void main(String[] args) {
        Map<String, String> nickNames = Map.of("Justin", "caterpillar", "Monica", "momor", "Irene", "hamimi");
        nickNames.entrySet().forEach(out::println);
        Function<Person, Integer> function = (Person person) -> person.getName().length();
        Person person = new Person("Justin biber");
        out.println(function.apply(person));
        BiFunction<Person, Person, Integer> biFunction = (person1, person2) -> person1.getName().length() + person2.getName().length();
        Person person2 = new Person("ama");
        out.println(biFunction.apply(person, person2));
        IntFunction<String> intFunction = age -> {
            if (age < 18) {
                return "Hello, young person!";
            } else if (age >= 18 && age < 65) {
                return "Hello, adult!";
            } else {
                return "Hello, senior citizen!";
            }
        };
        int age = 18;
        out.println(intFunction.apply(age));

        String[] fileNames = new String[] {
          "1.txt", "2.txt", "1.png"
        };

       long count = Stream.of(fileNames).filter(s -> s.endsWith("txt")).count();
        out.println(count);
    }
}

class Person {
    private String Name;

    public Person(String name) {
        Name = name;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
}

