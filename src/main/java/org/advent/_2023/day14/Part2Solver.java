package org.advent._2023.day14;

import org.advent._2023.day14.model.CycleTiltingPlatform;
import org.advent._2023.day14.model.Platform;

import java.util.ArrayList;
import java.util.Collection;

public class Part2Solver extends Day14Solver {
    @Override
    protected Platform createPlatform(Collection<String> lines) {
        return new CycleTiltingPlatform(new ArrayList<>(lines));
    }
}
