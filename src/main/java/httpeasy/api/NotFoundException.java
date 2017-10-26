package httpeasy.api;

public class NotFoundException extends ServiceException {
    NotFoundException(String message, Object error) {
        super(message, error);
    }
}
