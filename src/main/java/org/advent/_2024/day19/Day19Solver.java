package org.advent._2024.day19;

import org.advent.helper.AbstractSolver;

import java.util.*;

public abstract class Day19Solver extends AbstractSolver {

    private List<String> availablePatterns;

    private long count = 0L;

    @Override
    public void solve(Collection<String> lines) {
        var iterator = lines.iterator();
        availablePatterns = Arrays.asList(iterator.next().split(", "));
        while (iterator.hasNext()) {
            var line = iterator.next();
            if (line.isEmpty()) {
                continue;
            }
            count += countSolutions(line);
        }
    }

    protected abstract long countSolutions(String input);

    protected long countCombinations(String input) {
        int n = input.length();
        // this array will store the number of ways to form each substring of the input string
        long[] dp = new long[n + 1];
        // empty string can be formed in 1 way (by not selecting any pattern)
        dp[0] = 1;

        for (int i = 1; i <= n; i++) {
            for (String pattern : availablePatterns) {
                int len = pattern.length();
                if (i >= len && input.substring(i - len, i).equals(pattern)) {
                    // if a pattern matches the substring ending at position i,
                    // we add the number of ways to form the substring ending at position i - len to dp[i]
                    dp[i] += dp[i - len];
                }
            }
        }

        return dp[n];
    }

    @Override
    public Object getResult() {
        return count;
    }
}
