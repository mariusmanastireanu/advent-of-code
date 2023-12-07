package org.advent.day3;

public class Day3 {

    private void part1() {
        var resolver = new Part1Solver("day3.txt");
        resolver.solve();
        System.out.println(resolver.getResult());
    }

    private void part2() {
        var resolver = new Part2Solver("day3.txt");
        resolver.solve();
        System.out.println(resolver.getResult());
    }

    public static void main(String[] args) {
        var problemSolver = new Day3();
        problemSolver.part1();
        problemSolver.part2();
    }

}
