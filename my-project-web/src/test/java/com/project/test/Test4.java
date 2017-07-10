package com.project.test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * Created by qiaowentao on 2017/5/16.
 */
public class Test4 {

    static List<Employee> list = Arrays.asList(
            new Employee("张三",16,'男',10000),
            new Employee("李四",17,'男',12000),
            new Employee("王五",18,'男',13000),
            new Employee("张三",19,'男',10000)
    );

    static List<String> strList = Arrays.asList(
            "I am a boy",
            "I love the girl",
            "but the girl loves another boy"
    );

    static List<Integer> intList = Arrays.asList(
            12,421,14,123,542
    );

    private static void testStream(){
        Stream<Employee> stream = list.stream();
        stream.filter(employee -> {
            return employee.getAge() >= 17;
        }).distinct(
        ).map(Employee::getName).forEach(System.out::println);

    }

    private static void mergeStream(){
        Stream<String> stream = strList.stream();
        stream.map(str -> str.split(" ")
        ).flatMap(Arrays::stream
        ).distinct(
        ).forEach(System.out::println);
    }

    private static void anyMatch(){
        Stream<Employee> stream = list.stream();
        System.out.println(stream.anyMatch(employee -> employee.getSalary() > 10000));
    }

    private static void allMatch(){
        Stream<Employee> stream = list.stream();
        System.out.println(stream.allMatch(employee -> employee.getSalary() > 1000));
    }

    private static void noneMatch(){
        Stream<Employee> stream = list.stream();
        boolean b = stream.noneMatch(employee -> employee.getAge() > 23);
        System.out.println(b);
    }

    private static void findMatch(){
        Stream<Employee> stream = list.stream();
        Optional<Employee> any = stream.findAny();
        System.out.println(any);
    }

    private static void reduce(){
        Stream<Integer> stream = intList.stream();
        Integer reduce = stream.reduce(0, Integer::min);
        System.out.println(reduce);
    }

    private static void streamToNumber(){
        Stream<Employee> stream = list.stream();
        System.out.println(stream.mapToInt(Employee::getAge).min());
    }


    public static void main(String[] args) {
        testStream();
        mergeStream();
        /*anyMatch();
        allMatch();
        noneMatch();*/
        findMatch();
        reduce();
        streamToNumber();
        /*List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        Stream<Integer> stream = numbers.stream();
        stream.filter((x) -> {
            return x % 2 == 0;
        }).map((x) -> {
            return x * x;
        }).forEach(System.out::println);*/
    }

}
