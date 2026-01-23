package cloud.fesh.msgplatform.exception;

public class ThirdAuthException extends RuntimeException {

    public ThirdAuthException(String message) {
        super(message);
    }

    public ThirdAuthException(String message, Throwable cause) {
        super(message, cause);
    }

    public ThirdAuthException(Throwable cause) {
        super(cause);
    }
}
