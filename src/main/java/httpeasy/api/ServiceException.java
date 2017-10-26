package httpeasy.api;

public class ServiceException extends RuntimeException {
    private final Object error;

    ServiceException(String message, Object error) {
        super(message);
        this.error = error;
    }

    public <E> E as(Class<E> type) {
        return type.cast(this.error);
    }
}
