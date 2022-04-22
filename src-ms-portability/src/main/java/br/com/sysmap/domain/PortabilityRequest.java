package br.com.sysmap.domain;

import br.com.sysmap.domain.enums.PortabilityStatusEnum;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;
@Getter
@Setter
@EqualsAndHashCode
@Builder
@Entity
@Table(name = "tb_portabilities_requests")
public class PortabilityRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID requestid;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "portability_id")
    private Portability portability;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PortabilityStatusEnum status;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime dataDasolicitacao;
}
