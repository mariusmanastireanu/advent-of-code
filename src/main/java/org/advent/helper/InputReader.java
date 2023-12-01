package org.advent.helper;

import lombok.experimental.UtilityClass;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.Collections;

@UtilityClass
public class InputReader {

    public static Collection<String> readFile(String filename) {
        try {
            var resourceUrl = InputReader.class.getResource("/" + filename);
            if (resourceUrl == null) {
                return Collections.emptyList();
            }
            var resourcePath = Paths.get(resourceUrl.toURI());
            return Files.readAllLines(resourcePath);
        } catch (Exception e) {
            // do nothing
            return Collections.emptyList();
        }
    }

}
