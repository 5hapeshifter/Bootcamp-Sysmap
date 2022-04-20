package br.com.sysmap.domain;

import br.com.sysmap.domain.enums.PortabilityEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "tb_portability")
public class Portability implements Serializable {
    private static final long seralVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    //@Column(name = "portability_id)")
    private UUID portabilityId;

    @Enumerated(EnumType.STRING) // Formato que será salvo no BD
    //@Column(nullable = false)
    private PortabilityEnum source;

    @Enumerated(EnumType.STRING)
    //@Column(nullable = false)
    private PortabilityEnum target;
}
