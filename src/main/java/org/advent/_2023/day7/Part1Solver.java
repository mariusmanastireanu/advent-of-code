package org.advent._2023.day7;

import org.advent._2023.day7.model.Hand;

public class Part1Solver extends Day7Solver {
    @Override
    protected Hand createHand(String line) {
        return new Hand(line.split(" ")[0], Long.parseLong(line.split(" ")[1].trim()));
    }

}
