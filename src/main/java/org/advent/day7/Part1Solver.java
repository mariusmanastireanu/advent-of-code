package org.advent.day7;

import org.advent.day7.model.Hand;

public class Part1Solver extends Day7Solver {
    public Part1Solver(String filename) {
        super(filename);
    }

    @Override
    protected Hand createHand(String line) {
        return new Hand(line.split(" ")[0], Long.parseLong(line.split(" ")[1].trim()));
    }

}
