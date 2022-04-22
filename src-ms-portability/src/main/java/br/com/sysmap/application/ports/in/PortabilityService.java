package br.com.sysmap.application.ports.in;

import br.com.sysmap.domain.PortabilityRequest;

public interface PortabilityService {
    void savePortability(PortabilityRequest requestPortability);
}
