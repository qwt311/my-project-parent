package com.project.test;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by qiaowentao on 2017/5/27.
 */
public class TestHashMap {


    public static void main(String[] args) {
        Map<Country,String> map = new HashMap<>();
        map.put(new Country("Japan",100),"Tokyo");
        map.put(new Country("USA",10000),"Washington");
        map.put(new Country("UK",2000),"London");
        map.put(new Country("France",20000),"Paris");

        Iterator<Map.Entry<Country, String>> iterator = map.entrySet().iterator();
        while(iterator.hasNext()) {
            iterator.next();
        }

    }

}
