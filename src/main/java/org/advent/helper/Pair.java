package org.advent.helper;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Pair<FIRST, SECOND> {

    private final FIRST first;
    private final SECOND second;

}
