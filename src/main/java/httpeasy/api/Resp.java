package httpeasy.api;

import com.jayway.jsonpath.JsonPath;
import lombok.Builder;

@Builder
public class Resp {
    private final String body;

    public String as() {
        return as(String.class);
    }

    public <T> T as(Class<T> type) {
        return Mapper.valueOf(body, type);
    }

    public String get(String path) {
        return get(path, String.class);
    }

    public <T> T get(String path, Class<T> type) {
        return Mapper.valueOf(JsonPath.read(body, path).toString(), type);
    }
}
