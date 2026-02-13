package apigoldenraspberryawards;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import apigoldenraspberryawards.model.Movie;
import apigoldenraspberryawards.repository.MovieRepository;
import apigoldenraspberryawards.utils.CsvDataLoader;
import apigoldenraspberryawards.utils.MovieParser;

import javax.annotation.PostConstruct;
import java.util.List;

@Component
public class Setup {

    @Autowired
    private CsvDataLoader csvDataLoader;

    @Autowired
    private MovieParser movieParser;

    @Autowired
    private MovieRepository repository;

    @PostConstruct
    private void setupData() {
        List<String[]> records = csvDataLoader.loadCsvFile();
        List<Movie> movies = movieParser.parse(records);
        repository.saveAll(movies);
    }
}
