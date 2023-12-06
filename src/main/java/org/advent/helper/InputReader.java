package org.advent.helper;

import lombok.experimental.UtilityClass;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@UtilityClass
public class InputReader {

    public static Collection<String> readFile(String filename) {
        try {
            var resourceUrl = InputReader.class.getResource("/" + filename);
            if (resourceUrl == null) {
                return Collections.emptyList();
            }
            var resourcePath = Paths.get(resourceUrl.toURI());
            return Files.readAllLines(resourcePath);
        } catch (Exception e) {
            // do nothing
            return Collections.emptyList();
        }
    }

    public static Set<Integer> extractSet(String numbers) {
        return extractCollection(numbers, Integer::parseInt, Collectors.toSet());
    }

    public static <T, C extends Collection<T>> C extractCollection(
            String numbers,
            Function<String, T> parser,
            Collector<T, ?, C> collector) {
        return Arrays.stream(numbers.trim().split("\\s"))
                .filter(s -> !s.isEmpty())
                .map(parser)
                .collect(collector);
    }

}
