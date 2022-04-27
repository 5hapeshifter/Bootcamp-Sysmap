package br.com.sysmap.domain;

import br.com.sysmap.domain.enums.PortabilityStatusEnum;
import lombok.Builder;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Builder
@Table(name = "tb_portabilities_requests")
public class PortabilityRequest {

    public PortabilityRequest() {
    }

    public PortabilityRequest(UUID requestid, User user, Portability portability, PortabilityStatusEnum status, LocalDateTime dataDasolicitacao) {
        this.requestid = requestid;
        this.user = user;
        this.portability = portability;
        this.status = status;
        this.dataDasolicitacao = dataDasolicitacao;
    }

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
    private PortabilityStatusEnum status;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime dataDasolicitacao;

    public UUID getRequestid() {
        return requestid;
    }

    public void setRequestid(UUID requestid) {
        this.requestid = requestid;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Portability getPortability() {
        return portability;
    }

    public void setPortability(Portability portability) {
        this.portability = portability;
    }

    public PortabilityStatusEnum getStatus() {
        return status;
    }

    public void setStatus(PortabilityStatusEnum status) {
        this.status = status;
    }

    public LocalDateTime getDataDasolicitacao() {
        return dataDasolicitacao;
    }

    public void setDataDasolicitacao(LocalDateTime dataDasolicitacao) {
        this.dataDasolicitacao = dataDasolicitacao;
    }
}
