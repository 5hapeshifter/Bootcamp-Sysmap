package br.com.sysmap.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "tb_addresses")
public class Address implements Serializable {
    private static final long seralVersionUID = 1L;

    public Address() {
    }

    public Address(UUID addressId, String street, String number, String city, String country, String stateOrRegion) {
        this.addressId = addressId;
        this.street = street;
        this.number = number;
        this.city = city;
        this.country = country;
        this.stateOrRegion = stateOrRegion;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID addressId;

    private String street;

    private String number;

    private String city;

    private String country;

    private String stateOrRegion;;

    public UUID getAddressId() {
        return addressId;
    }

    public void setAddressId(UUID addressId) {
        this.addressId = addressId;
    }

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
