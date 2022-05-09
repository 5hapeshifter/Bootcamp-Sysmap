package br.com.sysmap.application.ports.out;

import br.com.sysmap.domain.PortabilityPublishRequest;

public interface KafkaService {

    void publishPortability(PortabilityPublishRequest request);

}
