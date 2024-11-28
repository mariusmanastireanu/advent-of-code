package org.advent._2023.day13;

import org.advent._2023.day13.model.AbstractPattern;
import org.advent._2023.day13.model.PatternPart2;

import java.util.List;

public class Part2Solver extends Day13Solver {
    @Override
    protected AbstractPattern createPattern(List<String> lines) {
        return new PatternPart2(lines);
    }
}
