package apigoldenraspberryawards.utils;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Log4j2
@Component
public class CsvDataLoader {

    private static final String CSV_FILE_PATH = "src/main/resources/Movielist.csv";
    private static final String SEMICOLON_DELIMITER = ";";

    public List<String[]> loadCsvFile() {
        List<String[]> records = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(CSV_FILE_PATH))) {
            String line;
            skipHeader(br);
            while ((line = br.readLine()) != null) {
                String[] values = line.split(SEMICOLON_DELIMITER);
                records.add(values);
            }
        } catch (FileNotFoundException e) {
            log.error("File " + CSV_FILE_PATH + " not found", e);
        } catch (IOException e) {
            log.error("Error reading CSV file " + CSV_FILE_PATH, e);
        }

        return records;
    }

    private static void skipHeader(BufferedReader br) throws IOException {
        br.readLine();
    }

}
