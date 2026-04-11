package com.jhontruse.wsrestaurante.exception;

import lombok.Getter;

@Getter
public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String resource, String field, Object value) {
        super(String.format("%s no encontrado con %s: '%s'", resource, field, value));
    }

    // Para listas vacías: "No se encontraron registros de Menu"
    public ResourceNotFoundException(String message) {
        super(message);
    }

}
