package org.advent.day11;

import org.advent.helper.DaySolver;

public class Day11 {

    public static void main(String[] args) {
        DaySolver.solve("day11.txt", new Day11Solver(2), new Day11Solver(1_000_000));
    }

}
