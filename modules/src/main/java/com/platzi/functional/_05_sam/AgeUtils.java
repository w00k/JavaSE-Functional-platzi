package com.platzi.functional._05_sam;

import java.time.LocalDate;
import java.time.Period;
import java.util.function.Function;

public class AgeUtils {
    // interface type SAM, Single Abstract Method
    // con la anotacion Java sabe que puedo definir como funcion
    @FunctionalInterface
    interface TriFunction<T, U, V, R> {
        R apply(T t, U u, V v);
    }

    public static void main(String[] args) {

        //funcion para agregar ceros a la izquierda de un numero
        Function<Integer, String> addCeros = x -> x < 10 ? "0" + x : String.valueOf(x);

        //funcion para transformar una triada de numeros en fecha, con formato year-month-day
        TriFunction<Integer, Integer, Integer, LocalDate> parseDate = (day, month, year) ->
                LocalDate.parse(year + "-" + addCeros.apply(month) + "-" + addCeros.apply(day));

        //cuncion que compara la fecha inicial menos la fecha actual
        TriFunction<Integer, Integer, Integer, Integer> calculateAge = (day, month, year) -> Period.between(
                parseDate.apply(day, month, year), LocalDate.now()
        ).getYears();

        System.out.println(calculateAge.apply(10, 10, 1992));
    }
}
