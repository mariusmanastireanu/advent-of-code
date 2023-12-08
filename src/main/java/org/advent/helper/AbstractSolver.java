package org.advent.helper;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Collection;

@Getter
public abstract class AbstractSolver {

    public abstract Object getResult();

    public abstract void solve(Collection<String> lines);

}
