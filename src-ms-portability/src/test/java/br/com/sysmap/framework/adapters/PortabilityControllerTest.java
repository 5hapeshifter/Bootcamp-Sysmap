package br.com.sysmap.framework.adapters;

import br.com.sysmap.application.services.PortabilityServiceImpl;
import br.com.sysmap.framework.adapters.in.dtos.PortabilityRequestDto;
import br.com.sysmap.tests.Factory;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.UUID;

@WebMvcTest(PortabilityController.class)
class PortabilityControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private PortabilityServiceImpl portabilityService;



    private PortabilityRequestDto portabilityRequestDto;
    private PortabilityRequestDto portabilityRequestDtoNull;


    @BeforeEach
    public void setUp() {
        portabilityRequestDto = Factory.createPortabilityRequestDto();
        portabilityRequestDto.setRequestid(UUID.randomUUID());
        portabilityRequestDtoNull = Factory.createPortabilityRequestDto();
        portabilityRequestDtoNull.setPortability(null);
        //Comportamentos simulados
        Mockito.when(portabilityService.savePortability(ArgumentMatchers.any())).thenReturn(portabilityRequestDto);

    }

    @Test
    void createPortability() throws Exception {
        portabilityRequestDto.setRequestid(UUID.randomUUID());
        String dto = objectMapper.writeValueAsString(portabilityRequestDto);
        ResultActions result = mockMvc.perform(MockMvcRequestBuilders.post("/portability")
                .content(dto)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        );

        result.andExpect(MockMvcResultMatchers.status().isOk());
        result.andReturn().getResponse().getContentAsString().contains(portabilityRequestDto.getRequestid().toString());
    }

    @Test
    void createPortability1() throws Exception {
        portabilityRequestDto.setPortability(null);
        String dto = objectMapper.writeValueAsString(portabilityRequestDto);
        ResultActions perform = mockMvc.perform(MockMvcRequestBuilders.post("/portability")
                .content(dto)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        );
        perform.andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    void getPortabilityById() {
    }

    @Test
    void updatePortability() {
    }
}