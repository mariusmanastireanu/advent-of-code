package org.advent.helper;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Point {
    private int x;
    private int y;
}
