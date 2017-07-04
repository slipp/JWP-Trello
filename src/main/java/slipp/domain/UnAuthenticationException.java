package slipp.domain;

public class UnAuthenticationException extends Exception {
    public UnAuthenticationException() {
    }

    public UnAuthenticationException(String message) {
        super(message);
    }

    public UnAuthenticationException(String message, Throwable cause) {
        super(message, cause);
    }

    public UnAuthenticationException(Throwable cause) {
        super(cause);
    }

    public UnAuthenticationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
