package com.platzi.functional._10_chaining;

public class ChaningTest {

    public static void main(String[] args) {

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Hola")
                .append("alumno")
                .append("de")
                .append("Platzi");

        Chainer chainer = new Chainer();
        chainer.sayHi().sayBye();
    }

    static class Chainer{

        public Chainer sayHi() {
            System.out.println("Hola");
            return this;
        }

        public Chainer sayBye() {
            System.out.println("Chao");
            return this;
        }

    }
}
