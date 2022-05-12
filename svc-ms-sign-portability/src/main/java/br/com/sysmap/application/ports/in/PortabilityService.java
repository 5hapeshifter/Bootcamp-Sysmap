package br.com.sysmap.application.ports.in;

import br.com.sysmap.domain.PortabilityPublishRequest;

public interface PortabilityService {

    void save(PortabilityPublishRequest entity);
}
