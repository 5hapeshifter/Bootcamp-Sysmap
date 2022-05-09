package br.com.sysmap.domain;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "tb_portabilities_published")
public class PortabilityPublishRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID publishedRequestId;
    private String number;
    private String documentNumber;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "portability_id")
    private Portability portability;

    public PortabilityPublishRequest() {
    }

    public PortabilityPublishRequest(UUID publishedRequestId, String number, String documentNumber, Portability portability) {
        this.publishedRequestId = publishedRequestId;
        this.number = number;
        this.documentNumber = documentNumber;
        this.portability = portability;
    }

    public UUID getPublishedRequestId() {
        return publishedRequestId;
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
                "publishedRequestId=" + publishedRequestId +
                ", number='" + number + '\'' +
                ", documentNumber='" + documentNumber + '\'' +
                ", portability=" + portability +
                '}';
    }
}
