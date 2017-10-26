package httpeasy;

import httpeasy.api.Resp;
import lombok.Data;

import static httpeasy.api.Http.get;
import static org.junit.Assert.assertEquals;

public class Demo {
    public static void main(String[] args) {
        {
            Result result = get("http://api.postcodes.io/postcodes/{}", "E3 2NN")
                    .as(Response.class)
                    .getResult();

            // "London"
            System.out.println(result.region);
        }
        {
            Resp resp = get("http://api.postcodes.io/postcodes/{}", "E3 2NN");

            assertEquals("London", resp.get("$.result.region"));
        }

    }

    @Data
    public static class Result {
        private String region;
    }

    @Data
    public static class Response {
        private Result result;
    }
}
