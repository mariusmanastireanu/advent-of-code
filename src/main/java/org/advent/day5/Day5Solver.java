package org.advent.day5;

import org.advent.helper.AbstractSolver;

import java.util.Collection;
import java.util.Map;
import java.util.stream.Stream;

public abstract class Day5Solver extends AbstractSolver {

    protected Stream<Long> seeds = null;

    protected final AlmanacList seedToSoil = new AlmanacList();
    protected final AlmanacList soilToFertilizer = new AlmanacList();
    protected final AlmanacList fertilizerToWater = new AlmanacList();
    protected final AlmanacList waterToLight = new AlmanacList();
    protected final AlmanacList lightToTemperature = new AlmanacList();
    protected final AlmanacList temperatureToHumidity = new AlmanacList();
    protected final AlmanacList humidityToLocation = new AlmanacList();

    protected long result = 0L;

    protected final Map<String, AlmanacList> almanacMap = Map.of(
            "seed-to-soil map:", seedToSoil,
            "soil-to-fertilizer map:", soilToFertilizer,
            "fertilizer-to-water map:", fertilizerToWater,
            "water-to-light map:", waterToLight,
            "light-to-temperature map:", lightToTemperature,
            "temperature-to-humidity map:", temperatureToHumidity,
            "humidity-to-location map:", humidityToLocation
    );

    @Override
    public void solve(Collection<String> lines) {
        AlmanacList almanacList = null;
        for (String line : lines) {
            if (line.startsWith("seeds:")) {
                seeds = extractSeeds(line);
            } else if (almanacMap.containsKey(line)) {
                almanacList = almanacMap.get(line);
            } else if (!line.isBlank() && almanacList != null) {
                almanacList.getAlmanacMaps().add(new AlmanacMap(line));
            }
        }

        result = seeds.parallel()
                .map(seedToSoil::getDestination)
                .map(soilToFertilizer::getDestination)
                .map(fertilizerToWater::getDestination)
                .map(waterToLight::getDestination)
                .map(lightToTemperature::getDestination)
                .map(temperatureToHumidity::getDestination)
                .map(humidityToLocation::getDestination)
                .min(Long::compareTo).orElse(0L);
    }

    @Override
    public Object getResult() {
        return result;
    }

    protected abstract Stream<Long> extractSeeds(String line);

}
