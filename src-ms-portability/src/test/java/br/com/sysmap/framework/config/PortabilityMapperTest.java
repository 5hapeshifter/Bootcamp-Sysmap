package br.com.sysmap.framework.config;

import br.com.sysmap.domain.PortabilityPublishRequest;
import br.com.sysmap.domain.PortabilityRequest;
import br.com.sysmap.framework.adapters.in.dtos.PortabilityRequestDto;
import br.com.sysmap.tests.Factory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.UUID;

@ExtendWith(SpringExtension.class)
class PortabilityMapperTest {

    @InjectMocks
    private PortabilityMapper portabilityMapper;

    private UUID existingID;
    private PortabilityRequestDto portabilityRequestDto;
    private PortabilityRequest portabilityRequest;

    @BeforeEach
    public void setUp() {
        existingID = UUID.randomUUID();
        portabilityRequestDto = Factory.createPortabilityRequestDto();
        portabilityRequestDto.setRequestid(existingID);
        portabilityRequest = Factory.createPortabilityRequest(portabilityRequestDto);
    }

    @Test
    void portabilityDtoToEntityShouldReturnPortabilityRequest() {
        PortabilityRequest entity = portabilityMapper.portabilityDtoToEntity(portabilityRequestDto);
        Assertions.assertInstanceOf(PortabilityRequest.class, entity);
    }

    @Test
    void portabilityEntityToDtoShouldReturnPortabilityRequestDto() {
        PortabilityRequestDto entity = portabilityMapper.portabilityEntityToDto(portabilityRequest);
        Assertions.assertInstanceOf(PortabilityRequestDto.class, entity);
    }

    @Test
    void createPortabilityTopublishShouldReturnPortabilityPublishRequest() {
        PortabilityPublishRequest entity = portabilityMapper.createPortabilityTopublish(portabilityRequestDto);
        Assertions.assertInstanceOf(PortabilityPublishRequest.class, entity);
    }
}