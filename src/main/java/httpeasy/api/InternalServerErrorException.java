package httpeasy.api;

public class InternalServerErrorException extends ServiceException {
    InternalServerErrorException(String message, Object error) {
        super(message, error);
    }
}
