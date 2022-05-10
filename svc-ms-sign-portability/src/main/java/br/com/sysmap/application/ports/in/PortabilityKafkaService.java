package br.com.sysmap.application.ports.in;

import br.com.sysmap.domain.PortabilityPublishRequestDto;
import org.apache.kafka.clients.consumer.ConsumerRecord;

public interface PortabilityKafkaService {

    void listenerTopicPortability(ConsumerRecord<String, PortabilityPublishRequestDto> record);

}
