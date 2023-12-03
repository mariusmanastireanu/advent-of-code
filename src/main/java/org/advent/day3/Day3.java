package org.advent.day3;

public class Day3 {

    private void part1() {
        var resolver = new Part1Resolver("3-1.txt");
        resolver.resolve();
        System.out.println(resolver.getResult());
    }

    private void part2() {
        var resolver = new Part2Resolver("3-2.txt");
        resolver.resolve();
        System.out.println(resolver.getResult());
    }

    public static void main(String[] args) {
        var problemSolver = new Day3();
        problemSolver.part1();
        problemSolver.part2();
    }

}
