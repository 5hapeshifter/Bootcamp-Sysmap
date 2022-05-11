package br.com.sysmap.application.services;

import br.com.sysmap.application.ports.in.PortabilityService;
import br.com.sysmap.application.ports.out.KafkaService;
import br.com.sysmap.domain.enums.PortabilityStatusEnum;
import br.com.sysmap.framework.adapters.in.dtos.PortabilityRequestDto;
import br.com.sysmap.framework.adapters.out.PortabilityRepository;
import br.com.sysmap.framework.adapters.in.dtos.PortabilityResponseStatus;
import br.com.sysmap.framework.config.PortabilityMapper;
import br.com.sysmap.framework.exceptions.PortabilityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class PortabilityServiceImpl implements PortabilityService {

    private PortabilityMapper portabilityMapper;
    private PortabilityRepository portabilityRepository;
    private KafkaService kafkaService;

    public PortabilityServiceImpl(PortabilityMapper portabilityMapper, PortabilityRepository portabilityRepository, KafkaService kafkaService) {
        this.portabilityMapper = portabilityMapper;
        this.portabilityRepository = portabilityRepository;
        this.kafkaService = kafkaService;
    }


    @Override
    @Transactional
    public PortabilityRequestDto savePortability(PortabilityRequestDto requestPortability) {
        var entity = portabilityMapper.portabilityDtoToEntity(requestPortability);
        //PortabilityRequest entity = modelMapper.map(requestPortability, PortabilityRequest.class); // Transfere os parametros de mesmo nome(de um objeto para o outro) e retorna o objeto
        entity.setStatus(PortabilityStatusEnum.PROCESSANDO_PORTABILIDADE);
        entity.setDataDasolicitacao(LocalDateTime.now());
        var result = portabilityMapper.portabilityEntityToDto(portabilityRepository.save(entity));
        var published = portabilityMapper.createPortabilityTopublish(result);
        kafkaService.publishPortability(published);
        return result;
    }

    @Override
    @Transactional
    public PortabilityRequestDto getPortabilityById(UUID uuid) {
        var solicitacao = portabilityRepository.findById(uuid).orElseThrow(() -> new PortabilityNotFoundException("Portability request not found."));
        return portabilityMapper.portabilityEntityToDto(solicitacao);
    }

    @Override
    @Transactional
    public PortabilityResponseStatus updatePortability(UUID uuid, PortabilityResponseStatus status) {
        var entity = portabilityRepository.getById(uuid);
        if (entity == null) {
            throw new PortabilityNotFoundException("Portability request not found.");
        }
        entity.setStatus(status.getStatus());
        portabilityRepository.save(entity);
        return status;
    }

}
