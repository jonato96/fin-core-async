package com.neoris.commons.library.exception;

import lombok.Getter;

@Getter
public class FinCoreException  extends RuntimeException {

    private final String errorMessage;

    public FinCoreException(final String errorMessage) {
        this.errorMessage = errorMessage;
    }

}
