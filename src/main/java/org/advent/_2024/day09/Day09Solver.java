package org.advent._2024.day09;

import org.advent.helper.AbstractSolver;

import java.util.*;

public abstract class Day09Solver extends AbstractSolver {

    protected final List<Integer> system = new ArrayList<>();
    private long checksum = 0L;

    @Override
    public void solve(Collection<String> lines) {
        processInput(lines);
        moveFiles();
        computeChecksum();
    }

    private void processInput(final Collection<String> lines) {
        var id = 0;
        var arrayIndex = 0;
        var isFile = true;
        for (String line : lines) {
            for (Character c : line.toCharArray()) {
                var number = Integer.parseInt("" + c);
                for (int i = 0; i < number; i++) {
                    system.add(arrayIndex++, isFile ? id : Integer.MIN_VALUE);
                }
                if (isFile) {
                    id++;
                }
                isFile = !isFile;
            }
        }
    }

    protected abstract void moveFiles();

    private void computeChecksum() {
        for (int i = 0; i < system.size(); i++) {
            if (system.get(i) != Integer.MIN_VALUE) {
                checksum += i * system.get(i);
            }
        }
    }

    @Override
    public Object getResult() {
        return checksum;
    }
}
