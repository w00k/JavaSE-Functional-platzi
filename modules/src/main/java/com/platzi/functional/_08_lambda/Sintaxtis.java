package com.platzi.functional._08_lambda;

import com.platzi.functional._06_reference_operator.NombresUtils;

import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Predicate;

public class Sintaxtis {

    public static void main(String[] args) {
        List<String> cursos = NombresUtils.getList("Java", "Funcional");

        //Lamda, funcion anonima. Logica muy simple
        cursos.forEach(curso -> System.out.println(curso));

        usarZero(() -> 2);

        usarPredicado(text -> text.isEmpty());

        usarBiFunction((x, y) -> x * y);

        //por ser interfaces llama a clases y objetos, no podemos usar los tipos de datos primitivos
        //en algunos casos se usa este metodos, pero no es frecuente
        usarBiFunction((Integer x, Integer y) -> x * y);

        usarBiFunction((x, y) -> {
            System.out.println("x:" + x + ", y:" + y);
            return x - y;
        });

        //no recibe ningun dato y no retorna ningun dato
        usarNada(() -> {});
    }

    static void usarNada(OperarNada operarNada){
    }

    //metodos static son hight order functions que toman funciones y operan esas funciones
    static void usarBiFunction(BiFunction<Integer, Integer, Integer> operacion) {
    }

    static void usarPredicado(Predicate<String> predicado) {
    }

    static void usarZero(ZeroArgumentos zeroArgumentos) {
    }

    @FunctionalInterface
    interface OperarNada {
        void nada();
    }

    @FunctionalInterface
    interface ZeroArgumentos {
        int get();
    }
}
