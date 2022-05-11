package br.com.sysmap.application.ports.services;

import br.com.sysmap.application.ports.in.PortabilityService;
import br.com.sysmap.application.ports.out.PortabilityConsumerRepository;
import br.com.sysmap.domain.PortabilityCorporationEnum;
import br.com.sysmap.domain.PortabilityPublishRequest;
import br.com.sysmap.domain.PortabilityStatusEnum;
import br.com.sysmap.framework.adapters.out.PortabilityResponseStatus;
import org.springframework.stereotype.Service;

@Service
public class PortabilityServiceImpl implements PortabilityService {

    private final PortabilityConsumerRepository repository;

    public PortabilityServiceImpl(PortabilityConsumerRepository repository) {
        this.repository = repository;
    }

    public PortabilityResponseStatus save(PortabilityPublishRequest entity) {
        PortabilityResponseStatus response = new PortabilityResponseStatus();
        repository.save(entity);
        if (entity.getNumber().length() == 11
            && entity.getPortability().getSource().equals(PortabilityCorporationEnum.VIVO)
            && entity.getPortability().getTarget().equals(PortabilityCorporationEnum.CLARO)
            && entity.getPortability().getTarget().equals(PortabilityCorporationEnum.NEXTEL)
            && entity.getPortability().getTarget().equals(PortabilityCorporationEnum.TIM)
            && entity.getPortability().getTarget().equals(PortabilityCorporationEnum.OI)
            ) {
            response.setId(entity.getPublishedRequestId());
            response.setStatus(PortabilityStatusEnum.PORTADO);
            return response;
        }
        response.setId(entity.getPublishedRequestId());
        response.setStatus(PortabilityStatusEnum.NAO_PORTADO);
        return response;

    }
}
