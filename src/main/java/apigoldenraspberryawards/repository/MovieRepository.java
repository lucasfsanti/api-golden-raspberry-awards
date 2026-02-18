package apigoldenraspberryawards.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import apigoldenraspberryawards.dto.AwardsRangeDTO;
import apigoldenraspberryawards.model.Movie;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {


    @Query(nativeQuery = true)
    List<AwardsRangeDTO> findRangeBetweenAwards();

}
