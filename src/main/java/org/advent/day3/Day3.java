package org.advent.day3;

public class Day3 {

    private void part1() {
        var resolver = new Part1Solver("3-1.txt");
        resolver.solve();
        System.out.println(resolver.getResult());
    }

    private void part2() {
        var resolver = new Part2Solver("3-2.txt");
        resolver.solve();
        System.out.println(resolver.getResult());
    }

    public static void main(String[] args) {
        var problemSolver = new Day3();
        problemSolver.part1();
        problemSolver.part2();
    }

}
