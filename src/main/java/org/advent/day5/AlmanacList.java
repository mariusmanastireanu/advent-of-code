package org.advent.day5;

import lombok.Builder;
import lombok.Data;

import java.util.Collection;
import java.util.LinkedList;

@Data
@Builder
public class AlmanacList {

    private final Collection<AlmanacMap> almanacMaps = new LinkedList<>();

    public long getDestination(long source) {
        return almanacMaps.stream()
                .filter(almanacMap -> almanacMap.isInRange(source))
                .findFirst()
                .map(almanacMap -> almanacMap.getDestination(source))
                .orElse(source);
    }

}
