package br.com.sysmap.domain;

import br.com.sysmap.domain.enums.PortabilityEnum;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "tb_portabilities")
public class Portability implements Serializable {
    private static final long seralVersionUID = 1L;

    public Portability() {
    }

    public Portability(UUID portabilityId, PortabilityEnum source, PortabilityEnum target) {
        this.portabilityId = portabilityId;
        this.source = source;
        this.target = target;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID portabilityId;

    @Enumerated(EnumType.STRING) // Formato que será salvo no BD
    private PortabilityEnum source;

    @Enumerated(EnumType.STRING)
    private PortabilityEnum target;

    public UUID getPortabilityId() {
        return portabilityId;
    }

    public void setPortabilityId(UUID portabilityId) {
        this.portabilityId = portabilityId;
    }

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

