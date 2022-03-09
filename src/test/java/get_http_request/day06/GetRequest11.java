package get_http_request.day06;

import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class GetRequest11 {
    String endPoint = "http://www.gmibank.com/api/tp-customers";
    String bearerToken = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJiYXRjaDQ0YXBpIiwiYXV0aCI6IlJPTEVfQ1VTVE9NRVIiLCJleHAiOjE2NDY2ODEzMjl9.DMZuXqhSFoAXIqiCORWHY93BGfylCiEF9MaMTNc8pv9o3hx0rjDodyOJ8tiL0yBorOT6V0KSDIiCdcmmaMtIuA";

    @Test
    public void test11(){
        Response response = given()
                .header("Authorization", "Bearer " + bearerToken)
                .when()
                .get(endPoint)
                .then()
                .extract()
                .response();//GMI bank icin yapilmis bir onlem olaraka Bearer den sonra bir bosluk birekilmali

        response.prettyPrint();
    }
}
