package httpeasy.api;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.function.Function;

public final class Http {

    private static final CloseableHttpClient HTTP_CLIENT = HttpClientBuilder.create().build();

    public static Resp get(String uri, String... queryParams) {
        String finalUri = mergeParams(uri, queryParams);
        return get(finalUri, resp -> finalUri, e -> e);
    }

    private static String mergeParams(String uri, String[] queryParams) {
        int i = 0;
        while (uri.contains("{}")) {
            try {
                uri = uri.replace("{}", URLEncoder.encode(queryParams[i++], "UTF-8").replaceFirst("\\+", "%20"));
            } catch (UnsupportedEncodingException e) {
                throw new IllegalStateException(e);
            }
        }
        return uri;
    }

    public static <E> Resp get(String uri, Function<Resp, E> errorMapper, Function<E, String> errorMessageMapper) {
        return execute(errorMapper, errorMessageMapper, new HttpGet(uri));
    }

    private static <E> Resp execute(Function<Resp, E> errorMapper, Function<E, String> errorMessageMapper, HttpUriRequest request) {
        try (CloseableHttpResponse execute = HTTP_CLIENT.execute(request)) {
            Resp resp = Resp.builder()
                    .body(EntityUtils.toString(execute.getEntity()))
                    .build();

            int statusCode = execute.getStatusLine().getStatusCode();

            if (statusCode == 200) {
                return resp;
            }

            E error = errorMapper.apply(resp);
            String msg = errorMessageMapper.apply(error);
            switch (statusCode) {
                case 400:
                    throw new BadRequestException(msg, error);
                case 401:
                    throw new UnauthorizedException(msg, error);
                case 403:
                    throw new ForbiddenException(msg, error);
                case 404:
                    throw new NotFoundException(msg, error);
                case 409:
                    throw new ConflictException(msg, error);
                case 500:
                    throw new InternalServerErrorException(msg, error);
                case 503:
                    throw new ServiceUnavailableException(msg, error);
                default:
                    throw new ServiceException(msg, error);
            }
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    public static <R> Resp post(String uri, R body) {
        HttpPost post = new HttpPost(uri);
        try {
            post.setEntity(new StringEntity(Mapper.parseObject(body)));
            post.setHeader("Content-Type", "application/json");
        } catch (UnsupportedEncodingException e) {
            throw new IllegalStateException(e);
        }

        return execute(resp -> uri, e -> e, post);
    }
}
