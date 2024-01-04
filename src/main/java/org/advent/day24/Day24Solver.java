package org.advent.day24;

import org.advent.helper.AbstractSolver;
import org.advent.helper.Point;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public class Day24Solver extends AbstractSolver {

    private static final long LOWER_BOUND = 200000000000000L;
    private static final long UPPER_BOUND = 400000000000000L;

    private long result = 0L;

    @Override
    public Object getResult() {
        return result;
    }

    @Override
    public void solve(Collection<String> lines) {
        result = countIntersectingHailstones(lines.stream()
                .map(this::parseHailstone)
                .toList());
    }

    private Hailstone parseHailstone(String line) {
        var coordinatesTokens = line.split("@");
        var coordinates = coordinatesTokens[0].trim().split(",");
        var velocityTokens = coordinatesTokens[1].split(",");
        return new Hailstone(
                Long.parseLong(coordinates[0].trim()),
                Long.parseLong(coordinates[1].trim()),
                Long.parseLong(velocityTokens[0].trim()),
                Long.parseLong(velocityTokens[1].trim())
        );
    }

    private int countIntersectingHailstones(List<Hailstone> hailstoneList) {
        int count = 0;
        for (int firstIterator = 0; firstIterator < hailstoneList.size() - 1; firstIterator++) {
            var firstHailstone = hailstoneList.get(firstIterator);
            for (int secondIterator = firstIterator + 1; secondIterator < hailstoneList.size(); secondIterator++) {
                var secondHailstone = hailstoneList.get(secondIterator);
                var maybeIntersect = calculateIntersectionPoint(firstHailstone.getSlope(),
                        firstHailstone.getYIntercept(),
                        secondHailstone.getSlope(),
                        secondHailstone.getYIntercept());
                if (maybeIntersect.isEmpty()) {
                    continue;
                }
                var intersect = maybeIntersect.get();
                if (isIntersectInBounds(intersect)
                        && isIntersectInFuture(firstHailstone, intersect)
                        && isIntersectInFuture(secondHailstone, intersect)) {
                    count++;
                }
            }
        }
        return count;
    }

    public Optional<Point> calculateIntersectionPoint(double m1, double b1, double m2, double b2) {
        if (m1 == m2) {
            return Optional.empty();
        }
        double x = (b2 - b1) / (m1 - m2);
        double y = m1 * x + b1;
        return Optional.of(new Point(x, y));
    }

    private boolean isIntersectInBounds(Point intersect) {
        return intersect.getX() >= LOWER_BOUND
                && intersect.getX() <= UPPER_BOUND
                && intersect.getY() >= LOWER_BOUND
                && intersect.getY() <= UPPER_BOUND;
    }

    private boolean isIntersectInFuture(Hailstone hailstone, Point intersect) {
        final boolean futureX;
        if (hailstone.getVelocityX() < 0) {
            futureX = intersect.getX() <= hailstone.getX();
        } else {
            futureX = intersect.getX() > hailstone.getX();
        }
        if (!futureX) {
            return false;
        }
        final boolean futureY;
        if (hailstone.getVelocityY() < 0) {
            futureY = intersect.getY() <= hailstone.getY();
        } else {
            futureY = intersect.getY() > hailstone.getY();
        }
        return futureY;
    }

}
