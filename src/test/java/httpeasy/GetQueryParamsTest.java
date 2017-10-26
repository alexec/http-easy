package httpeasy;

import com.github.tomakehurst.wiremock.junit.WireMockRule;
import httpeasy.api.Http;
import org.junit.Rule;
import org.junit.Test;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

public class GetQueryParamsTest {
    @Rule
    public WireMockRule wireMockRule = new WireMockRule();

    @Test
    public void urlEncoding() throws Exception {
        wireMockRule.stubFor(get(urlMatching(".*\\?foo=baz\\%20qux")).willReturn(ok()));

        Http.get("http://localhost:8080?foo={}", "baz qux");
    }

}
