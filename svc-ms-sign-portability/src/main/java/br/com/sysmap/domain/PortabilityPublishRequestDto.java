package br.com.sysmap.domain;

public class PortabilityPublishRequestDto {

    private String number;
    private String documentNumber;
    private Portability portability;

    public PortabilityPublishRequestDto() {
    }

    public PortabilityPublishRequestDto(String number, String documentNumber, Portability portability) {
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

    @Override
    public String toString() {
        return "PortabilityPublishRequest{" +
                "publishedRequestId=" +
                ", number='" + number + '\'' +
                ", documentNumber='" + documentNumber + '\'' +
                ", portability=" + portability +
                '}';
    }
}
