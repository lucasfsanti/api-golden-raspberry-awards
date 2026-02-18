package apigoldenraspberryawards;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import apigoldenraspberryawards.dto.ResponseDTO;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ProducerControllerTest {

    private static final String RESPONSE_EXPECTED = "{\"min\":[{\"producer\":\"Joel Silver\",\"interval\":1,\"previousWin\":1990,\"followingWin\":1991},{\"producer\":\"Matthew Vaughn\",\"interval\":1,\"previousWin\":2002,\"followingWin\":2003}],\"max\":[{\"producer\":\"Matthew Vaughn\",\"interval\":22,\"previousWin\":1980,\"followingWin\":2002},{\"producer\":\"Matthew Vaughn\",\"interval\":22,\"previousWin\":2015,\"followingWin\":2037}]}";

    @Test
    void testAwardsRangeResponseStructure(@Autowired MockMvc mvc, @Autowired ObjectMapper objectMapper) throws Exception {
        MvcResult mvcResult = mvc.perform(get("/producers/awards-range"))
                .andExpect(status().isOk())
                .andReturn();

        String json = mvcResult.getResponse().getContentAsString();
        ResponseDTO responseDto = objectMapper.readValue(json, new TypeReference<ResponseDTO>(){});

        assertNotNull(responseDto);
        assertNotNull(responseDto.getMax());
        assertNotNull(responseDto.getMin());
    }

    @Test
    void testAwardsRangeResponseData(@Autowired MockMvc mvc) throws Exception {
        MvcResult mvcResult = mvc.perform(get("/producers/awards-range"))
                .andExpect(status().isOk())
                .andExpect(content().json(RESPONSE_EXPECTED))
                .andReturn();
    }

}
