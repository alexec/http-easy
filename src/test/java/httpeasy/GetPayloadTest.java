package httpeasy;

import com.github.tomakehurst.wiremock.junit.WireMockRule;
import httpeasy.api.*;
import org.junit.Rule;
import org.junit.Test;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

public class GetPayloadTest {
    @Rule
    public WireMockRule wireMockRule = new WireMockRule();

    @Test
    public void okReturns() throws Exception {
        wireMockRule.stubFor(get(anyUrl()).willReturn(ok()));

        Http.get("http://localhost:8080");
    }

    @Test(expected = BadRequestException.class)
    public void badRequestThrowIllegalArgumentException() throws Exception {
        wireMockRule.stubFor(get(anyUrl()).willReturn(badRequest()));

        Http.get("http://localhost:8080");
    }


    @Test(expected = UnauthorizedException.class)
    public void unauthorizedThrowIllegalArgumentException() throws Exception {
        wireMockRule.stubFor(get(anyUrl()).willReturn(unauthorized()));

        Http.get("http://localhost:8080");
    }

    @Test(expected = ForbiddenException.class)
    public void forbiddenThrownIllegalArgumentException() throws Exception {
        wireMockRule.stubFor(get(anyUrl()).willReturn(forbidden()));

        Http.get("http://localhost:8080");
    }


    @Test(expected = NotFoundException.class)
    public void notFoundReturnNull() throws Exception {
        wireMockRule.stubFor(get(anyUrl()).willReturn(notFound()));

        Http.get("http://localhost:8080");
    }


    @Test(expected = ConflictException.class)
    public void conflictReturnConflictException() throws Exception {
        wireMockRule.stubFor(get(anyUrl()).willReturn(status(409)));

        Http.get("http://localhost:8080");
    }

    @Test(expected = InternalServerErrorException.class)
    public void serverErrorsThrowIllegal() throws Exception {
        wireMockRule.stubFor(get(anyUrl()).willReturn(serverError()));

        Http.get("http://localhost:8080");
    }

    @Test(expected = ServiceUnavailableException.class)
    public void serviceUnavailableErrorsThrowIllegal() throws Exception {
        wireMockRule.stubFor(get(anyUrl()).willReturn(serviceUnavailable()));

        Http.get("http://localhost:8080");
    }

}
