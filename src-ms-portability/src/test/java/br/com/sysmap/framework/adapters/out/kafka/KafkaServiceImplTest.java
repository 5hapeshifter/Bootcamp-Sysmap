package br.com.sysmap.framework.adapters.out.kafka;

import br.com.sysmap.domain.PortabilityPublishRequest;
import br.com.sysmap.framework.adapters.in.dtos.PortabilityRequestDto;
import br.com.sysmap.tests.Factory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.UUID;

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
        topicName = "portability";
        portabilityRequestDto = Factory.createPortabilityRequestDto();
        publishRequest = Factory.createPortabilityToPublish(portabilityRequestDto);
        Mockito.doNothing().when(kafkaTemplate).send(topicName, publishRequest);
    }


//    @Test
//    void publishPortabilityShouldDoNothingWhenReceivePortabilityPublishRequest() {
//        kafkaService.publishPortability(publishRequest);
//        Mockito.verify(kafkaTemplate, Mockito.times(1)).send(topicName,publishRequest);
//
//    }
}