package apigoldenraspberryawards.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Builder
@AllArgsConstructor
@Data
public class AwardsRangeDTO implements Serializable {

    private String producer;

    private Integer interval;

    private Integer previousWin;

    private Integer followingWin;

}
