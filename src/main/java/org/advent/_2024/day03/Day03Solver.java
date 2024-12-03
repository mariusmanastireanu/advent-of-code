package org.advent._2024.day03;

import org.advent.helper.AbstractSolver;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.regex.Pattern;

public abstract class Day03Solver extends AbstractSolver {

    private final Pattern multiplyPattern = Pattern.compile("mul\\((\\d{1,3}),(\\d{1,3})\\)");
    private BigDecimal result = BigDecimal.ZERO;

    @Override
    public void solve(Collection<String> lines) {
        lines.forEach(this::processString);
    }

    protected void processString(final String line) {
        var matcher = multiplyPattern.matcher(line);
        while (matcher.find()) {
            processNumbers(Integer.parseInt(matcher.group(1)), Integer.parseInt(matcher.group(2)));
        }
    }

    private void processNumbers(final int a, final int b) {
        result = result.add(BigDecimal.valueOf(a).multiply(BigDecimal.valueOf(b)));
    }

    @Override
    public Object getResult() {
        return result;
    }

}
