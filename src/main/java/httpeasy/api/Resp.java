package httpeasy.api;

import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class Resp {
    private final String body;

    public String as(Class<String> stringClass) {
        return null;
    }
}
