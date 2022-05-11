package br.com.sysmap.framework.adapters.in;

import br.com.sysmap.application.ports.in.PortabilityKafkaService;
import br.com.sysmap.application.ports.in.PortabilityService;
import br.com.sysmap.domain.Portability;
import br.com.sysmap.domain.PortabilityPublishRequest;
import br.com.sysmap.domain.PortabilityPublishRequestDto;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class PortabilityKafkaServiceImpl implements PortabilityKafkaService {

    private final PortabilityService service;

    public PortabilityKafkaServiceImpl(PortabilityService service) {
        this.service = service;
    }

    // Metodo que esta escutando as mensagens que chegam no topico
    @KafkaListener(topics = "${topic.name.consumer}", groupId = "${spring.kafka.consumer.group-id}", containerFactory = "portabilityKafkaListenerContainerFactory")
    public void listenerTopicPortability(ConsumerRecord<String, PortabilityPublishRequestDto> record) {
        var received = record.value();
        log.info("Received Message Partition = " + record.partition());
        log.info("Received Message Value = " + record.value());

        Portability portability = received.getPortability();
        PortabilityPublishRequest entity = PortabilityPublishRequest.builder()
                .documentNumber(received.getDocumentNumber())
                .number(received.getNumber())
                .portability(portability)
                .build();
        //entity.setPortability(portability);

        service.save(entity);
    }
}
