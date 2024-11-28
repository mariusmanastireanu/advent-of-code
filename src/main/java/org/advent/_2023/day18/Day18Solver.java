package org.advent._2023.day18;

import org.advent.helper.AbstractSolver;
import org.advent.helper.Direction;

import java.util.Collection;

public abstract class Day18Solver extends AbstractSolver {

    private long result = 0L;

    @Override
    public Object getResult() {
        return result;
    }

    @Override
    public void solve(Collection<String> lines) {
        var xPoints = new int[lines.size() + 1];
        var yPoints = new int[lines.size() + 1];
        xPoints[0] = 0;
        yPoints[0] = 0;
        var index = 1;
        var lineLength = 0L;
        for (String line : lines) {
            var direction = getDirection(line);
            var steps = getSteps(line);
            xPoints[index] = xPoints[index - 1] + direction.getRowOffset() * steps;
            yPoints[index] = yPoints[index - 1] + direction.getColOffset() * steps;
            lineLength += Math.abs(xPoints[index] - xPoints[index - 1]) + Math.abs(yPoints[index] - yPoints[index - 1]);
            index++;
        }
        result = calculatePolygonArea(xPoints, yPoints) + lineLength/2 + 1;
    }

    private long calculatePolygonArea(int[] xPoints, int[] yPoints) {
        var sum = 0L;
        for (int i = 0; i < xPoints.length - 1; i++) {
            sum += (long) xPoints[i] * yPoints[i + 1] - (long) xPoints[i + 1] * yPoints[i];
        }
        return Math.abs(sum) / 2L;
    }

    protected abstract Direction getDirection(String line);
    protected abstract int getSteps(String line);

}
