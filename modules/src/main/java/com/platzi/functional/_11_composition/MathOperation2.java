package com.platzi.functional._11_composition;

import java.util.function.Function;

public class MathOperation2 {

    /*
    * Funciones de orden mayor, una funcion que toma como parametro otra funcion
    * o devuelve como parametro una funcion
    * incluso puede ser los 2 casos
    * */
    public static void main(String[] args) {

        Function<Integer, Integer> multiplicationBy3 = x -> {
            System.out.println("Multiplico por 3 el numero " + x);
            return x * 3;
        };

        //agrega un paso antes de ejecutar la funcion multiplicationBy3
        Function<Integer, Integer> add1MultipliBy3 = multiplicationBy3.compose(y -> {
                System.out.println("Le agregare 1 a " + y);
                return y + 1;
        });



        //agrega un paso posterior de ejecutar la funcion multiplicationBy3
        Function<Integer, Integer> andSquare = multiplicationBy3.andThen(
                w -> {
                    System.out.println("Estoy elevando a 2 el numero " + w);
                    return w * w;
                }
        );

        //ejecuta el add1MultipliBy3, multiplicationBy3 y andSquare
        Function<Integer, Integer> doAll = add1MultipliBy3.andThen(
                w -> {
                    System.out.println("Estoy elevando a 2 el numero " + w);
                    return w * w;
                }
        );

        System.out.println(add1MultipliBy3.apply(5));
        System.out.println(andSquare.apply(2));

        System.out.println(doAll.apply(5));

    }
}
