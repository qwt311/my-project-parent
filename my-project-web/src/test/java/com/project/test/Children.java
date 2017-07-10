package com.project.test;

import java.util.function.Supplier;

/**
 * Created by qiaowentao on 2017/5/8.
 */
public class Children implements Parent {

    static Parent creat(final Supplier<Parent> supplier){
        return supplier.get();
    }

    @Override
    public void marry() {
        System.out.println("结婚啦。。。。。。。");
    }

    @Override
    public String birth() {
        return "生下宝宝啦。。。。。";
    }
}
