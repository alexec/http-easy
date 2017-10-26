package httpeasy;

import com.github.tomakehurst.wiremock.junit.WireMockRule;
import httpeasy.api.BadRequestException;
import httpeasy.api.Http;
import httpeasy.api.Resp;
import org.junit.Rule;
import org.junit.Test;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.junit.Assert.*;

public class GetErrorMapperTest {
    @Rule
    public WireMockRule wireMockRule = new WireMockRule();

    @Test
    public void mapError() throws Exception {
        wireMockRule.stubFor(get(anyUrl()).willReturn(badRequest().withBody("{\"bar\": \"baz\"}")));

        try {
            assertNotNull(Http.get("http://localhost:8080",
                    (Resp r) -> r.as(Foo.class),
                    Foo::getBar
            ));
            fail();
        } catch (BadRequestException e) {
            assertEquals("baz", e.getMessage());
            assertEquals(new Foo("baz"), e.as(Foo.class));
        }
    }

}
