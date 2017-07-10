package com.project.test;

import java.util.function.Supplier;

/**
 * Created by qiaowentao on 2017/5/8.
 */
public interface Parent {

    void marry();

    String birth();

    default String died(){

        return "死了....";
    }

    static void reborn(){
        System.out.println("重生了");
    }

}
