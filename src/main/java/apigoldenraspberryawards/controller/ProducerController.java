package apigoldenraspberryawards.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import apigoldenraspberryawards.dto.ResponseDTO;
import apigoldenraspberryawards.service.ProducerService;

@RestController
@RequestMapping("/producers")
public class ProducerController {

    @Autowired
    private ProducerService producerService;

    @GetMapping("/awards-range")
    public ResponseEntity<ResponseDTO> findAwardsRange() {
        ResponseDTO response = producerService.getRanges();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
