package org.advent._2023.day14;

import org.advent._2023.day14.model.NorthTiltingPlatform;
import org.advent._2023.day14.model.Platform;

import java.util.ArrayList;
import java.util.Collection;

public class Part1Solver extends Day14Solver {
    @Override
    protected Platform createPlatform(Collection<String> lines) {
        return new NorthTiltingPlatform(new ArrayList<>(lines));
    }

}
