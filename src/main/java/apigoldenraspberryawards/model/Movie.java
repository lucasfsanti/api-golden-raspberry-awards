package apigoldenraspberryawards.model;

import apigoldenraspberryawards.dto.AwardsRangeDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name = "movie")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@NamedNativeQuery(name = "Movie.findRangeBetweenAwards",
        query = "WITH winners AS (" + Movie.RANGE_BETWEEN_WINS_QUERY + ") " +
                "SELECT * " +
                "FROM winners " +
                "WHERE previousWin IS NOT NULL",
        resultSetMapping = "range_between_awards_dto")
@SqlResultSetMapping(name = "range_between_awards_dto", classes = @ConstructorResult(targetClass = AwardsRangeDTO.class, columns = {
        @ColumnResult(name = "producer", type = String.class),
        @ColumnResult(name = "interval", type = Integer.class),
        @ColumnResult(name = "previousWin", type = Integer.class),
        @ColumnResult(name = "followingWin", type = Integer.class)
}))
public class Movie implements Serializable {

    static final String RANGE_BETWEEN_WINS_QUERY = "SELECT m.producer, " +
            "       m.launch_year - COALESCE(lag(m.launch_year) OVER (PARTITION BY m.producer ORDER BY m.launch_year), m.launch_year) AS \"interval\", " +
            "       LAG(m.launch_year) OVER (PARTITION BY m.producer ORDER BY m.launch_year) AS previousWin, " +
            "       m.launch_year AS followingWin " +
            "FROM movie m " +
            "WHERE m.winner = true";


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "launch_year", length = 255, nullable = false)
    private Integer year;

    @NotEmpty
    @Column(name = "title", length = 255, nullable = false)
    private String title;

    @NotEmpty
    @Column(name = "studio", length = 255, nullable = false)
    private String studio;

    @NotEmpty
    @Column(name = "producer", length = 255, nullable = false)
    private String producer;

    @NotNull
    @Column(name = "winner", nullable = false)
    private Boolean winner;
}
