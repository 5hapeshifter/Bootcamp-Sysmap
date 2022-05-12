package br.com.sysmap.framework.adapters.out;

import br.com.sysmap.domain.PortabilityStatusEnum;

public class PortabilityResponseStatus {

    private PortabilityStatusEnum status;

    public PortabilityResponseStatus() {
    }

    public PortabilityResponseStatus(PortabilityStatusEnum status) {
        this.status = status;
    }


    public PortabilityStatusEnum getStatus() {
        return status;
    }

    public void setStatus(PortabilityStatusEnum status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "PortabilityResponseStatus{" +
                ", status=" + status +
                '}';
    }
}
