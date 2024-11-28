package org.advent._2023.day22;

import org.advent._2023.day22.model.Brick;
import org.advent.helper.AbstractSolver;

import java.util.*;

public abstract class Day22Solver extends AbstractSolver {

    protected long result = 0L;
    private final Map<String, Brick> bricksOccupancyMap = new HashMap<>();
    protected Map<Brick, Set<Brick>> supportsMap = new HashMap<>();
    protected Map<Brick, Set<Brick>> supportedBy = new HashMap<>();

    @Override
    public Object getResult() {
        return result;
    }

    protected void populateSupportMaps(Collection<String> lines) {
        populateSupportMaps(getSortedBricks(lines));
    }

    private List<Brick> getSortedBricks(Collection<String> lines) {
        return lines.stream()
                .map(line -> {
                    var brick = new Brick(line);
                    supportsMap.put(brick, new HashSet<>());
                    supportedBy.put(brick, new HashSet<>());
                    return brick;
                })
                .sorted(Comparator.comparingInt(Brick::getMinZ))
                .toList();
    }

    private void populateSupportMaps(List<Brick> sortedBricks) {
        for (Brick currentBrick : sortedBricks) {
            var currentMinZ = currentBrick.getMinZ();
            var deltaZ = 0;
            var canDescend = true;
            while (currentMinZ - deltaZ > 0 && canDescend) {
                for (int x = currentBrick.getMinX(); x <= currentBrick.getMaxX(); x++) {
                    for (int y = currentBrick.getMinY(); y <= currentBrick.getMaxY(); y++) {
                        for (int z = currentBrick.getMinZ() - deltaZ; z <= currentBrick.getMaxZ() - deltaZ; z++) {
                            var key = x + "," + y + "," + z;
                            if (bricksOccupancyMap.containsKey(key)) {
                                supportsMap.get(bricksOccupancyMap.get(key)).add(currentBrick);
                                supportedBy.get(currentBrick).add(bricksOccupancyMap.get(key));
                                canDescend = false;
                            }
                        }
                    }
                }
                deltaZ++;
            }

            for (int x = currentBrick.getMinX(); x <= currentBrick.getMaxX(); x++) {
                for (int y = currentBrick.getMinY(); y <= currentBrick.getMaxY(); y++) {
                    for (int z = currentBrick.getMinZ() - deltaZ + (canDescend ? 1 : 2);
                         z <= currentBrick.getMaxZ() - deltaZ + (canDescend ? 1 : 2); z++) {
                        var key = x + "," + y + "," + z;
                        bricksOccupancyMap.put(key, currentBrick);
                    }
                }
            }
        }
    }

    protected Collection<Brick> collectRemovableBricks() {
        var removableBricks = new HashSet<Brick>();
        for (Map.Entry<Brick, Set<Brick>> entry : supportsMap.entrySet()) {
            if (entry.getValue().isEmpty()) {
                removableBricks.add(entry.getKey());
            } else {
                var canRemove = true;
                for (Brick brick : entry.getValue()) {
                    if (supportedBy.get(brick).size() == 1) {
                        canRemove = false;
                        break;
                    }
                }
                if (canRemove) {
                    removableBricks.add(entry.getKey());
                }
            }
        }
        return removableBricks;
    }

}
