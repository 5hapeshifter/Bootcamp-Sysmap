package br.com.sysmap.framework.adapters.in.dtos;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

public class AddressDto implements Serializable {
    private static final long seralVersionUID = 1L;

    public AddressDto() {
    }

    public AddressDto(String street, String number, String city, String country, String stateOrRegion) {
        this.street = street;
        this.number = number;
        this.city = city;
        this.country = country;
        this.stateOrRegion = stateOrRegion;
    }

    @NotBlank(message = "Field 'street' cannot be blank")
    private String street;

    @NotBlank(message = "Field 'number' cannot be blank")
    private String number;

    @NotBlank(message = "Field 'city' cannot be blank")
    private String city;

    @NotBlank(message = "Field 'country' cannot be blank")
    private String country;

    @NotBlank(message = "Field 'State or region' cannot be blank")
    private String stateOrRegion;

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getStateOrRegion() {
        return stateOrRegion;
    }

    public void setStateOrRegion(String stateOrRegion) {
        this.stateOrRegion = stateOrRegion;
    }
}
