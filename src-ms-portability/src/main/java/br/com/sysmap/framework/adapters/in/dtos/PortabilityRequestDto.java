package br.com.sysmap.framework.adapters.in.dtos;

import javax.validation.constraints.NotNull;
import java.util.UUID;

public class PortabilityRequestDto {
    public PortabilityRequestDto() {
    }

    public PortabilityRequestDto(UUID requestid, UserDto user, PortabilityDto portability) {
        this.requestid = requestid;
        this.user = user;
        this.portability = portability;
    }

    private UUID requestid;

    private UserDto user;

    @NotNull(message = "Portability has to be filled.")
    private PortabilityDto portability;

    public UUID getRequestid() {
        return requestid;
    }

    public void setRequestid(UUID requestid) {
        this.requestid = requestid;
    }

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }

    public PortabilityDto getPortability() {
        return portability;
    }

    public void setPortability(PortabilityDto portability) {
        this.portability = portability;
    }
}
