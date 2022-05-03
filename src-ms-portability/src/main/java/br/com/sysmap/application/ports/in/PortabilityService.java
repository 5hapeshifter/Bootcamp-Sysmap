package br.com.sysmap.application.ports.in;

import br.com.sysmap.framework.adapters.in.dtos.PortabilityRequestDto;

import java.util.UUID;

public interface PortabilityService {
    PortabilityRequestDto savePortability(PortabilityRequestDto requestPortability);

    PortabilityRequestDto getPortabilityById(UUID uuid);

    PortabilityRequestDto updatePortability(UUID uuid, PortabilityRequestDto requestDto);

}
