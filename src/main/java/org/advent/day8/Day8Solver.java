package org.advent.day8;

import org.advent.helper.AbstractSolver;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public abstract class Day8Solver extends AbstractSolver {

    protected Map<String, Node> nodes = new HashMap<>();
    private String instructions;
    protected long result;

    @Override
    public Object getResult() {
        return result;
    }

    protected void processInput(Collection<String> lines) {
        var index = 0;
        for (String line : lines) {
            if (index == 0) {
                instructions = line;
            } else if (index >= 2) {
                var tokens = line.split(" = ");
                nodes.put(tokens[0], new Node(tokens[1].substring(1, 4), tokens[1].substring(6, 9)));
            }
            index++;
        }
    }

    protected long solveForNode(String node) {
        var iterations = 0;
        while (!node.endsWith("Z")) {
            node = getNextNode(node, getNextInstruction(iterations++));
        }
        return iterations;
    }

    private char getNextInstruction(int iteration) {
        return instructions.charAt(iteration % instructions.length());
    }

    private String getNextNode(String node, char instruction) {
        return 'L' == instruction ? nodes.get(node).getLeftNode() : nodes.get(node).getRightNode();
    }

}
