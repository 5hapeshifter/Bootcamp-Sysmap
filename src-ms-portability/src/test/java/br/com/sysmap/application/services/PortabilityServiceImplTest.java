package br.com.sysmap.application.services;

import br.com.sysmap.application.ports.out.KafkaService;
import br.com.sysmap.domain.PortabilityPublishRequest;
import br.com.sysmap.domain.PortabilityRequest;
import br.com.sysmap.framework.adapters.in.dtos.PortabilityRequestDto;
import br.com.sysmap.framework.adapters.out.PortabilityRepository;
import br.com.sysmap.framework.config.PortabilityMapper;
import br.com.sysmap.tests.Factory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

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
    private PortabilityRequest portabilityRequest;
    private PortabilityPublishRequest portabilityPublishRequest;

    @BeforeEach
    public void setUp() {
        portabilityRequestDto = Factory.createPortabilityRequestDto();
        portabilityRequest = Factory.createPortabilityRequest(portabilityRequestDto);
        portabilityPublishRequest = Factory.createPortabilityToPublish(portabilityRequestDto);
        //Configuracao do comportamento simulado
        Mockito.when(portabilityMapper.portabilityDtoToEntity(portabilityRequestDto)).thenReturn(portabilityRequest);
        Mockito.when(portabilityMapper.portabilityEntityToDto(portabilityRequest)).thenReturn(portabilityRequestDto);
        //Mockito.when(portabilityMapper.createPortabilityTopublish(portabilityRequestDto)).thenReturn(portabilityPublishRequest);
        Mockito.when(portabilityRepository.save(any())).thenReturn(portabilityRequest);

    }

    @Test
    void savePortabilityShouldDoNothingWhenRequestIsReceived() {
        PortabilityRequestDto entity = portabilityServiceImpl.savePortability(this.portabilityRequestDto);
        Assertions.assertNotNull(entity);
        Mockito.verify(portabilityRepository, Mockito.times(1)).save(portabilityRequest);
    }

    @Test
    void getPortabilityById() {
    }

    @Test
    void updatePortability() {
    }
}