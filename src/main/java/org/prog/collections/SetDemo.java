package org.prog.collections;

import java.util.HashSet;
import java.util.Iterator;

public class SetDemo {
    public static void main(String... args) {
        HashSet<String> mySet = new HashSet<>();

        mySet.add("a");
        mySet.add("b");
        mySet.add("c");
        mySet.add("d");

        System.out.println(mySet.size());

        Iterator<String> i = mySet.iterator();
        while (i.hasNext()){
            System.out.println(i.next());
        }
    }
}
