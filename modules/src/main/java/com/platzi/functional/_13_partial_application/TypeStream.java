package com.platzi.functional._13_partial_application;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class TypeStream {

    public static void main(String[] args) {
        IntStream infiniteStream = IntStream.iterate(0, x -> x + 1);
        List<Integer> numberList = infiniteStream.limit(1000)
                .parallel() //opcion para ejecutar de una forma concurrente, mejor para miles y millones, no usar con pocos datos o que te importe el orden de los datos
                //.filter(x -> x % 2 == 0)
                .boxed()
                //.forEach(System.out::println);
                .collect(Collectors.toList());

        numberList.forEach(System.out::println);
    }

    /* TODO
    * Las operaciones terminales más comunes que se encuentran en Stream son:

    * Validaciones, retorna boolean
      anyMatch()
      allMatch()
      noneMatch()

    * Busqueda, retorna Optional.
    * La principal razón para usar estas operaciones es poder usar los elementos de un Stream después haber filtrado y
    * convertido tipos de datos. Con Optional nos aseguramos que, aún si no hubiera resultados, podremos seguir
    * trabajando sin excepciones o escribiendo condicionales para validar los datos.
    findAny()
    findFirst()

    * Operaciones de reducción, retorna Optional
    * Son dos operaciones cuya finalidad es obtener el elemento más pequeño (min) o el elemento más grande (max) de un
    * Stream usando un Comparator. Puede haber casos de Stream vacíos, es por ello que las dos operaciones retornan
    * un Optional para en esos casos poder usar Optional.empty.
    min()
    max()

    * La diferencia entre los 3 tipos de invocación:
    - reduce(valorInicial, BinaryOperator)
      Retorna un valor del mismo tipo que el Stream después de aplicar BinaryOperator sobre cada elemento del
      Stream. En caso de un Stream vacío, el valorInicial es retornado.

    - reduce(BinaryAccumulator)
      Retorna un Optional del mismo tipo que el Stream, con un solo valor resultante de aplicar el BinaryAccumulator
      sobre cada elemento o Optional.empty() si el stream estaba vacío. Puede generar un NullPointerException en
      casos donde el resultado de BinaryAccumulator sea null.

    - reduce(valorInicial, BinaryFunction, BinaryOperator)
    Genera un valor de tipo V después de aplicar BinaryFunction sobre cada elemento de tipo T en el Stream
    y obtener un resultado V.

    * Esta version de reduce usa el BinaryFunction como map + reduce. Es decir, por cada elemento en el Stream se
    * genera un valor V basado en el valorInicial y el resultado anterior de la BinaryFunction.
    * BinaryOperator se utiliza en streams paralelos (stream.parallel()) para determinar el valor que se debe mantener
    * en cada iteración.
    reduce()

    * Una operación sencilla: sirve para obtener cuantos elementos hay en el Stream.
    count()

    * Agrega todos los elementos del Stream a un arreglo y nos retorna dicho arreglo. La operación genera un Object[],
    * pero es sposible hacer castings al tipo de dato del Stream.
    toArray()

    * Collector es una interfaz que tomara datos de tipo T del Stream, un tipo de dato mutable A, donde se irán
    * agregando los elementos (mutable implica que podemos cambiar su contenido, como un LinkedList) y
    * generara un resultado de tipo R.
    * Usando java.util.stream.Collectors podemos convertir sencillamente un Stream en un Set, Map, List, Collection,
    * etc. La clase Collectors ya cuenta con métodos para generar un Collector que corresponda con el tipo de dato
    * que tu Stream esta usando. Incluso vale la pena resaltar que Collectors puede generar un ConcurrentMap que
    * puede ser de utilidad si requieres de multiples threads.
    collect()

    * Tan simple y tan lindo como un clásico for. forEach es una operación que recibe un Consumer y no tiene un valor
    * de retorno (void). La principal utilidad de esta operación es dar un uso final a los elementos del Stream.
    forEach()

    * Conclusion
    * Las operaciones terminales se encargan de dar un fin y liberar el espacio usado por un Stream. Son también la
    * manera de romper los encadenamientos de métodos entre streams y regresar a nuestro código a un punto de ejecución
    * lineal. Como su nombre lo indica, por lo general, son la ultima operación presente cuando escribes chaining.
    *
    Stream infiniteStream = Stream.iterate(0, x -> x + 1);
    List numbersList = infiniteStream.limit(1000)
         .filter(x -> x % 2 == 0) // Operación intermedia
         .map(x -> x * 3) //Operación intermedia
         .collect(Collectors.toList()); //Operación final

    *
    * Por ultimo, recuerda que una vez que has agregado una operación a un Stream, el Stream original ya no puede ser
    * utilizado. Y más aun al agregar una operación terminal, pues esta ya no crea un nuevo Stream. Internamente,
    * al recibir una operación, el Stream en algún punto llama a su método close, que se encarga de liberar los datos
    * y la memoria del Stream.
    *
    * */

    /*
    * TODO
    *  Existen 2 tipos de operaciones, la intermedias y las finales.
    *
    * Operación intermedia a toda operación dentro de un Stream que como resultado devuelva un nuevo Stream. Es decir,
    * tras invocar una operación intermedia con un cierto tipo de dato, obtendremos como resultado un nuevo Stream
    * conteniendo los datos ya modificados.
    * El Stream que recibe la operación intermedia pasa a ser “consumido” posterior a la invocación de la operación,
    * quedando inutilizable para posteriores operaciones. Si decidimos usar el Stream para algún otro tipo de
    * operaciones tendremos un IllegalStateException.
    *
    * Operaciones disponibles:
    * filter(...)
    Filtra el stream como por ejemplo
    Stream filter(Predicatesuper T> predicate) --- de vuelve un tipo T --- clase 11
    Mientras más sencillo sean los filtros, más legible sera tu codigo

    * map(...)
    Stream map(Functionsuper T, ? extends R> mapper) --- toma u tipo T y generará un tipo R
    Stream ids = DatabaseUtils.getIds().stream();
    Stream users = ids.map(id -> db.getUserWithId(id));
    Esto es bastante practico cuando queremos hacer alguna conversión de datos y realmente no nos interese el dato
    completo, solo partes de él o si queremos convertir un dato complejo partiendo de un dato base.

    * flatMap(...)
    Pasa de Stream a Stream y simplifica la estructura del Stream.
    flatMap toma el Stream y usará el resultado de mapper para “acumular” elementos en un Stream desde otro Stream.

    * distinct(...)
    Lo que hace es comparar cada elemento del Stream contra el resto usando el método equals. De esta manera, evita
    que el Stream contenga elementos duplicados. La operación, al ser intermedia, retorna un nuevo Stream donde los
    elementos son únicos. Recuerda que para garantizar esto es recomendable que sobrescribas el método equals en tus
    clases que representen datos.

    * limit(...)
    La operación limit recibe un long que determina cuántos elementos del Stream original seran preservados. Si el
    número es mayor a la cantidad inicial de elementos en el Stream, básicamente, todos los elementos seguirán en el
    Stream. Un detalle interesante es que algunas implementaciones de Stream pueden estar ordenadas (sorted()) o
    explícitamente no ordenadas (unordered()), lo que puede cambiar drásticamente el resultado de la operación
    y el performance.

    * peek(...)
    peek funciona como una lupa, como un momento de observación de lo que está pasando en el Stream. Lo que hace esta
    operación es tomar un Consumer, pasar los datos conforme van estando presentes en el Stream y generar un nuevo
    Stream idéntico para poder seguir operando.

    * skip(...)
    Esta operación es contraria a limit(). Mientras limit() reduce los elementos presentes en el Stream a un numero
    especifico, skip descarta los primeros n elementos y genera un Stream con los elementos restantes en el Stream.

    * sorted(...)
    La operación sorted() requiere que los elementos presentes en el Stream implementen la interfaz Comparable para
    poder hacer un ordenamiento de manera natural dentro del Stream. El Stream resultante contiene todos los elementos
    pero ya ordenados, hacer un ordenamiento tiene muchas ventajas.

    *
    Las operaciones intermedias nos permiten tener control sobre los streams y manipular sus contenidos de manera
    sencilla sin preocuparnos realmente por cómo se realizan los cambios.

    Recuerda que las operaciones intermedias tienen la funcionalidad de generar nuevos streams que podremos dar como
    resultado para que otras partes del código los puedan utilizar.

    Aunque existen otras operaciones intermedias en diferentes implementaciones de Stream, las que aquí listamos están
    presentes en la interfaz base, por lo que entender estas operaciones te facilitara la vida en la mayoría de los
    usos de Stream.

    * */
}
