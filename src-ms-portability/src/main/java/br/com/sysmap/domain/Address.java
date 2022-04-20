package br.com.sysmap.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Entity(name = "tb_address")
@Table
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Address implements Serializable {
    private static final long seralVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "address_id)")
    private UUID addressId;

    //@Column(nullable = false)
    private String street;

    //@Column(nullable = false)
    private String number;

    //@Column(nullable = false)
    private String city;

    //@Column(nullable = false)
    private String country;

    //@Column(nullable = false)
    private String stateOrRegion;

}
