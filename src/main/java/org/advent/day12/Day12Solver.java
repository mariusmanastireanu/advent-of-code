package org.advent.day12;

import org.advent.helper.AbstractSolver;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public abstract class Day12Solver extends AbstractSolver {

    protected long result = 0L;
    protected final Map<ConditionRecord, Long> cache = new HashMap<>();

    @Override
    public Object getResult() {
        return result;
    }

    @Override
    public void solve(Collection<String> lines) {
        result = lines.stream()
                .map(this::createConditionRecord)
                .map(this::getNumberOfSolutions)
                .mapToLong(Long::longValue)
                .sum();
    }

    protected abstract ConditionRecord createConditionRecord(String line);

    private long getNumberOfSolutions(ConditionRecord conditionRecord) {
        if (cache.containsKey(conditionRecord)) {
            return cache.get(conditionRecord);
        }
        var condition = conditionRecord.getCondition();
        var brokenGroups = conditionRecord.getBrokenRecordGroups();
        if (condition.isEmpty()) {
            // if there are no conditions left, this is a valid condition only if there are no broken groups left
            cache.put(conditionRecord, brokenGroups.isEmpty() ? 1L : 0L);
            return cache.get(conditionRecord);
        } else if (brokenGroups.isEmpty()) {
            // if there are no broken groups left, this is valid only if the condition does not contain broken groups (#)
            cache.put(conditionRecord, condition.contains("#") ? 0L : 1L);
            return cache.get(conditionRecord);
        } else {
            var solutions = 0L;
            if (condition.startsWith(".")) {
                // the first character is a break, so we can remove it and continue
                solutions += getNumberOfSolutions(new ConditionRecord(condition.substring(1), new ArrayList<>(brokenGroups)));
            } else if (condition.startsWith("#")) {
                // the first character can mark the beginning of a broken group
                if (condition.length() >= brokenGroups.stream().reduce(Integer::sum).orElse(0)
                        && !condition.substring(0, brokenGroups.get(0)).contains(".")
                        && (condition.length() == brokenGroups.get(0) || condition.charAt(brokenGroups.get(0)) != '#')) {
                    // if the condition is long enough to contain the broken group, and the broken group does not contain a break
                    solutions += getNumberOfSolutions(new ConditionRecord(condition.substring(
                            Math.min(condition.length(), brokenGroups.get(0) + 1)),
                            new ArrayList<>(brokenGroups.subList(1, brokenGroups.size()))));
                }
            } else {
                solutions += getNumberOfSolutions(new ConditionRecord(condition.replaceFirst("\\?", "."), new ArrayList<>(brokenGroups)));
                solutions += getNumberOfSolutions(new ConditionRecord(condition.replaceFirst("\\?", "#"), new ArrayList<>(brokenGroups)));
            }
            cache.put(conditionRecord, solutions);
            return cache.get(conditionRecord);
        }
    }
}
