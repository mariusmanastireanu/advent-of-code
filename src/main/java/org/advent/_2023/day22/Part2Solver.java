package org.advent._2023.day22;

import org.advent._2023.day22.model.Brick;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class Part2Solver extends Day22Solver {

    protected Set<Brick> visited = new HashSet<>();

    @Override
    public void solve(Collection<String> lines) {
        populateSupportMaps(lines);
        var removableBricks = new HashSet<>(supportsMap.keySet());
        removableBricks.removeAll(collectRemovableBricks());
        removableBricks.forEach(brick -> {
                    visited.clear();
                    result += countRemovedBricks(brick);
                });
        result -= removableBricks.size();
    }

    private int countRemovedBricks(Brick removableBrick) {
        if (!canRemoveBrick(removableBrick)) {
            return 0;
        }
        visited.add(removableBrick);
        var sum = 1;
        for (Brick supportedBrick : supportsMap.get(removableBrick)) {
            sum += countRemovedBricks(supportedBrick);
        }
        return sum;
    }

    protected boolean canRemoveBrick(Brick b) {
        return visited.isEmpty() || visited.containsAll(supportedBy.get(b));
    }

}
