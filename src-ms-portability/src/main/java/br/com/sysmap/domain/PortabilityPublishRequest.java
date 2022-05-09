package br.com.sysmap.domain;

public class PortabilityPublishRequest {

    private String number;
    private String documentNumber;
    private Portability portability;

    public PortabilityPublishRequest() {
    }

    public PortabilityPublishRequest(String number, String documentNumber, Portability portability) {
        this.number = number;
        this.documentNumber = documentNumber;
        this.portability = portability;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getDocumentNumber() {
        return documentNumber;
    }

    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
    }

    public Portability getPortability() {
        return portability;
    }

    public void setPortability(Portability portability) {
        this.portability = portability;
    }
}
