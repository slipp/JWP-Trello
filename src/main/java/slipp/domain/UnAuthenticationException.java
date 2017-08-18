package slipp.domain;

public class UnAuthenticationException extends Exception {
    private static final long serialVersionUID = 2568164128597871381L;

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
