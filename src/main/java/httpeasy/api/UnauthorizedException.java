package httpeasy.api;

public class UnauthorizedException extends ServiceException {
    UnauthorizedException(String message, Object error) {
        super(message, error);
    }
}
