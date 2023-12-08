package org.advent.day7;

import org.advent.day7.model.Hand;
import org.advent.day7.model.HandComparator;
import org.advent.helper.AbstractSolver;

import java.util.Collection;
import java.util.concurrent.atomic.AtomicInteger;

public abstract class Day7Solver extends AbstractSolver {

    private long result = 0L;

    @Override
    public Object getResult() {
        return result;
    }

    @Override
    public void solve(Collection<String> lines) {
        var index = new AtomicInteger(lines.size());
        result = lines.stream()
                .map(this::createHand)
                .sorted(new HandComparator())
                .map(hand -> index.getAndDecrement() * hand.getBet())
                .reduce(Long::sum).orElse(0L);
    }

    protected abstract Hand createHand(String line);

}
