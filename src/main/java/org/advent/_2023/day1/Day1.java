package org.advent._2023.day1;

import org.advent.helper.InputReader;

import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class Day1 {

    private static final Map<String, Integer> REPLACEMENTS = Map.of(
            "one", 1,
            "two", 2,
            "three", 3,
            "four", 4,
            "five", 5,
            "six", 6,
            "seven", 7,
            "eight", 8,
            "nine", 9,
            "zero", 0
    );

    public void part1() {
        var sum = new AtomicInteger();
        InputReader.readFile("_2023/day1.txt").forEach(line -> sum.getAndAdd(solveOneLine(line)));
        System.out.println(sum);
    }

    private int solveOneLine(String line) {
        int lastDigit = -1;
        var numberAsString = new StringBuilder();
        for (int i = 0; i < line.length(); i++) {
            if (Character.isDigit(line.charAt(i))) {
                if (lastDigit == -1) {
                    numberAsString.append(line.charAt(i));
                }
                lastDigit = Character.getNumericValue(line.charAt(i));
            }
        }
        numberAsString.append(lastDigit);
        return Integer.parseInt(numberAsString.toString());
    }

    private void part2() {
        var sum = new AtomicInteger();
        InputReader.readFile("_2023/day1.txt").stream()
                        .map(this::replaceNumbers)
                        .forEach(line -> sum.getAndAdd(solveOneLine(line)));
        System.out.println(sum);
    }

    private String replaceNumbers(String line) {
        var number = new StringBuilder();
        var startingIndex = -1;
        var iterator = 0;
        while (iterator < line.length()) {
            if (Character.isDigit(line.charAt(iterator))) {
                number = new StringBuilder();
                iterator++;
            } else {
                if (startingIndex == -1) {
                    startingIndex = iterator;
                }
                number.append(line.charAt(iterator));
                iterator++;
                if (REPLACEMENTS.containsKey(number.toString())) {
                    line = line.replaceFirst(number.toString(), REPLACEMENTS.get(number.toString()).toString() + number.substring(number.length() - 1));
                    return replaceNumbers(line);
                } else {
                    StringBuilder finalNumber = number;
                    if (REPLACEMENTS.keySet().stream().noneMatch(fullNumber -> fullNumber.startsWith(finalNumber.toString()))) {
                        number = new StringBuilder();
                        iterator = startingIndex + 1;
                        startingIndex = -1;
                    }
                }
            }
        }
        return line;
    }

    public static void main(String[] args) {
        var problemSolver = new Day1();
        problemSolver.part1();
        problemSolver.part2();
    }

}
