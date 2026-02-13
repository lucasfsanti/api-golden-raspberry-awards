package apigoldenraspberryawards.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import apigoldenraspberryawards.dto.AwardsRangeDTO;
import apigoldenraspberryawards.model.Movie;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {

    @Query(value = "SELECT \n" +
            "new apigoldenraspberryawards.dto.AwardsRangeDTO(m.producer, " +
            "    MAX(m.year) - MIN(m.year), " +
            "    MIN(m.year), " +
            "    MAX(m.year)) " +
            "FROM Movie m \n" +
            "WHERE m.winner = true \n" +
            "GROUP BY m.producer HAVING COUNT(1) > 1")
    List<AwardsRangeDTO> findRanges();

}
