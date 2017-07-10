package com.project.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by qiaowentao on 2017/1/17.
 */
public class Test {
    private static Logger logger = LoggerFactory.getLogger(Test.class);


    public static void main(String[] args) {

        for(int i=0 ;i<10; i++){
            System.out.println("日志："+i);
            logger.info("日志："+i);
        }

    }

}
