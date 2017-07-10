package com.project.test;

import java.io.Serializable;
import java.util.List;

/**
 * Created by qiaowentao on 2017/5/12.
 */
public interface BaseInterface<T> extends Serializable {

    T getValue();

    List<T> getList(T t);

    void update(T t);

    default void init(){
    }



}
