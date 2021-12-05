package com.platzi.functional._06_reference_operator;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class NombresUtils {

    public static <T> List<T> getList(T ... elements) {
        return Arrays.asList(elements);
    }

    public static void main(String[] args) {
        List<String> profesores = getList("Nicolas", "Juan", "Zulama");

        Consumer<String> printer = text -> System.out.println(text);
        profesores.forEach(printer);
        System.out.println("---");
        profesores.forEach(System.out::println);

        //podemos usar metodos definido en otros lados,
        //podemos usar el operador de refencia,
        // siempre y cuando utilice la misma cantidad de elementos y los mismos tipos de datos.
    }
}
