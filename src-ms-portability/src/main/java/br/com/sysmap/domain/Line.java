package br.com.sysmap.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Table(name = "tb_lines")
@Entity
public class Line implements Serializable {
    private static final long seralVersionUID = 1L;

    public Line() {
    }

    public Line(UUID lineId, String number) {
        this.lineId = lineId;
        this.number = number;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID lineId;

    private String number;

    public UUID getLineId() {
        return lineId;
    }

    public void setLineId(UUID lineId) {
        this.lineId = lineId;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}

