package org.advent._2024.day09;

public class Part1Solver extends Day09Solver {

    @Override
    protected void moveFiles() {
        int backCount = 1;
        for (int i = 0; i < system.size(); i++) {
            var number = system.get(i);
            if (number == Integer.MIN_VALUE) {
                if (i > system.size() - backCount) {
                    break;
                }
                while (number == Integer.MIN_VALUE) {
                    number = system.get(system.size() - backCount++);
                }
                system.set(i, number);
                system.set(system.size() - backCount + 1, Integer.MIN_VALUE);
            }
        }
    }
}
