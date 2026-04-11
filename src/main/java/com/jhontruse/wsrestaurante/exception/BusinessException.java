package com.jhontruse.wsrestaurante.exception;

import lombok.Getter;

@Getter
public class BusinessException extends RuntimeException {

    private final Integer status;
    private final String code;

    public BusinessException(String message, Integer status, String code) {
        super(message);
        this.status = status;
        this.code = code;
    }

    public BusinessException(Integer status, String code, String message) {
        super(message);
        this.status = status;
        this.code = code;
    }
}
