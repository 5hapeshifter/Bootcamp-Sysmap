package br.com.sysmap.framework.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StandardError implements Serializable {
    private static final long seriaLVersionUID = 1L;

    private Instant timestamp;
    private int status;
    private String error;
    private String message;
    private String path;
}
