package org.advent._2024.day18;

public class Part2Solver extends Day18Solver {

    @Override
    public Object getResult() {
        for (int i = 0; i < lines.size(); i++) {
            processInputs(i);
            if (findShortestPath() == -1) {
                return lines.get(i - 1);
            }
        }
        return null;
    }

}
