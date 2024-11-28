package org.advent._2023.day19;

import org.advent._2023.day19.model.Rule;
import org.advent.helper.AbstractSolver;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public abstract class Day19Solver extends AbstractSolver {

    protected final Map<String, Rule> rulesMap = new HashMap<>();
    protected long result = 0L;

    @Override
    public Object getResult() {
        return result;
    }

    @Override
    public void solve(Collection<String> lines) {
        var processingRules = true;
        for (String line : lines) {
            if (line.isEmpty()) {
                processingRules = false;
            } else if (processingRules) {
                rulesMap.put(line.split("\\{")[0], new Rule(line.split("\\{")[1]));
            } else {
                processPart(line);
            }
        }
    }

    protected abstract void processPart(String line);
}
