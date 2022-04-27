package br.com.sysmap.framework.adapters.in.dtos;

import org.hibernate.validator.constraints.br.CPF;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.io.Serializable;
import java.time.LocalDate;

public class UserDto implements Serializable {
    private static final long seralVersionUID = 1L;

    public UserDto(){

    }
    public UserDto(LineDto line, AddressDto address, String name, LocalDate dateOfBirth, String documentNumber) {
        this.line = line;
        this.address = address;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.documentNumber = documentNumber;
    }

    @NotNull(message = "Field 'Line' must to be completely filled" )
    @Valid
    private LineDto line;

    @NotNull(message = "Field 'Address' must to be completely filled" )
    @Valid
    private AddressDto address;

    @NotBlank(message = "Field 'name' cannot be blank")
    private String name;

    @NotNull(message = "Field 'dateOfBirth' cannot be blank")
    @Past(message = "Field 'dateOfBirth' must be a past date")
    private LocalDate dateOfBirth;

    @CPF(message = "Invalid CPF.")
    @NotBlank(message = "Field 'Document number' must be filled with 11 characters")
    private String documentNumber;

    public LineDto getLine() {
        return line;
    }

    public void setLine(LineDto line) {
        this.line = line;
    }

    public AddressDto getAddress() {
        return address;
    }

    public void setAddress(AddressDto address) {
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
