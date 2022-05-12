package br.com.sysmap.application.ports.out;

import br.com.sysmap.domain.PortabilityStatusEnum;

import java.util.UUID;

public interface PortabilityFeign {
    void updatePortabilityStatus(UUID portabilityId, PortabilityStatusEnum status);
}
