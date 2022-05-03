package br.com.sysmap.domain;

import br.com.sysmap.domain.enums.PortabilityCorporationEnum;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "tb_portabilities")
public class Portability implements Serializable {
    private static final long seralVersionUID = 1L;

    public Portability() {
    }

    public Portability(UUID portabilityId, PortabilityCorporationEnum source, PortabilityCorporationEnum target) {
        this.portabilityId = portabilityId;
        this.source = source;
        this.target = target;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID portabilityId;

    @Enumerated(EnumType.STRING) // Formato que será salvo no BD
    private PortabilityCorporationEnum source;

    @Enumerated(EnumType.STRING)
    private PortabilityCorporationEnum target;

    public UUID getPortabilityId() {
        return portabilityId;
    }

    public void setPortabilityId(UUID portabilityId) {
        this.portabilityId = portabilityId;
    }

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

