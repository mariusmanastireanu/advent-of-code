package org.advent._2024.day18;

public class Part1Solver extends Day18Solver {

    @Override
    public Object getResult() {
        processInputs(1024);
        return findShortestPath();
    }

}
