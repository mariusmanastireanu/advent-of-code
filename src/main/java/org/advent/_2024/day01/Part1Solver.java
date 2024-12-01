package org.advent._2024.day01;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Part1Solver extends Day01Solver {

    private final List<Integer> destinationList = new ArrayList<>();
    private Long result = 0L;

    @Override
    void processDestinationList(final int i) {
        destinationList.add(i);
    }

    @Override
    void computeResult() {
        var firstList = new ArrayList<>(sourceList);
        Collections.sort(firstList);
        Collections.sort(destinationList);
        var firstIterator = firstList.iterator();
        var secondIterator = destinationList.iterator();
        for (int i = 0; i < firstList.size(); i++) {
            result += Math.abs(secondIterator.next() - firstIterator.next());
        }
    }

    @Override
    public Object getResult() {
        return result;
    }

}
