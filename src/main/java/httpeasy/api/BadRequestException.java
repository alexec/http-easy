package httpeasy.api;

public class BadRequestException extends ServiceException {
    BadRequestException(String message, Object error) {
        super(message, error);
    }
}
