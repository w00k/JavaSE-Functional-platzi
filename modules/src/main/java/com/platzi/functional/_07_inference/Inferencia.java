package com.platzi.functional._07_inference;

import com.platzi.functional._06_reference_operator.NombresUtils;

import java.util.List;
import java.util.function.Function;

public class Inferencia {

    public static void main(String[] args) {
        //en tiempo de compilacion, adivina basado en la definicion que los objetos pertenecen al tipo de datos
        Function<Integer, String> funcionConvertidora = integer-> "Al doble ::: " + (integer * 2);

        List<String> alumnos = NombresUtils.getList("Hugo", "Paco", "Luis");
        alumnos.forEach((String name) -> System.out.println(name));
        System.out.println("---");
        alumnos.forEach(name -> System.out.println(name));
    }
}
