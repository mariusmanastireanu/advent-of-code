package org.advent.day5;

public class Day5 {

    private void part1() {
        var problemSolver = new Part1Solver("day5.txt");
        problemSolver.solve();
        System.out.println(problemSolver.getResult());
    }

    private void part2() {
        var problemSolver = new Part2Solver("day5.txt");
        problemSolver.solve();
        System.out.println(problemSolver.getResult());
    }

    public static void main(String[] args) {
        var problemSolver = new Day5();
        problemSolver.part1();
        problemSolver.part2();
    }

}
