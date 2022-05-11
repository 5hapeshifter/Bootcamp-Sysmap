package br.com.sysmap.framework.adapters.out;

import br.com.sysmap.domain.PortabilityStatusEnum;

import java.util.UUID;

public class PortabilityResponseStatus {

    private UUID id;
    private PortabilityStatusEnum status;

    public PortabilityResponseStatus() {
    }

    public PortabilityResponseStatus(PortabilityStatusEnum status, UUID id) {
        this.id = id;
        this.status = status;
    }

    public PortabilityStatusEnum getStatus() {
        return status;
    }

    public void setStatus(PortabilityStatusEnum status) {
        this.status = status;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "PortabilityResponseStatus{" +
                "id=" + id +
                ", status=" + status +
                '}';
    }
}
