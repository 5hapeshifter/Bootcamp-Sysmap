package br.com.sysmap.framework.config;

import br.com.sysmap.domain.*;
import br.com.sysmap.framework.adapters.in.dtos.*;
import org.springframework.stereotype.Component;

@Component
public class PortabilityMapper {

    public PortabilityRequest portabilityDtoToEntity(PortabilityRequestDto request) {

        Address address = new Address();
        address.setStreet(request.getUser().getAddress().getStreet());
        address.setNumber(request.getUser().getAddress().getNumber());
        address.setCity(request.getUser().getAddress().getCity());
        address.setCountry(request.getUser().getAddress().getCountry());
        address.setStateOrRegion(request.getUser().getAddress().getStateOrRegion());

        Line line = new Line();
        line.setNumber(request.getUser().getLine().getNumber());

        User user = new User();
        user.setAddress(address);
        user.setLine(line);
        user.setName(request.getUser().getName());
        user.setDateOfBirth(request.getUser().getDateOfBirth());
        user.setDocumentNumber(request.getUser().getDocumentNumber());

        Portability portability = new Portability();
        portability.setSource(request.getPortability().getSource());
        portability.setTarget(request.getPortability().getTarget());

        return PortabilityRequest.builder()
                .user(user)
                .portability(portability)
                .build();
    }

    public PortabilityRequestDto portabilityEntityToDto(PortabilityRequest entity) {

        AddressDto addressDto = new AddressDto();
        LineDto lineDto = new LineDto();
        UserDto userDto = new UserDto();
        PortabilityDto portabilityDto = new PortabilityDto();
        PortabilityRequestDto requestDto = new PortabilityRequestDto();

        addressDto.setCity(entity.getUser().getAddress().getCity());
        addressDto.setCountry(entity.getUser().getAddress().getCountry());
        addressDto.setNumber(entity.getUser().getAddress().getNumber());
        addressDto.setStreet(entity.getUser().getAddress().getStreet());
        addressDto.setStateOrRegion(entity.getUser().getAddress().getStateOrRegion());

        lineDto.setNumber(entity.getUser().getLine().getNumber());

        userDto.setAddress(addressDto);
        userDto.setLine(lineDto);
        userDto.setName(entity.getUser().getName());
        userDto.setDocumentNumber(entity.getUser().getDocumentNumber());
        userDto.setDateOfBirth(entity.getUser().getDateOfBirth());

        portabilityDto.setSource(entity.getPortability().getSource());
        portabilityDto.setTarget(entity.getPortability().getTarget());

        requestDto.setPortability(portabilityDto);
        requestDto.setUser(userDto);
        requestDto.setRequestid(entity.getRequestid());

        return requestDto;

    }

    public PortabilityPublishRequest createPortabilityTopublish(PortabilityRequestDto request) {
        PortabilityPublishRequest portabilityPublished = new PortabilityPublishRequest();
        Portability portability = new Portability();
        portability.setPortabilityId(request.getRequestid());
        portability.setSource(request.getPortability().getSource());
        portability.setTarget(request.getPortability().getTarget());
        portabilityPublished.setDocumentNumber(request.getUser().getDocumentNumber());
        portabilityPublished.setNumber(request.getUser().getLine().getNumber());
        portabilityPublished.setPortability(portability);
        return portabilityPublished;
    }

}
