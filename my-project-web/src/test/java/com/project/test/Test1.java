package com.project.test;

import org.apache.commons.lang.StringUtils;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by qiaowentao on 2017/4/24.
 */
public class Test1 {

    public static void hi(){
        List<Integer> list = new ArrayList<>();
        for(int i=0; i<110; i++){
            list.add(i);
        }

        if(list.size() > 100){
            list = list.subList(0,100);
        }
        System.out.println(list.subList(0,100));
        for(Integer in: list){
            System.out.println(in);
        }
    }

    public static void main(String[] args) {
        Parent children;
        children = Children.creat(Children::new);
        InvocationHandler handler = new TestDynamic(children);
        Parent proxy = (Parent) Proxy.newProxyInstance(handler.getClass().getClassLoader(),children.getClass().getInterfaces(),handler);
        proxy.marry();
        String str = proxy.birth();
        System.out.println(str);
        System.out.println(proxy.died());
        Arrays.asList("a","b","d").forEach(System.out::println);
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        String a = "|";
        list.forEach(System.out::println);
    }

}
