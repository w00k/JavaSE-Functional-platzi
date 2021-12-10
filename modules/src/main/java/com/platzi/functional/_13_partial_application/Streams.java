package com.platzi.functional._13_partial_application;

import com.platzi.functional._06_reference_operator.NombresUtils;

import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.stream.Stream;

public class Streams {

    public static void main(String[] args) {
        List<String> courseList = NombresUtils.getList("Java", "Frontend", "Backend", "Fullsatck");

        for (String course : courseList) {
            String newCourse = course.toLowerCase().replace("!", "!!!");
            System.out.println("Plati: " + newCourse);
        }

        //es la manera mas sencilla de generar un stream
        Stream<String> courseStream = Stream.of("Java", "Frontend", "Backend", "Fullsatck");

        //Stream<Integer> courseLengthStream = courseStream.map(course -> course.length());

        //Optional<Integer> longestName = courseLengthStream.max((x, y) -> y - x);

        Stream<String> emphasisCourses = courseStream.map(course -> course + "!");

        Stream<String> justJavaCourse = emphasisCourses.filter(course -> course.contains("Java"));

        justJavaCourse.forEach(System.out::println);

        //tomar collecciones y generar un stream de sus elementos
        Stream<String> coursesStream2 = courseList.stream();

        System.out.println();

        addOperator(
                coursesStream2.map(course -> course + "!!")
                        .filter(course -> course.contains("Java"))
        ).forEach(System.out::println);
        /*
         * los streams es lo mismo que generar variables, pero hace que el codigo tenga una mejor legividad.
         * no tenemos que ir almacenando el resultado en cada iteracion.
         * Los streams tiene 2 tipos de operaciones,
         *    1. operaciones intermedias, genera un nuevo stream
         *    2. operaciones terminales, genera un dato final, despues de operar todo
         * */

        List<String> remember = NombresUtils.getList(
                "Consumer<T>: recibe un dato de tipo T y no genera ningún resultado",
                "Function<T,R>: toma un dato de tipo T y genera un resultado de tipo R",
                "Predicate<T>: toma un dato de tipo T y evalúa si el dato cumple una condición",
                "Supplier<T>: no recibe ningún dato, pero genera un dato de tipo T cada vez que es invocado",
                "UnaryOperator<T> recibe un dato de tipo T y genera un resultado de tipo T"
        );

        System.out.println();

        remember.stream().forEach(System.out::println);
    }

    /*
     * esto es algo muy comun, generar funciones estaticas que de vuelvan un stream que ya tenga nuevas funciones.
     * */
    static <T> Stream<T> addOperator(Stream<T> stream) {
        return stream.peek(data -> System.out.println("Dato:" + data));
    }

    /* TODO
    * RECORDAR:
    Consumer<T>: recibe un dato de tipo T y no genera ningún resultado
    Function<T,R>: toma un dato de tipo T y genera un resultado de tipo R
    Predicate<T>: toma un dato de tipo T y evalúa si el dato cumple una condición
    Supplier<T>: no recibe ningún dato, pero genera un dato de tipo T cada vez que es invocado
    UnaryOperator<T> recibe un dato de tipo T y genera un resultado de tipo T
    *
    Collector<T, A, R> es una interfaz que tomará datos de tipo T del Stream, un tipo de dato mutable A, donde se iran
    agregando los elementos (mutable implica que podemos cambiar su contenido, como un LinkedList),
    y generara un resultado de tipo R
    *
    * S
    Estas interfaces (y otras más) sirven como la base de donde generar los objetos con las lambdas que pasamos a los
    diferentes métodos de Stream. Cada una de ellas cumple esencialmente con recibir el tipo de dato de el Stream y
    generar el tipo de retorno que el método espera.
    * */

}

