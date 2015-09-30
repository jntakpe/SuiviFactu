package com.github.jntakpe.sf.exception;

/**
 * Codes d'exception techniques
 *
 * @author jntakpe
 */
public enum TechnicalCode implements ExceptionCode {

    NO_RESULT(404),
    ASYNC(500),
    ENCODING(500),
    //Ne devrait jamais arriver il faut surveiller ce type d'exception
    ILLEGAL_STATE(500);

    private final int status;

    TechnicalCode(int status) {
        this.status = status;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getStatus() {
        return status;
    }
}
