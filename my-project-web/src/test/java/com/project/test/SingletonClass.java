package com.project.test;

/*
  Created by qiaowentao on 2017/5/17.
 */
public class SingletonClass {

    /*
    第一种方法：
     */
    /*private static volatile SingletonClass ourInstance = null;

    public static SingletonClass getInstance() {
        if(ourInstance == null){
            synchronized(SingletonClass.class){
                if(ourInstance == null){
                    ourInstance = new SingletonClass();
                }
            }
        }
        return ourInstance;
    }*/

    /*
    第二种方法：
     */
    private static class SingletonClassInstance{
        private static final SingletonClass singletonClass = new SingletonClass();
    }

    public static SingletonClass getSingletonInstance(){
        return SingletonClassInstance.singletonClass;
    }

    private SingletonClass() {
    }
}
