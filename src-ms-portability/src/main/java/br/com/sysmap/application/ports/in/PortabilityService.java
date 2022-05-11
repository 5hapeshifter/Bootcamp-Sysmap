package br.com.sysmap.application.ports.in;

import br.com.sysmap.framework.adapters.in.dtos.PortabilityRequestDto;
import br.com.sysmap.framework.adapters.in.dtos.PortabilityResponseStatus;

import java.util.UUID;

public interface PortabilityService {
    PortabilityRequestDto savePortability(PortabilityRequestDto requestPortability);

    PortabilityRequestDto getPortabilityById(UUID uuid);

    PortabilityResponseStatus updatePortability(UUID uuid, PortabilityResponseStatus status);

}
