package org.advent._2023.day14.model;

import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public abstract class Platform {

    protected final List<String> rows;
    public abstract long tiltAndComputeWeight();

}
