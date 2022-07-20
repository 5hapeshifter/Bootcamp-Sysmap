package br.com.sysmap.application.services;

import br.com.sysmap.application.ports.out.KafkaService;
import br.com.sysmap.domain.PortabilityPublishRequest;
import br.com.sysmap.domain.PortabilityRequest;
import br.com.sysmap.domain.enums.PortabilityStatusEnum;
import br.com.sysmap.framework.adapters.in.dtos.PortabilityRequestDto;
import br.com.sysmap.framework.adapters.in.dtos.PortabilityResponseStatus;
import br.com.sysmap.framework.adapters.out.PortabilityRepository;
import br.com.sysmap.framework.config.PortabilityMapper;
import br.com.sysmap.framework.exceptions.PortabilityNotFoundException;
import br.com.sysmap.tests.Factory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;

@ExtendWith(SpringExtension.class)
class PortabilityServiceImplTest {

    @InjectMocks
    private PortabilityServiceImpl portabilityServiceImpl;

    @Mock
    private PortabilityMapper portabilityMapper;

    @Mock
    private PortabilityRepository portabilityRepository;

    @Mock
    private KafkaService kafkaService;

    private PortabilityRequestDto portabilityRequestDto;
    private PortabilityRequestDto portabilityRequestDtoNull;
    private PortabilityRequest portabilityRequest;
    private PortabilityPublishRequest portabilityPublishRequest;
    private UUID existingPortabilityId;
    private UUID nonExistingPortabilityId;
    private PortabilityResponseStatus responseStatus = new PortabilityResponseStatus();

    @BeforeEach
    public void setUp() {
        portabilityRequestDto = Factory.createPortabilityRequestDto();
        portabilityRequest = Factory.createPortabilityRequest(portabilityRequestDto);
        portabilityPublishRequest = Factory.createPortabilityToPublish(portabilityRequestDto);
        existingPortabilityId = UUID.fromString("c06c5202-ee75-4849-9155-a0dad09d9b8f");
        nonExistingPortabilityId = UUID.randomUUID();
        responseStatus.setStatus(PortabilityStatusEnum.PORTADO);
        responseStatus.setId(existingPortabilityId);
        //Configuracao do comportamento simulado
        Mockito.when(portabilityMapper.portabilityDtoToEntity(portabilityRequestDto)).thenReturn(portabilityRequest);
        Mockito.when(portabilityMapper.portabilityEntityToDto(portabilityRequest)).thenReturn(portabilityRequestDto);
        Mockito.when(portabilityMapper.createPortabilityTopublish(portabilityRequestDto)).thenReturn(portabilityPublishRequest);
        Mockito.when(portabilityRepository.save(any())).thenReturn(portabilityRequest);
        Mockito.doThrow(PortabilityNotFoundException.class).when(portabilityRepository).findById(nonExistingPortabilityId);
        Mockito.when(portabilityRepository.findById(existingPortabilityId)).thenReturn(Optional.ofNullable(portabilityRequest));
        Mockito.when(portabilityRepository.getById(existingPortabilityId)).thenReturn(portabilityRequest);
        Mockito.doThrow(PortabilityNotFoundException.class).when(portabilityRepository).getById(nonExistingPortabilityId);

    }

    @Test
    void savePortabilityShouldReturnPortabilityRequestDtoWhenRequestIsOk() {
        PortabilityRequestDto entity = portabilityServiceImpl.savePortability(this.portabilityRequestDto);
        Assertions.assertNotNull(entity);
        Mockito.verify(portabilityRepository, Mockito.times(1)).save(portabilityRequest);
    }

    @Test
    void getPortabilityByIdShouldReturnPortabilityRequestDto() {
        PortabilityRequestDto entity = portabilityServiceImpl.getPortabilityById(existingPortabilityId);
        Assertions.assertNotNull(entity);
        Mockito.verify(portabilityRepository).findById(existingPortabilityId);
    }

    @Test
    void getPortabilityByIdShouldThrowPortabilityNotFoundExceptionWhenIdDoesNotExists() {
        Assertions.assertThrows(PortabilityNotFoundException.class, () -> portabilityServiceImpl.getPortabilityById(nonExistingPortabilityId));
        Mockito.verify(portabilityRepository, Mockito.times(1)).findById(nonExistingPortabilityId);
    }

    @Test
    void updatePortabilityShouldReturnStatusWhenRequestIsOk() {
        var entity = portabilityServiceImpl.updatePortability(existingPortabilityId, responseStatus);
        Assertions.assertNotNull(entity);
        Mockito.verify(portabilityRepository, Mockito.times(1)).getById(existingPortabilityId);
    }

    @Test
    void updatePortabilityShouldThrowPortabilityNotFoundExceptionWhenIdDoesNotExists() {
        Assertions.assertThrows(PortabilityNotFoundException.class, () -> portabilityServiceImpl.updatePortability(nonExistingPortabilityId, responseStatus));
        Mockito.verify(portabilityRepository, Mockito.times(1)).getById(nonExistingPortabilityId);
    }

}