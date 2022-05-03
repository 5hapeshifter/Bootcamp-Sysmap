package br.com.sysmap.application.ports.out;

import br.com.sysmap.framework.adapters.in.dtos.PortabilityRequestDto;

public interface KafkaService {

    void publishPortability(PortabilityRequestDto portabilityRequestDto);
}
