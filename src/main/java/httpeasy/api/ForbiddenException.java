package httpeasy.api;

public class ForbiddenException extends ServiceException {
    ForbiddenException(String message, Object error) {
        super(message, error);
    }
}
