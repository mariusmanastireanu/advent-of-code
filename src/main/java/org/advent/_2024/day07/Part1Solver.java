package org.advent._2024.day07;

import java.util.Arrays;
import java.util.List;

public class Part1Solver extends Day07Solver {

    @Override
    protected List<Operation> getOperations() {
        return Arrays.asList(Operation.ADDITION, Operation.MULTIPLICATION);
    }

}
