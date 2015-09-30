package com.github.jntakpe.sf.exception;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.Map;
import java.util.TreeMap;

/**
 * Exception générale de l'application
 *
 * @author jntakpe
 */
@JsonIgnoreProperties({"stackTrace", "localizedMessage", "suppressed"})
public class SfException extends RuntimeException {

    private ExceptionCode exceptionCode;

    private final Map<String, Object> properties = new TreeMap<String, Object>();

    public SfException(ExceptionCode exceptionCode) {
        this.exceptionCode = exceptionCode;
    }

    public SfException(String message, ExceptionCode exceptionCode) {
        super(message);
        this.exceptionCode = exceptionCode;
    }

    public SfException(Throwable cause, ExceptionCode exceptionCode) {
        super(cause);
        this.exceptionCode = exceptionCode;
    }

    public SfException(String message, Throwable cause, ExceptionCode exceptionCode) {
        super(message, cause);
        this.exceptionCode = exceptionCode;
    }

    /**
     * Transforme une exception existante en {@link SfException}
     *
     * @param exception exception à transformer
     * @return parc exception corresponte
     */
    public static SfException wrap(Throwable exception) {
        return wrap(exception, null);
    }

    /**
     * Transforme une exception existante en {@link SfException}
     *
     * @param exception     exception à transformer
     * @param exceptionCode code a attribuer à l'exception
     * @return parc exception corresponte
     */
    public static SfException wrap(Throwable exception, ExceptionCode exceptionCode) {
        if (exception instanceof SfException) {
            SfException se = (SfException) exception;
            if (exceptionCode != null && exceptionCode != se.getExceptionCode()) {
                return new SfException(exception.getMessage(), exception, exceptionCode);
            }
            return se;
        } else {
            return new SfException(exception.getMessage(), exception, exceptionCode);
        }
    }

    public ExceptionCode getExceptionCode() {
        return exceptionCode;
    }

    public SfException setExceptionCode(ExceptionCode exceptionCode) {
        this.exceptionCode = exceptionCode;
        return this;
    }

    public Map<String, Object> getProperties() {
        return properties;
    }

    @SuppressWarnings("unchecked")
    public <T> T get(String name) {
        return (T) properties.get(name);
    }

    public SfException set(String name, Object value) {
        properties.put(name, value);
        return this;
    }

    public void printStackTrace(PrintStream s) {
        printStackTrace(new PrintWriter(s));

    }

    public void printStackTrace(PrintWriter s) {
        s.println(this);
        s.println("\t-------------------------------");
        if (exceptionCode != null) {
            s.println("\t" + exceptionCode + ":" + exceptionCode.getClass().getName());
        }
        for (String key : properties.keySet()) {
            s.println("\t" + key + "=[" + properties.get(key) + "]");
        }
        s.println("\t-------------------------------");
        StackTraceElement[] trace = getStackTrace();
        for (StackTraceElement aTrace : trace) {
            s.println("\tat " + aTrace);
        }
        Throwable ourCause = getCause();
        if (ourCause != null) {
            ourCause.printStackTrace(s);
        }
        s.flush();
    }

}
