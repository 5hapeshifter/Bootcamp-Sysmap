package br.com.sysmap.tests;

import br.com.sysmap.domain.*;
import br.com.sysmap.domain.enums.PortabilityCorporationEnum;
import br.com.sysmap.domain.enums.PortabilityStatusEnum;
import br.com.sysmap.framework.adapters.in.dtos.*;
import br.com.sysmap.framework.config.PortabilityMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public class Factory {

    @Autowired
    private static PortabilityMapper portabilityMapper;

    public static PortabilityRequestDto createPortabilityRequestDto() {
        PortabilityRequestDto portabilityRequestDto = new PortabilityRequestDto();
        LineDto line = new LineDto();
        AddressDto addressDto = new AddressDto();
        UserDto user = new UserDto();
        PortabilityDto portabilityDto = new PortabilityDto();
        line.setNumber("11222333444");
        addressDto.setStateOrRegion("");
        addressDto.setStreet("");
        addressDto.setCountry("");
        addressDto.setCity("");
        addressDto.setNumber("");
        user.setLine(line);
        user.setAddress(addressDto);
        user.setDateOfBirth(LocalDate.now());
        user.setName("Teste da Silva");
        user.setDocumentNumber("11122233344");
        portabilityDto.setSource(PortabilityCorporationEnum.VIVO);
        portabilityDto.setTarget(PortabilityCorporationEnum.OI);
        portabilityRequestDto.setPortability(portabilityDto);
        portabilityRequestDto.setUser(user);
        return portabilityRequestDto;
    }

    public static PortabilityRequest createPortabilityRequest(PortabilityRequestDto dto) {
        PortabilityRequest request = new PortabilityRequest();
        User user = new User();
        Address address = new Address();
        Line line = new Line();
        Portability portability = new Portability();

        address.setStateOrRegion(dto.getUser().getAddress().getStateOrRegion());
        address.setCountry(dto.getUser().getAddress().getCountry());
        address.setCity(dto.getUser().getAddress().getCity());
        address.setNumber(dto.getUser().getAddress().getNumber());
        address.setStreet(dto.getUser().getAddress().getStreet());

        line.setNumber(dto.getUser().getLine().getNumber());

        user.setAddress(address);
        user.setLine(line);
        user.setName(dto.getUser().getName());
        user.setDocumentNumber(dto.getUser().getDocumentNumber());
        user.setDateOfBirth(dto.getUser().getDateOfBirth());

        portability.setTarget(dto.getPortability().getTarget());
        portability.setSource(dto.getPortability().getSource());

        request.setUser(user);
        request.setPortability(portability);
        request.setStatus(PortabilityStatusEnum.PROCESSANDO_PORTABILIDADE);
        request.setDataDasolicitacao(LocalDateTime.now());

        return request;
    }

    public static PortabilityPublishRequest createPortabilityToPublish(PortabilityRequestDto dto) {
        PortabilityPublishRequest publishRequest = new PortabilityPublishRequest();
        Portability portability = new Portability();
        portability.setSource(dto.getPortability().getSource());
        portability.setTarget(dto.getPortability().getTarget());
        publishRequest.setPortability(portability);
        publishRequest.setNumber(dto.getUser().getLine().getNumber());
        publishRequest.setDocumentNumber(dto.getUser().getDocumentNumber());
        return publishRequest;
    }

    public static PortabilityResponseStatus createPortabilityResponseStatus(UUID uuid) {
        return new PortabilityResponseStatus(PortabilityStatusEnum.PORTADO, uuid);
    }

}
