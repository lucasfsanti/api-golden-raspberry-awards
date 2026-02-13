package apigoldenraspberryawards.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import apigoldenraspberryawards.dto.AwardsRangeDTO;
import apigoldenraspberryawards.dto.ResponseDTO;
import apigoldenraspberryawards.repository.MovieRepository;
import java.util.Arrays;
import java.util.List;
import java.util.TreeMap;

@Service
public class ProducerService {

    @Autowired
    private MovieRepository repository;

    public ResponseDTO getRanges() {
        List<AwardsRangeDTO> ranges = repository.findRanges();

        TreeMap<Integer, List<AwardsRangeDTO>> map = new TreeMap<>();

        ranges.forEach(r -> {
            if (map.containsKey(r.getInterval())) {
                map.get(r.getInterval()).add(r);
            } else {
                map.put(r.getInterval(), Arrays.asList(r));
            }
        });

        return ResponseDTO.builder()
                .min(map.firstEntry().getValue())
                .max(map.lastEntry().getValue())
                .build();
    }
}
