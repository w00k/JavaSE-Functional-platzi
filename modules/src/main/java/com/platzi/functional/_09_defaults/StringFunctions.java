package com.platzi.functional._09_defaults;

public class StringFunctions {

    @FunctionalInterface
    interface StringOperation {
        int getAmount();
        default void operate(String texto) {
            int x = getAmount();
            while(x-- > 0) {
                System.out.println(texto);
            }
        }
    }

    @FunctionalInterface
    interface DoOperation {
        void take(String text);

        default void execute(int x, String text) {
            while(x-- > 0) {
                take(text);
            }
        }
    }

    public static void main(String[] args) {

        StringOperation six = () -> 6;
        six.operate("Alumno");

        System.out.println("---");

        DoOperation operationFive = text -> System.out.println(text);
        operationFive.execute(5,"Platzi");

        System.out.println("---");

        operationFive.take("One time");
    }
}
