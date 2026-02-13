package apigoldenraspberryawards.utils;

import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Component;
import apigoldenraspberryawards.model.Movie;

import java.util.ArrayList;
import java.util.List;

@Component
public class MovieParser {

    private static final Integer YEAR_INDEX = 0;
    private static final Integer TITLE_INDEX = 1;
    private static final Integer STUDIOS_INDEX = 2;
    private static final Integer PRODUCERS_INDEX = 3;
    private static final Integer WINNER_INDEX = 4;
    private static final String PRODUCERS_SEPARATORS_REGEX = "( and |,)";

    public List<Movie> parse(List<String[]> records) {
        List<Movie> movies = new ArrayList<>();
        records.forEach(values -> {
            String[] producers = values[PRODUCERS_INDEX].split(PRODUCERS_SEPARATORS_REGEX);
            for (String producer : producers) {
                if (producer != null && Strings.isNotBlank(producer)) {
                    Movie.MovieBuilder builder = Movie.builder()
                            .year(Integer.valueOf(values[YEAR_INDEX]))
                            .title(values[TITLE_INDEX].trim())
                            .studio(values[STUDIOS_INDEX].trim())
                            .producer(producer.trim());

                    if (values.length > 4 && values[WINNER_INDEX] != null) {
                        builder.winner(values[WINNER_INDEX].equalsIgnoreCase("yes"));
                    } else {
                        builder.winner(Boolean.FALSE);
                    }

                    movies.add(builder.build());
                }
            }
        });

        return movies;
    }

}
