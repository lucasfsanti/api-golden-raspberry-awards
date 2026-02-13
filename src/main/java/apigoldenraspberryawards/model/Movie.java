package apigoldenraspberryawards.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
public class Movie implements Serializable {

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
