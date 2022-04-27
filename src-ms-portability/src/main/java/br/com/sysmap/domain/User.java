package br.com.sysmap.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "tb_users")
//@Builder
public class User implements Serializable {
    private static final long seralVersionUID = 1L;

    public User() {
    }

    public User(UUID userId, Line line, Address address, String name, LocalDate dateOfBirth, String documentNumber) {
        this.userId = userId;
        this.line = line;
        this.address = address;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.documentNumber = documentNumber;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID userId;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "line_id")
    private Line line;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    private Address address;

    private String name;

    private LocalDate dateOfBirth;

    private String documentNumber;

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public Line getLine() {
        return line;
    }

    public void setLine(Line line) {
        this.line = line;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getDocumentNumber() {
        return documentNumber;
    }

    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
    }
}
