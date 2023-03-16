package org.prog.collections;

import java.util.ArrayList;

public class ListDemo {

    public static void main(String... arg) {
        ArrayList<String> aList = new ArrayList<>();
        ArrayList<String> bList = new ArrayList<>();
        aList.add("string");
        aList.add("string2");
        aList.add("string3");

        bList.add("string4");
        bList.add("string65");
        bList.add("1");

        aList.addAll(bList);

        aList.set(2, "asdasd");

//        for (int i = 0; i < aList.size(); i++) {
//            System.out.println(aList.get(i));
//        }
//
        for (String s : aList) {
            System.out.println(s);
        }

//        aList.forEach(s -> {
//            System.out.println(s);
//        });

        System.out.println(aList.size());
    }
}
