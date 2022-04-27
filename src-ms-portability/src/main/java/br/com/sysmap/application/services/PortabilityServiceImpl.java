package br.com.sysmap.application.services;

import br.com.sysmap.application.ports.in.PortabilityService;
import br.com.sysmap.domain.enums.PortabilityStatusEnum;
import br.com.sysmap.framework.adapters.in.dtos.PortabilityRequestDto;
import br.com.sysmap.framework.adapters.out.PortabilityRepository;
import br.com.sysmap.framework.config.PortabilityMapper;
import br.com.sysmap.framework.exceptions.PortabilityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class PortabilityServiceImpl implements PortabilityService {

    @Autowired
    private PortabilityMapper portabilityMapper;

//    @Autowired
//    private ModelMapper modelMapper;

    @Autowired
    private PortabilityRepository portabilityRepository;

//    public PortabilityServiceImpl(ModelMapper modelMapper, PortabilityRepository portabilityRepository) {
//        this.modelMapper = modelMapper;
//        this.portabilityRepository = portabilityRepository;
//    }

    @Override
    @Transactional
    public PortabilityRequestDto savePortability(PortabilityRequestDto requestPortability) {
        var entity = portabilityMapper.portabilityDtoToEntity(requestPortability);
        //PortabilityRequest entity = modelMapper.map(requestPortability, PortabilityRequest.class); // Transfere os parametros de mesmo nome(de um objeto para o outro) e retorna o objeto
        entity.setStatus(PortabilityStatusEnum.PROCESSANDO_PORTABILIDADE);
        entity.setDataDasolicitacao(LocalDateTime.now());
        return portabilityMapper.portabilityEntityToDto(portabilityRepository.save(entity));

    }

    @Override
    @Transactional
    public PortabilityRequestDto getPortabilityById(UUID uuid) {
        var solicitacao = portabilityRepository.findById(uuid).orElseThrow(() -> new PortabilityNotFoundException("Portability request not found."));
        return portabilityMapper.portabilityEntityToDto(solicitacao);
    }

    @Override
    @Transactional
    public PortabilityRequestDto updatePortability(UUID uuid, PortabilityRequestDto requestDto) {
        var oldEntity = portabilityRepository.findById(uuid)
                .orElseThrow(() -> new PortabilityNotFoundException("Portability request not found."));
        oldEntity.setStatus(PortabilityStatusEnum.PORTABILIDADE_ALTERADA);
        portabilityRepository.save(oldEntity);
        var newEntity = portabilityMapper.portabilityDtoToEntity(requestDto);
        newEntity.setStatus(PortabilityStatusEnum.PROCESSANDO_PORTABILIDADE);
        newEntity.setDataDasolicitacao(LocalDateTime.now());
        newEntity = portabilityRepository.save(newEntity);
        return portabilityMapper.portabilityEntityToDto(newEntity);
    }
}
