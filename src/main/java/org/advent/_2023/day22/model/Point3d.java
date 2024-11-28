package org.advent._2023.day22.model;

import lombok.Data;

@Data
public class Point3d {

    private final int x;
    private final int y;
    private final int z;

    public Point3d(String line) {
        var split = line.split(",");
        this.x = Integer.parseInt(split[0]);
        this.y = Integer.parseInt(split[1]);
        this.z = Integer.parseInt(split[2]);
    }

}
