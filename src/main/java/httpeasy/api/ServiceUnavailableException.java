package httpeasy.api;

public class ServiceUnavailableException extends ServiceException {
    ServiceUnavailableException(String message, Object error) {
        super(message, error);
    }
}
