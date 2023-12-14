package org.advent.day14.model;

import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public abstract class Platform {

    protected final List<String> rows;
    public abstract long tiltAndComputeWeight();

}
