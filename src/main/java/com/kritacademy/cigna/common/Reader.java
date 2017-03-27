package com.kritacademy.cigna.common;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author krit <chertpong.github.io> on 3/27/2017.
 */
public class Reader {
    private static Reader ourInstance = new Reader();
    public static Reader getInstance() {
        return ourInstance;
    }

    private Reader() {}

    public Optional<List<String>> read(String inputPath) throws IOException{
       try (Stream<String> lines = Files.lines(Paths.get(inputPath))) {
           return Optional.of(lines.collect(Collectors.toList()));
       } catch (IOException ioe) {
           System.out.println("Error: "+ ioe.getMessage());
       }
        return Optional.empty();
    }

}
