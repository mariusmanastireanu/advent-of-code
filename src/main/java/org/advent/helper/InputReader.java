package org.advent.helper;

import lombok.experimental.UtilityClass;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.function.Function;
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
        return extractSet(numbers, Integer::parseInt);
    }

    public static <T> Set<T> extractSet(String numbers, Function<String, T> parser) {
        return Arrays.stream(numbers.trim().split("\\s"))
                .filter(s -> !s.isEmpty())
                .map(parser)
                .collect(Collectors.toSet());
    }

}
