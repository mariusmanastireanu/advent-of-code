package org.advent.day7;

public class Day7 {

    private void part1() {
        var problemSolver = new Part1Solver("7-1.txt");
        problemSolver.solve();
        System.out.println(problemSolver.getResult());
    }

    private void part2() {
        var problemSolver = new Part2Solver("7-2.txt");
        problemSolver.solve();
        System.out.println(problemSolver.getResult());
    }

    public static void main(String[] args) {
        var problemSolver = new Day7();
        problemSolver.part1();
        problemSolver.part2();
    }

}
