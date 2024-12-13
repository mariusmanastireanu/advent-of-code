package org.advent._2024.day13;

import org.advent.helper.AbstractSolver;
import org.advent.helper.Pair;
import org.apache.commons.math3.linear.LUDecomposition;
import org.apache.commons.math3.linear.MatrixUtils;

import java.util.Collection;

public abstract class Day13Solver extends AbstractSolver {

    private Pair<Integer, Integer> buttonA;
    private Pair<Integer, Integer> buttonB;
    private Pair<Integer, Integer> prize;

    private long result = 0L;

    @Override
    public void solve(Collection<String> lines) {
        for (String line : lines) {
            if (line.startsWith("Button A:")) {
                buttonA = buildButton(line);
            } else if (line.startsWith("Button B:")) {
                buttonB = buildButton(line);
            } else if (line.startsWith("Prize:")) {
                prize = buildPoint(line, 7, "=");
            } else {
                result += getResultForMachine();
                resetState();
            }
        }
    }

    private Pair<Integer, Integer> buildButton(String line) {
        return buildPoint(line, 9, "+");
    }

    private Pair<Integer, Integer> buildPoint(String line, int substringStart, String separator) {
        line = line.substring(substringStart);
        var tokens = line.split(",");
        var x = Integer.parseInt(tokens[0].substring(tokens[0].indexOf(separator) + 1));
        var y = Integer.parseInt(tokens[1].substring(tokens[1].indexOf(separator) + 1));
        return new Pair<>(x, y);
    }

    private long getResultForMachine() {
        if (buttonA == null || buttonB == null || prize == null) {
            return 0;
        }
        // Create the coefficient matrix and the constants vector
        var matrix = MatrixUtils.createRealMatrix(new double[][]{
                new double[]{buttonA.getFirst(), buttonB.getFirst()},
                new double[]{buttonA.getSecond(), buttonB.getSecond()}
        });
        var vector = MatrixUtils.createRealVector(new double[]{
                prize.getFirst() + getMultiplicationFactor(),
                prize.getSecond() + getMultiplicationFactor()
        });

        // Solve the system of equations
        var solution = new LUDecomposition(matrix).getSolver().solve(vector);
        var tokens = solution.toString()
                .replace("{","")
                .replace("}","")
                .replace(",", "")
                .replace(" ", "")
                .split(";");

        // Extract the values of a and b
        if (isCloseToInteger(tokens[0]) && isCloseToInteger(tokens[1])) {
            var longA = Math.round(Double.parseDouble(tokens[0]));
            var longB = Math.round(Double.parseDouble(tokens[1]));
            return longA * 3 + longB;
        } else {
            return 0;
        }
    }

    private boolean isCloseToInteger(String number) {
        return !number.contains(".") || number.contains(".0000") || number.contains(".9999");
    }

    private void resetState() {
        buttonA = null;
        buttonB = null;
        prize = null;
    }

    protected abstract long getMultiplicationFactor();

    @Override
    public Object getResult() {
        return result;
    }

}
