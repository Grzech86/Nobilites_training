package Nobilities.ex57;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.IntConsumer;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.Stream;

public class Ex57 {


    public static void main(String[] args) {

        Map<List<Integer>, Integer> mapOfTriplesFrequenties = new HashMap<>();
//
        try (final var stream = Files.lines(Path.of("lotto.txt"))) {

            // przepianie ze strumienia do mapy występujących konfiguracji trójek z równoczesnym zliczeniem ilości ich wystąpień
            Stream<List<Integer>> steve = stream
                    .map(s -> s.split(" "))
                    .map(s -> s[2])
                    .map(s -> s.split(","))
                    .map(s -> Arrays.stream(s)
                            .collect(Collectors.toList()))
                    .map(s -> s.stream()
                            .map(v -> Integer.valueOf(v))
                            .collect(Collectors.toList()))
                    .flatMap(inputList -> {
                        final var tuples = new LinkedList<List<Integer>>();
                        for (int i = 0; i <= inputList.size() - 3; i++) {
                            for (int j = i + 1; j <= inputList.size() - 2; j++) {
                                for (int k = j + 1; k <= inputList.size() - 1; k++) {
                                    List<Integer> localInputList = List.of(inputList.get(i), inputList.get(j), inputList.get(k));
                                    tuples.add(localInputList);
                                }
                            }
                        }
                        return tuples.stream();
                    });

            final var map = steve
                    .collect(Collectors.groupingBy(v -> v))
                    .entrySet()
                    .stream()
                    .map(entry -> Map.entry(entry.getKey(), entry.getValue().size()))
                    .sorted(Comparator.<Map.Entry<List<Integer>, Integer>>comparingInt(Map.Entry::getValue).reversed())
                    .limit(5)
                    .collect(Collectors.toMap(entry -> entry.getKey(), entry -> entry.getValue()));

// posortowanie i odcięcie z listy 5 trójek, które wystąpiły najczęściej - zwraca listę kluczy
            final var top5Frequency = mapOfTriplesFrequenties.entrySet().stream()
//                .sorted(Comparator.comparing(Map.Entry::getValue))
//                    .sorted(Comparator.comparing(Map.Entry::getValue).reversed())
//                .sorted(Comparator.reverseOrder())
                    .limit(5)
                    .map(Map.Entry::getKey)
                    .collect(Collectors.toList());


            // wydrukowanie 5 trójek i ilości ich powtórzeń na podstawie kluczy z mapy
            top5Frequency.stream()
                    .forEach(v -> System.out.println(v + " " + mapOfTriplesFrequenties.get(v)));


        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
