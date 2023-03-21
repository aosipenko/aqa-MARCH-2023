package org.prog.collections;

import java.util.Collection;
import java.util.HashMap;

public class MapDemo {

    public static void main(String... args) {
        HashMap<String, String> myStringMap = new HashMap<>();
        HashMap<String, Object> myObjectMap = new HashMap<>();

        myStringMap.put("key_1", "value_1");
        myObjectMap.put("key_1", "value_1");

        System.out.println(myStringMap.get("key_1").length());
        System.out.println(myObjectMap.get("key_1").hashCode());

//        myStringMap.put("key_2", "value_2");
//        myStringMap.put("key_3", "value_2");
//        myStringMap.put(null, "value_4");

//        System.out.println(myMap.size());
//
//        System.out.println(myMap.get("key_1"));
//        System.out.println(myMap.get("key_3"));
//        System.out.println(myMap.get("key_4"));
//        System.out.println(myMap.get(null));
//
//        myMap.put("key_1", "new_value_1");
//        System.out.println(myMap.get("key_1"));

//        Collection<String> values = myMap.values();
//        System.out.println(values.size());
    }
}
