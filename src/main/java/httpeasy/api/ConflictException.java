package httpeasy.api;

public class ConflictException extends ServiceException {
    ConflictException(String message, Object error) {
        super(message, error);
    }
}
