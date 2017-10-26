package httpeasy;

import httpeasy.api.*;

import java.util.Arrays;
import java.util.List;

public class ServiceExceptionTest {

    // this is a test - won't compile if they are not sub-classes
    private final List<Class<? extends ServiceException>> testSubClasses = Arrays.asList(
            BadRequestException.class,
            ConflictException.class,
            ForbiddenException.class,
            InternalServerErrorException.class,
            NotFoundException.class,
            UnauthorizedException.class
    );

}