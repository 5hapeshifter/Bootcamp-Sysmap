package br.com.sysmap.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;



@AllArgsConstructor
@NoArgsConstructor
@Data
@Table
@Entity(name = "tb_line")
public class Line implements Serializable {
    private static final long seralVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "line_id)")
    private UUID lineId;

    //@Column(nullable = false, length = 9)
    private String number;
}

