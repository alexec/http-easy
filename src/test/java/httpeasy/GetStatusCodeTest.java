package httpeasy;

import com.github.tomakehurst.wiremock.junit.WireMockRule;
import httpeasy.api.Http;
import org.junit.Rule;
import org.junit.Test;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class GetStatusCodeTest {
    @Rule
    public WireMockRule wireMockRule = new WireMockRule();

    @Test
    public void okReturns() throws Exception {
        wireMockRule.stubFor(get(anyUrl()).willReturn(ok()));

        assertNotNull(Http.get("http://localhost:8080"));
    }

    @Test
    public void bodyAsInt() throws Exception {
        wireMockRule.stubFor(get(anyUrl()).willReturn(ok().withBody("1")));

        int as = Http.get("http://localhost:8080").as(int.class);
        assertEquals(1, as);
    }

    @Test
    public void bodyAsInteger() throws Exception {
        wireMockRule.stubFor(get(anyUrl()).willReturn(ok().withBody("1")));

        Integer as = Http.get("http://localhost:8080").as(Integer.class);
        assertEquals(1, as.intValue());
    }

    @Test
    public void bodyAsString() throws Exception {
        wireMockRule.stubFor(get(anyUrl()).willReturn(ok().withBody("Ok")));

        assertEquals("Ok", Http.get("http://localhost:8080").as(String.class));
    }

    @Test
    public void bodyAsJson() throws Exception {
        wireMockRule.stubFor(get(anyUrl()).willReturn(ok().withHeader("Content-Type", "application/json").withBody("{\"bar\": \"baz\"}")));

        assertEquals(new Foo("baz"), Http.get("http://localhost:8080").as(Foo.class));
    }
}
