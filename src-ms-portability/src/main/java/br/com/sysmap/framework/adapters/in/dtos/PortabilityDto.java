package br.com.sysmap.framework.adapters.in.dtos;

import br.com.sysmap.domain.enums.PortabilityCorporationEnum;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

public class PortabilityDto implements Serializable {
    public PortabilityDto() {
    }

    public PortabilityDto(PortabilityCorporationEnum source, PortabilityCorporationEnum target) {
        this.source = source;
        this.target = target;
    }

    private static final long seralVersionUID = 1L;

    @NotBlank(message = "Field 'source' must to be filled" )
    private PortabilityCorporationEnum source;

    @NotBlank(message = "Field 'target' must to be filled" )
    private PortabilityCorporationEnum target;

    public PortabilityCorporationEnum getSource() {
        return source;
    }

    public void setSource(PortabilityCorporationEnum source) {
        this.source = source;
    }

    public PortabilityCorporationEnum getTarget() {
        return target;
    }

    public void setTarget(PortabilityCorporationEnum target) {
        this.target = target;
    }
}
