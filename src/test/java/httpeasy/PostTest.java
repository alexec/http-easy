package httpeasy;

import com.github.tomakehurst.wiremock.junit.WireMockRule;
import com.github.tomakehurst.wiremock.matching.EqualToPattern;
import httpeasy.api.Http;
import org.junit.Rule;
import org.junit.Test;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.junit.Assert.assertNotNull;

public class PostTest {

    @Rule
    public WireMockRule wireMockRule = new WireMockRule(9090);

    @Test
    public void okReturns() throws Exception {

        wireMockRule.stubFor(post(anyUrl()).withRequestBody(new EqualToPattern("\"foo\"")).willReturn(ok()));

        assertNotNull(Http.post("http://localhost:9090", "foo"));
    }

}
