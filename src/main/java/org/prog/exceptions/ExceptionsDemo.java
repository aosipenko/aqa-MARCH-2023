package org.prog.exceptions;

import java.io.FileNotFoundException;
import java.io.FileReader;

public class ExceptionsDemo {

    public static void main(String... args) {
        tryCatchFinally_1(null);
//        tryCatchFinally_1("a");
        System.out.println("this works now!");
    }

    private static void tryCatchFinally_1(String arg) {
        try {
            System.out.println("before error");
            tryCatchFinally_2(arg);
            System.out.println("this never works!");
        } catch (Throwable t) {
            System.out.println("this is catch!");
            throw new RuntimeException(t);
        } finally {
            System.out.println("this is finally");
        }
    }

    private static void tryCatchFinally_2(String arg) {
        try {
            tryCatchFinally_3(arg);
        } catch (Throwable t) {
            throw new RuntimeException(t);
        }
    }

    private static void tryCatchFinally_3(String arg) {
        tryCatchFinally_4(arg);
    }

    private static void tryCatchFinally_4(String arg) {
        System.out.println(arg.length());
    }

    private static void tryCatch(String arg) {
        try {
            tryCatchFinally_4(arg);
        } catch (Throwable throwable) {
            System.out.println("try catch");
        }
    }

    private static void tryFinally(String arg) {
        try {
            tryCatchFinally_4(arg);
        } finally {
            System.out.println("try finally");
        }
    }

    public static void checkExceptionExample() throws FileNotFoundException {
        FileReader r = new FileReader("");
    }
}
