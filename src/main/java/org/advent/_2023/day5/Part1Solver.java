package org.advent._2023.day5;

import org.advent.helper.InputReader;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Part1Solver extends Day5Solver {

    @Override
    protected Stream<Long> extractSeeds(String line) {
        return InputReader.extractCollection(line.split(":")[1], Long::parseLong, Collectors.toSet()).stream();
    }

}
