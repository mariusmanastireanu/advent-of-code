package org.advent._2024.day09;

import org.advent.helper.Pair;

public class Part2Solver extends Day09Solver {

    @Override
    protected void moveFiles() {
        var lastIndex = system.getLast();
        while (true) {
            var lastNumber = getNumber(lastIndex--);
            if (lastNumber == null) {
                break;
            }
            var gap = getFirstGap(lastNumber.getSecond() - lastNumber.getFirst() + 1, lastIndex + 1);
            if (gap == null) {
                if (lastIndex >= 0) {
                    continue;
                }
                break;
            }
            swapNumbers(gap, lastNumber);
        }
    }

    private Pair<Integer, Integer> getNumber(int value) {
        int endIndex = -1;
        for (int i = system.size() - 1; i >= 0; i--) {
            if (system.get(i) == value) {
                if (endIndex == -1) {
                    endIndex = i;
                }
            } else if (endIndex != -1) {
                return new Pair<>(i + 1, endIndex);
            }
        }
        return endIndex != -1 ? new Pair<>(0, endIndex) : null;
    }

    private Pair<Integer, Integer> getFirstGap(int desiredGapSize, int beforeIndex) {
        int startIndex = -1;
        int gapSize = 0;
        for (int i = 0; i < system.size(); i++) {
            if (system.get(i) == beforeIndex) {
                return null;
            }
            if (system.get(i) == Integer.MIN_VALUE) {
                if (startIndex == -1) {
                    startIndex = i;
                }
                gapSize++;
                if (gapSize == desiredGapSize) {
                    return new Pair<>(startIndex, i);
                }
            } else {
                startIndex = -1;
                gapSize = 0;
            }
        }
        return null;
    }

    private void swapNumbers(final Pair<Integer, Integer> gap, final Pair<Integer, Integer> lastNumber) {
        var iteration = 0;
        for (int i = gap.getFirst(); i <= gap.getSecond(); i++) {
            system.set(i, system.get(lastNumber.getFirst() + iteration++));
            system.set(lastNumber.getFirst() + iteration - 1, Integer.MIN_VALUE);
        }
    }

}
