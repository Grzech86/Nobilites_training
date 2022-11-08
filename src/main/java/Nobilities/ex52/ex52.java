package Nobilities.ex52;//package ex52;
////# Grades Stream
////        Calculate an average grade of students using Java Streams.
////        java
//
//import java.util.Arrays;
//import java.util.List;
//import java.util.OptionalDouble;
//
//public class ex52 {
//
//    private final static String[] input = {
//            //name, age, city, grade, faculties
//            "Kasia,22,Warsaw,76.5,MATHEMATICS,COMPUTER_SCIENCE,PHYSICS,",
//            "Basia,25,Warsaw,94.3,PHYSICS",
//            "Piotr,19,Krakow,82.5,ART_HISTORY,JOURNALISM",
//            "Krzysztof,21,Krakow,72.4,JOURNALISM",
//            "Gosia,23,Poznan,99.6,MATHEMATICS,ART_HISTORY",
//            "Artur,24,Krakow,71.4,COMPUTER_SCIENCE,PHYSICS",
//            "Marcin,20,Warsaw,81.5,JOURNALISM,ART_HISTORY",
//            "Marta,21,Poznan,95.8,COMPUTER_SCIENCE,MATHEMATICS"
//    };
//    public static void main(String[] args) {
//        OptionalDouble average1 = Arrays.stream(input)
//                .parallel()
//                .map(line -> Student.decode(line))                  // .map(Student::decode)
//                .mapToDouble(student -> student.getGrade())         //.mapToDouble(Student::getGrade)
//                .average();
//
//        average1.ifPresent(v -> System.out.println(v));
//
//
//
//        OptionalDouble average2 = Arrays.stream(input)
//                .parallel()
//                .map(Student::decode)
//                .mapToDouble(Student::getGrade)
//                .average();
//
//        average2.ifPresent(v -> System.out.println(v));
//
//    }
//}