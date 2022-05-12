package br.com.sysmap.framework.adapters.out;

import br.com.sysmap.application.ports.out.PortabilityFeign;
import br.com.sysmap.domain.PortabilityStatusEnum;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

public class PortabilityFeignImpl implements PortabilityFeign {

    @Autowired
    private PortabilityFeignClient portabilityFeignClient;

    @Override
    public void updatePortabilityStatus(UUID portabilityId, PortabilityStatusEnum status) {
        portabilityFeignClient.updatePortability(portabilityId, new PortabilityResponseStatus(status));
    }
}
