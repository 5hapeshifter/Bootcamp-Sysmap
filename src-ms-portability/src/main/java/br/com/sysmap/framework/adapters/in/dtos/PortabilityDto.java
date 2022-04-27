package br.com.sysmap.framework.adapters.in.dtos;

import br.com.sysmap.domain.enums.PortabilityEnum;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class PortabilityDto implements Serializable {
    public PortabilityDto() {
    }

    public PortabilityDto(PortabilityEnum source, PortabilityEnum target) {
        this.source = source;
        this.target = target;
    }

    private static final long seralVersionUID = 1L;

    @NotNull(message = "Field 'source' must to be filled" )
    private PortabilityEnum source;

    @NotNull(message = "Field 'target' must to be filled" )
    private PortabilityEnum target;

    public PortabilityEnum getSource() {
        return source;
    }

    public void setSource(PortabilityEnum source) {
        this.source = source;
    }

    public PortabilityEnum getTarget() {
        return target;
    }

    public void setTarget(PortabilityEnum target) {
        this.target = target;
    }
}
