package br.com.sysmap.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;



@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "tb_lines")
@Entity
public class Line implements Serializable {
    private static final long seralVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID lineId;

    @Column(nullable = false, length = 11)
    private String number;
}

