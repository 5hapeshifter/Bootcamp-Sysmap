package br.com.sysmap.framework.adapters.in.dtos;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;

public class LineDto implements Serializable {
    private static final long seralVersionUID = 1L;

    @Valid
    @NotBlank(message = "Field 'number' must to be filled")
    @Size(min = 11, max = 11)
    private String number;

    public LineDto() {

    }
    public LineDto(String number) {
        this.number = number;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}

