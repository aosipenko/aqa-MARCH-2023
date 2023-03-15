package org.prog;

public class Main {

    /*
     **************************
     ******varTwo**************
     ********==****************
     *********varOne***********
     **************************
     ********01****************
     */

    public static void main(String[] args) {
        AnotherClass varOne = new AnotherClass();
        AnotherClass varTwo = new AnotherClass();
        AnotherClass varThree = varOne;
        AnotherClass varFour = varOne;
//        varOne.aString = "varOneValue";
//        varTwo.aString = "varTwoValue";


//        System.out.println(varOne.aString);
//        System.out.println(varTwo.aString);

        varOne.aString = "a";
        varTwo.aString = "a";

        System.out.println(varOne == varTwo);
        System.out.println(varOne == varThree);
        System.out.println(varThree == varFour);

//        boolean b = true;
//        char c = 1;
//        short s = 1;
//        int i = 0;
//        long l = 10;
//        double d = 1.00D;
//        float f = 5.24444F;

        // String pool
//        String myString = "a";
//        myString = myString + "b";
//        System.out.println(myString);
//
        Object o = new Object();
        "a".equals("a");
        o.equals(new Object());
        varOne.equals(varTwo);
    }
}