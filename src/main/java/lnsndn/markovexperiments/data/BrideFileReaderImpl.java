package lnsndn.markovexperiments.data;

import lombok.Getter;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BrideFileReaderImpl implements DataReader {

    @Getter(lazy = true)
    private final List<String> lines = readLines();

    private List<String> readLines() {
        String filename = "src/main/resources/brides.txt";
        List<String> records = new ArrayList<>();

        try(BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                records.add(line);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return records;
    }
}
