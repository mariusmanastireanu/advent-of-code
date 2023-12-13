package org.advent.day13;

import org.advent.day13.model.AbstractPattern;
import org.advent.day13.model.PatternPart1;

import java.util.List;

public class Part1Solver extends Day13Solver {
    @Override
    protected AbstractPattern createPattern(List<String> lines) {
        return new PatternPart1(lines);
    }
}
