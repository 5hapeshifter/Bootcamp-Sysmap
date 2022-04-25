package br.com.sysmap.framework.exceptions;

import java.io.Serializable;

public class PortabilityNotFoundException extends RuntimeException implements Serializable {
    private static final long seriaLVersionUID = 1L;

    public PortabilityNotFoundException(String msg) {
        super(msg); // Repassando o argumento para o construtor da super classe
    }

}
