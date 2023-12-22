package org.advent.day22;

import java.util.Collection;

public class Part1Solver extends Day22Solver {

    @Override
    public void solve(Collection<String> lines) {
        populateSupportMaps(lines);
        result = collectRemovableBricks().size();
    }

}
