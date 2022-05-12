package br.com.sysmap.application.ports.services;

import br.com.sysmap.application.ports.in.PortabilityService;
import br.com.sysmap.application.ports.out.PortabilityConsumerRepository;
import br.com.sysmap.application.ports.out.PortabilityFeign;
import br.com.sysmap.domain.PortabilityCorporationEnum;
import br.com.sysmap.domain.PortabilityPublishRequest;
import br.com.sysmap.domain.PortabilityStatusEnum;
import org.springframework.stereotype.Service;

@Service
public class PortabilityServiceImpl implements PortabilityService {

    private final PortabilityConsumerRepository repository;
    private final PortabilityFeign feign;

    public PortabilityServiceImpl(PortabilityConsumerRepository repository, PortabilityFeign feign) {
        this.repository = repository;
        this.feign = feign;
    }

    public void callbackPortability(PortabilityPublishRequest portability, PortabilityStatusEnum status){
        feign.updatePortabilityStatus(portability.getPortability().getPortabilityId(), status);
    }

    public void save(PortabilityPublishRequest entity) {
        var validated = portabilityValidation(entity);
        callbackPortability(entity, validated);
        repository.save(entity);
    }

    public PortabilityStatusEnum portabilityValidation(PortabilityPublishRequest entity) {

        if (entity.getNumber().length() == 9
                && entity.getPortability().getSource().equals(PortabilityCorporationEnum.VIVO)
                && (entity.getPortability().getTarget().equals(PortabilityCorporationEnum.CLARO)
                || entity.getPortability().getTarget().equals(PortabilityCorporationEnum.NEXTEL)
                || entity.getPortability().getTarget().equals(PortabilityCorporationEnum.TIM)
                || entity.getPortability().getTarget().equals(PortabilityCorporationEnum.OI))
        ) {
            return PortabilityStatusEnum.PORTADO;

        }
        return PortabilityStatusEnum.NAO_PORTADO;
    }
}
