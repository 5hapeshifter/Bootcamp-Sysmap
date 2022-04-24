package br.com.sysmap.application.ports.in;

import br.com.sysmap.domain.PortabilityRequest;

import java.util.UUID;

public interface PortabilityService {
    PortabilityRequest savePortability(PortabilityRequest requestPortability);

    PortabilityRequest getPortabilityById(UUID uuid);

    PortabilityRequest updatePortability(UUID uuid, PortabilityRequest portabilityRequest);
}
