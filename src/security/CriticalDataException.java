package security;

public class CriticalDataException extends Exception{

    public CriticalDataException() {
    }

    public CriticalDataException(String message) {
        super(message);
    }

    public CriticalDataException(Throwable cause) {
        super(cause);
    }

    public CriticalDataException(String message, Throwable cause) {
        super(message, cause);
    }

    public CriticalDataException(String message, Throwable cause, boolean enableSuppression,
            boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    
}
