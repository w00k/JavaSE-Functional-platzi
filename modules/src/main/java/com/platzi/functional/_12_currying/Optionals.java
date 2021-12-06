package com.platzi.functional._12_currying;

import java.util.*;

public class Optionals {

    static List<String> getNames() {
        List<String> list = new LinkedList<>();
        return Collections.emptyList();
    };

    static Optional<List<String>> getOptionalNames() {
        List<String> namesList = new LinkedList<>();
        //obtener los nombres
        return Optional.of(namesList);
    }

    static String motValuePlayer() {
        //return "";
        return null;
    }

    static Optional<String> optionalValuablePlayer() {
        //busqueda de datos
        //return Optional.ofNullable(); retorno algo que no se si es null

        //tambien podemos
        try {
            //accesos
            return Optional.of("w00kyx");
        } catch (Exception e) {
            e.printStackTrace();
        }
        //retorno un vacio
        return Optional.empty();
    }

    static Integer mostExpensiveItem() {
        return -1;
    }

    public static void main(String[] args) {
        List<String> names = getNames();
        if(names != null) {
            //Optional: operar con nombres
        }

        Optional<List<String>> optionalNames = getOptionalNames();
        if(optionalNames.isPresent()) {
            //optional
        }

        optionalNames.ifPresent(namesValues -> namesValues.forEach(System.out::println));

        Optional<String> valuablePlayer = optionalValuablePlayer();
        String valuablePlayerName = valuablePlayer.orElse(() -> "No Player");
    }
}
