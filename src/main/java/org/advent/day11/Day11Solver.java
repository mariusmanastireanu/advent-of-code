package org.advent.day11;

import org.advent.helper.AbstractSolver;

import java.util.Collection;

public class Day11Solver extends AbstractSolver {

    protected final int expandRate;
    protected long result = 0;


    public Day11Solver(int expandRate) {
        this.expandRate = expandRate;
    }

    @Override
    public Object getResult() {
        return result;
    }

    @Override
    public void solve(Collection<String> lines) {
        var universe = new Universe(lines);
        var indexMap = universe.getGalaxyIndexMap();
        for (int i = 1; i <= indexMap.keySet().size() - 1; i++) {
            for (int j = i + 1; j <= indexMap.keySet().size(); j++) {
                result += universe.computeDistance(indexMap.get(i), indexMap.get(j), expandRate);
            }
        }
    }

}
