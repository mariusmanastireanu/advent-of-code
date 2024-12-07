package org.advent._2024.day07;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;

@Getter
@RequiredArgsConstructor
public enum Operation {

    ADDITION(Long::sum),
    MULTIPLICATION((a, b) -> a * b),
    CONCATENATION((a, b) -> Long.parseLong(a + "" + b)),
    ;

    private final BiFunction<Long, Long, Long> function;

    public static List<List<Operation>> generateCombinations(int numberOfCombinations, List<Operation> operations) {
        List<List<Operation>> result = new ArrayList<>();
        generateCombinationsHelper(numberOfCombinations, operations, new ArrayList<>(), result);
        return result;
    }

    private static void generateCombinationsHelper(int numberOfCombinations, List<Operation> operations, List<Operation> current, List<List<Operation>> result) {
        if (current.size() == numberOfCombinations) {
            result.add(new ArrayList<>(current));
            return;
        }

        for (Operation op : operations) {
            current.add(op);
            generateCombinationsHelper(numberOfCombinations, operations, current, result);
            current.removeLast();
        }
    }
}
