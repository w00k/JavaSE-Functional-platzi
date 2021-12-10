package com.platzi.functional._13_partial_application;

import com.platzi.functional._06_reference_operator.NombresUtils;

import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.stream.Stream;

public class Streams {

    public static void main(String[] args) {
        List<String> courseList = NombresUtils.getList("Java", "Frontend", "Backend", "Fullsatck");

        for(String course: courseList) {
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
    }
}
