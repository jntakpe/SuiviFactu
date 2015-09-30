package com.github.jntakpe.sf.exception;

/**
 * Codes d'exception fonctionnels
 *
 * @author jntakpe
 */
public enum FunctionalCode implements ExceptionCode {

    VALIDATION(400),
    NOT_AUTHENTICATED(401),
    USERNAME_NOT_FOUND(401),
    WRONG_PASSWORD(401),
    ACCOUNT_LOCKED(401);

    private final int status;

    FunctionalCode(int status) {
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
