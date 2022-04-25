package br.com.sysmap.application.services;

import br.com.sysmap.application.ports.in.PortabilityService;
import br.com.sysmap.domain.PortabilityRequest;
import br.com.sysmap.domain.enums.PortabilityStatusEnum;
import br.com.sysmap.framework.adapters.out.PortabilityRepository;
import br.com.sysmap.framework.exceptions.PortabilityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Service
public class PortabilityServiceImpl implements PortabilityService {

    @Autowired
    private PortabilityRepository portabilityRepository;

    @Override
    @Transactional(readOnly = true)
    public PortabilityRequest savePortability(PortabilityRequest requestPortability) {
        requestPortability.setStatus(PortabilityStatusEnum.PROCESSANDO_PORTABILIDADE);
        requestPortability.setDataDasolicitacao(LocalDateTime.now());
        PortabilityRequest request = portabilityRepository.save(requestPortability);
        return request;
    }

    @Override
    @Transactional(readOnly = true)
    public PortabilityRequest getPortabilityById(UUID uuid) {
        var solicitacao = portabilityRepository.findById(uuid);
        return solicitacao.orElseThrow(() -> new PortabilityNotFoundException("Portability request not found."));
    }

    @Override
    @Transactional(readOnly = true)
    public PortabilityRequest updatePortability(UUID uuid, PortabilityRequest portabilityRequest) {
        var solicitacao = portabilityRepository.findById(uuid).orElseThrow(() -> new PortabilityNotFoundException("Portability request not found."));
        solicitacao.setPortability(portabilityRequest.getPortability());
        solicitacao.setUser(portabilityRequest.getUser());
        solicitacao.setDataDasolicitacao(LocalDateTime.now());
        solicitacao = portabilityRepository.save(solicitacao);
        return solicitacao;
    }

}
