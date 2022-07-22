package br.com.sysmap.framework.adapters.out.kafka;

import br.com.sysmap.domain.PortabilityPublishRequest;
import br.com.sysmap.framework.adapters.in.dtos.PortabilityRequestDto;
import br.com.sysmap.tests.Factory;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.UUID;

@Slf4j
@ExtendWith(SpringExtension.class)
class KafkaServiceImplTest {

    @InjectMocks
    private KafkaServiceImpl kafkaService;

    @Mock
    private KafkaTemplate<UUID, PortabilityPublishRequest> kafkaTemplate;

    private PortabilityRequestDto portabilityRequestDto;
    private PortabilityPublishRequest publishRequest;
    private String topicName;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        topicName = "portability";
        portabilityRequestDto = Factory.createPortabilityRequestDto();
        publishRequest = Factory.createPortabilityToPublish(portabilityRequestDto);
        publishRequest.getPortability().setPortabilityId(UUID.randomUUID());
        ReflectionTestUtils.setField(kafkaService, "topicName", "portability");
        Mockito.when(kafkaTemplate.send(topicName, UUID.randomUUID(), publishRequest)).thenReturn(null);

    }


//    @Test
//    void publishPortabilityShouldPostMessageWhenReceivePortabilityPublishRequest() {
//        Assertions.assertDoesNotThrow(() -> kafkaService.publishPortability(publishRequest));
//        Mockito.verify(kafkaTemplate, Mockito.times(1)).send(topicName, UUID.randomUUID(), publishRequest);
//
//    }
}