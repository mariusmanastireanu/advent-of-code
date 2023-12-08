package org.advent.helper;

import lombok.experimental.UtilityClass;

@UtilityClass
public class DaySolver {

    public static void solve(String filename, AbstractSolver... solvers) {
        var index = 1;
        for (AbstractSolver solver : solvers) {
            solver.solve(InputReader.readFile(filename));
            System.out.println("Part " + index++ + ": " + solver.getResult());
        }
    }

}
