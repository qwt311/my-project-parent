package com.project.test;

import java.time.*;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Stream;

/**
 * Created by qiaowentao on 2017/5/12.
 */
public class Test3 {

    private static List<Employee> list = Arrays.asList(
            new Employee("张三",15,'男',3000),
            new Employee("李四",18,'男',5000),
            new Employee("琼瑶",20,'女',10000),
            new Employee("李师师",23,'女',30000),
            new Employee("张无忌",30,'男',100000)
    );


    public static void main(String[] args) {
        LocalDate localDate = LocalDate.now();
        System.out.println(localDate);
        MonthDay birthDay = MonthDay.of(localDate.getMonth(),localDate.getDayOfMonth());
        MonthDay currentBirthDay = MonthDay.from(LocalDate.now());
        if(birthDay.equals(currentBirthDay)){
            System.out.println("Today is your birthday!");
        }else{
            System.out.println("sorry,today is not your birthday");
        }

        System.out.println(LocalTime.now().plusHours(2));

        System.out.println("------------------------");

        System.out.println(LocalDate.now()+" "+LocalTime.now());

        //System.out.println("获取一周后的时间："+LocalDate.now().plus(1, ChronoUnit.WEEKS));
        System.out.println("获取一周后的时间："+LocalDate.now().plusWeeks(1));

        //System.out.println("获取一年前的时间："+LocalDate.now().minus(1,ChronoUnit.YEARS));
        System.out.println("获取一年前的时间："+LocalDate.now().minusYears(1));

        System.out.println(LocalDate.of(2017,5,14).isBefore(LocalDate.now()));

        System.out.println(LocalDate.of(2016,5,14).isLeapYear());

        //System.out.println("获取两分钟前的时间:"+LocalDateTime.now().minus(2,ChronoUnit.MINUTES));
        System.out.println("获取两分钟前的时间:"+LocalDateTime.now().minusMinutes(2));

        System.out.println(localDate.atStartOfDay());

        LocalDateTime endTime = LocalDateTime.now().minusMinutes(30);
        System.out.println(endTime);

        System.out.println(LocalDateTime.now());

    }

}
