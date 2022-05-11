package br.com.sysmap.application.ports.in;

import br.com.sysmap.domain.PortabilityPublishRequest;
import br.com.sysmap.framework.adapters.out.PortabilityResponseStatus;

public interface PortabilityService {

    PortabilityResponseStatus save(PortabilityPublishRequest entity);
}
