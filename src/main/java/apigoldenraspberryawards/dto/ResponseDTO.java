package apigoldenraspberryawards.dto;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
public class ResponseDTO implements Serializable {

    private List<AwardsRangeDTO> min;

    private List<AwardsRangeDTO> max;

}
