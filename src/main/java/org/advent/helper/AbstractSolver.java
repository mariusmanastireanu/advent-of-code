package org.advent.helper;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Collection;

@Getter
@RequiredArgsConstructor
public abstract class AbstractSolver {

    private final String filename;
    public abstract Object getResult();

    public void solve() {
        solve(InputReader.readFile(filename));
    }

    public abstract void solve(Collection<String> lines);

}
