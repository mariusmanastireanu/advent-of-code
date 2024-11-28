package org.advent._2023.day19;

import org.advent._2023.day19.model.Combination;
import org.advent._2023.day19.model.Condition;
import org.advent._2023.day19.model.Rule;

import java.util.Collection;

public class Part2Solver extends Day19Solver {

    @Override
    public void solve(Collection<String> lines) {
        super.solve(lines);
        result += solveRule(rulesMap.get("in"), new Combination());
    }

    private long solveRule(Rule rule, Combination combination) {
        long result = 0L;
        for (int i = 0; i < rule.getConditions().size(); i++) {
            var condition = rule.getConditions().get(i);
            if (i != 0) {
                combination = rule.getConditions().get(i - 1).alter(combination, true);
            }
            result += solveCondition(condition, combination);
        }
        return result;
    }

    private long solveCondition(Condition condition, Combination combination) {
        if ("R".equals(condition.getDestination())) {
            return 0L;
        } else if ("A".equals(condition.getDestination())) {
            return condition.alter(combination, false).getNumberOfCombinations();
        } else {
            return solveRule(rulesMap.get(condition.getDestination()), condition.alter(combination, false));
        }
    }

    @Override
    protected void processPart(String line) {
        // we ignore parts in part 2
    }
}
