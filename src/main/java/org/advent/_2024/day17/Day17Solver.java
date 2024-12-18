package org.advent._2024.day17;

import org.advent.helper.AbstractSolver;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public abstract class Day17Solver extends AbstractSolver {

    protected long a;
    protected long b;
    protected long c;
    protected List<Integer> instructions = new ArrayList<>();
    protected String originalProgram;

    protected StringBuilder result = new StringBuilder();

    @Override
    public void solve(Collection<String> lines) {
        processInput(lines);
        solve(instructions);
    }

    private void processInput(final Collection<String> lines) {
        for (String line : lines) {
            if (line.contains("A:")) {
                a = Long.parseLong(line.split(": ")[1]);
            } else if (line.contains("B:")) {
                b = Long.parseLong(line.split(": ")[1]);
            } else if (line.contains("C:")) {
                c = Long.parseLong(line.split(": ")[1]);
            } else if (line.contains("Program")) {
                originalProgram = line.split(": ")[1];
                Arrays.stream(line.split(": ")[1].split(",")).map(Integer::parseInt).forEach(instructions::add);
            }
        }
    }

    protected void solve(List<Integer> instructions) {
        int i = 0;
        while (i <= instructions.size() - 2) {
            if (shouldStop()) {
                break;
            }
            var jump = applyOperationToOperand(instructions.get(i), instructions.get(i + 1));
            if (jump) {
                i = instructions.get(i + 1);
            } else {
                i += 2;
            }
        }
    }

    protected boolean shouldStop() {
        return false;
    }

    protected boolean applyOperationToOperand(int operation, int operand) {
        if (operation == 0) {
            a = (int) (a / Math.pow(2, combo(operand)));
        } else if (operation == 1) {
            b = b ^ operand;
        } else if (operation == 2) {
            b = combo(operand) % 8;
        } else if (operation == 3 && a != 0) {
            return true;
        } else if (operation == 4) {
            b = b ^ c;
        } else if (operation == 5) {
            result.append(combo(operand) % 8).append(",");
        } else if (operation == 6) {
            b = (int) (a / Math.pow(2, combo(operand)));
        } else if (operation == 7) {
            c = (int) (a / Math.pow(2, combo(operand)));
        }
        return false;
    }

    protected long combo(int comboOperand) {
        return switch (comboOperand) {
            case 0, 1, 2, 3 -> comboOperand;
            case 4 -> a;
            case 5 -> b;
            case 6 -> c;
            default -> throw new IllegalArgumentException("Invalid combo operand: " + comboOperand);
        };
    }

    @Override
    public Object getResult() {
        result.deleteCharAt(result.length() - 1);
        return result.toString();
    }
}
