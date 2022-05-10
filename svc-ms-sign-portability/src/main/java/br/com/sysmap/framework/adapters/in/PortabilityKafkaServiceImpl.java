package br.com.sysmap.framework.adapters.in;

import br.com.sysmap.application.ports.in.PortabilityKafkaService;
import br.com.sysmap.domain.PortabilityPublishRequestDto;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class PortabilityKafkaServiceImpl implements PortabilityKafkaService {

    // Metodo que esta escutando as mensagens que chegam no topico
    @KafkaListener(topics = "${topic.name.consumer}", groupId = "${spring.kafka.consumer.group-id}") // Estamos passando o metodo que criamos no containerFactory
    public void listenerTopicPortability(ConsumerRecord<String, PortabilityPublishRequestDto> record) {
        log.info("Received Message Partition = " + record.partition());
        log.info("Received Message Value = " + record.value());
    }
}
