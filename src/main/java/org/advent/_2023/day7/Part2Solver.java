package org.advent._2023.day7;

import org.advent._2023.day7.model.Hand;
import org.advent._2023.day7.model.HandWithJoker;

public class Part2Solver extends Day7Solver {
    @Override
    protected Hand createHand(String line) {
        return new HandWithJoker(line.split(" ")[0], Long.parseLong(line.split(" ")[1].trim()));
    }

}
