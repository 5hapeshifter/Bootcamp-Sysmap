package br.com.sysmap.framework.adapters;

import br.com.sysmap.application.services.PortabilityServiceImpl;
import br.com.sysmap.framework.adapters.in.dtos.PortabilityRequestDto;
import br.com.sysmap.framework.adapters.in.dtos.PortabilityResponseStatus;
import br.com.sysmap.framework.exceptions.PortabilityNotFoundException;
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

import static org.mockito.ArgumentMatchers.any;

@WebMvcTest(PortabilityController.class)
class PortabilityControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private PortabilityServiceImpl portabilityService;


    private UUID existingID;
    private UUID nonExistingID;
    private PortabilityRequestDto portabilityRequestDto;
    private PortabilityResponseStatus responseStatus;

    @BeforeEach
    public void setUp() {
        existingID = UUID.randomUUID();
        nonExistingID = UUID.randomUUID();
        responseStatus = Factory.createPortabilityResponseStatus(UUID.randomUUID());
        portabilityRequestDto = Factory.createPortabilityRequestDto();
        portabilityRequestDto.setRequestid(existingID);
        //Comportamentos simulados
        Mockito.when(portabilityService.savePortability(any())).thenReturn(portabilityRequestDto);
        Mockito.when(portabilityService.getPortabilityById(any())).thenReturn(portabilityRequestDto);
        Mockito.when(portabilityService.getPortabilityById(nonExistingID)).thenThrow(PortabilityNotFoundException.class);
        Mockito.when(portabilityService.updatePortability(ArgumentMatchers.eq(existingID), any())).thenReturn(responseStatus);
        Mockito.when(portabilityService.updatePortability(ArgumentMatchers.eq(nonExistingID), any())).thenThrow(PortabilityNotFoundException.class);

    }

    @Test
    void createPortabilityShouldReturnOkWhenRequestIsCorrect() throws Exception {
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
    void createPortabilityShouldReturnBadRequestWhenPortabilityIsNotPresent() throws Exception {
        portabilityRequestDto.setPortability(null);
        String dto = objectMapper.writeValueAsString(portabilityRequestDto);
        ResultActions result = mockMvc.perform(MockMvcRequestBuilders.post("/portability")
                .content(dto)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        );
        result.andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    void getPortabilityByIdShouldReturnPortabilityRequestDtoWhenIdExists() throws Exception {
        ResultActions result = mockMvc.perform(MockMvcRequestBuilders
                .get("/portability/{portabilityId}", portabilityRequestDto.getRequestid())
                .accept(MediaType.APPLICATION_JSON)
        );
        result.andExpect(MockMvcResultMatchers.status().isFound());
        result.andReturn().getResponse().getContentAsString().contains(portabilityRequestDto.getUser().getName());
    }

    @Test
    void getPortabilityByIdShouldReturnPortabilityNotFoundWhenIdDoesNotExists() throws Exception {
        ResultActions result = mockMvc.perform(MockMvcRequestBuilders
                .get("/portability/{portabilityId}", nonExistingID)
                .accept(MediaType.APPLICATION_JSON)
        );
        result.andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    void updatePortabilityShouldReturnPortabilityResponseStatusWhenIdExists() throws Exception {
        String response = objectMapper.writeValueAsString(responseStatus);
        ResultActions result = mockMvc.perform(MockMvcRequestBuilders.put("/portability/{portabilityId}", existingID)
                .content(response)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        );
        result.andExpect(MockMvcResultMatchers.status().isOk());
        result.andExpect(MockMvcResultMatchers.jsonPath("id").exists());
        result.andExpect(MockMvcResultMatchers.jsonPath("status").exists());
    }

    @Test
    void updatePortabilityShouldReturnPortabilityNotFoundWhenIdDoesNotExists() throws Exception {
        String response = objectMapper.writeValueAsString(responseStatus);
        ResultActions result = mockMvc.perform(MockMvcRequestBuilders.put("/portability/{portabilityId}", nonExistingID)
                .content(response)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        );
        result.andExpect(MockMvcResultMatchers.status().isNotFound());
    }
}