package org.advent.day7;

import org.advent.day7.model.Hand;
import org.advent.day7.model.HandWithJoker;

public class Part2Solver extends Day7Solver {
    public Part2Solver(String filename) {
        super(filename);
    }

    @Override
    protected Hand createHand(String line) {
        return new HandWithJoker(line.split(" ")[0], Long.parseLong(line.split(" ")[1].trim()));
    }

}
