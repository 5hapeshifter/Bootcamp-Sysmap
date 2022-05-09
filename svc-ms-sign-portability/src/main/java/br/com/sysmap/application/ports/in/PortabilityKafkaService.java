package br.com.sysmap.application.ports.in;

import br.com.sysmap.domain.PortabilityPublishRequest;
import org.apache.kafka.clients.consumer.ConsumerRecord;

public interface PortabilityKafkaService {

    void listenerTopicPortability(ConsumerRecord<String, PortabilityPublishRequest> record);

}
