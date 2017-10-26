package httpeasy.api;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public final class Http {

    private static final CloseableHttpClient HTTP_CLIENT = HttpClientBuilder.create().build();

    public static Resp get(String uri) {
        try (CloseableHttpResponse execute = HTTP_CLIENT.execute(new HttpGet(uri))) {

            switch (execute.getStatusLine().getStatusCode()) {
                case 200:
                    return Resp.builder()
                            .body(EntityUtils.toString(execute.getEntity()))
                            .build();
                case 400:
                    throw new BadRequestException();
                case 401:
                    throw new UnauthorizedException();
                case 403:
                    throw new ForbiddenException();
                case 404:
                    throw new NotFoundException();
                case 409:
                    throw new ConflictException();
                case 500:
                    throw new InternalServerErrorException();
                case 503:
                    throw new ServiceUnavailableException();
                default:
                    throw new ServiceException();
            }

        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }
}
