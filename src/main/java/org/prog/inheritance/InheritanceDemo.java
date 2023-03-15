package org.prog.inheritance;

public class InheritanceDemo {

    public static void main(String... args) {
        ParentClass parent = new ParentClass();
        parent.parentString = "a";

        ChildOne childOne = new ChildOne();
        childOne.parentString = "b";
        childOne.childOneString = "c";

        GrandChildOne grandChildOne = new GrandChildOne();
        grandChildOne.parentString = "e";
        grandChildOne.childOneString = "d";
        grandChildOne.grandString = "f";

        ChildTwo childTwo = new ChildTwo();
        childTwo.childTwoString = "g";
    }
}
