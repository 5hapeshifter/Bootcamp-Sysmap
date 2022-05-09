package br.com.sysmap.framework.adapters.out.kafka;

import br.com.sysmap.application.ports.out.KafkaService;
import br.com.sysmap.domain.PortabilityPublishRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Slf4j
@Component
public class KafkaServiceImpl implements KafkaService {

    private final String topicName;
    private final KafkaTemplate<UUID, PortabilityPublishRequest> kafkaTemplate;

    public KafkaServiceImpl(@Value("${topic.name.producer}") String topicName, KafkaTemplate<UUID, PortabilityPublishRequest> kafkaTemplate) {
        this.topicName = topicName;
        this.kafkaTemplate = kafkaTemplate;
    }

    public void publishPortability(PortabilityPublishRequest request) {
        String msgSuccess = String.format("Mensagem enviada com SUCESSO para o topico %s, Mensagem = %s ", topicName, request.toString());
        String msgFailure = String.format("FALHA ao enviar a mensagem para o topico %s, Mensagem = %s ", topicName, request.toString());
        kafkaTemplate.send(topicName, request).addCallback(
            success -> log.info(msgSuccess),
            failure -> log.info(msgFailure)
        );
    }


}
