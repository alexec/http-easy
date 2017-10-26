package httpeasy.api;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Builder;
import lombok.Value;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@Builder
@Value
public class Resp {
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper()
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    private final String body;

    public <T> T as(Class<T> type) {
        if (type.equals(String.class)) {
            return type.cast(body);
        }
        try {
            Method valueOf = type.getMethod("valueOf", String.class);
            if (valueOf != null) {
                return type.cast(valueOf.invoke(null, body));
            }
            Constructor<T> constructor = type.getConstructor(String.class);
            if (constructor != null) {
                return type.cast(constructor.newInstance(body));
            }
        } catch (IllegalAccessException | InvocationTargetException | InstantiationException e) {
            throw new IllegalStateException(e);
        } catch (NoSuchMethodException ignored) {

        }

        try {
            return OBJECT_MAPPER.readValue(body, type);
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

}
