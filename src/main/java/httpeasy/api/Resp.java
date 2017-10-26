package httpeasy.api;

import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class Resp {
    private final String body;

    public <T> T as(Class<T> type) {
        return type.cast(body);
    }
}
