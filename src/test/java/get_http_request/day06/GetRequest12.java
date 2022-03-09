package get_http_request.day06;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import utilities.Authentication;

import static io.restassured.RestAssured.given;

public class GetRequest12 {
    //Authentication Class'inin icindeki generateToken() methodu kullanilarak
    String endPoint = "http://www.gmibank.com/api/tp-customers";

    @Test
    public void test12() {
        String token = Authentication.generateToken();
        Response response = given().header("Authorization", "Bearer " + token).when().get(endPoint).then().extract().response();
//        response.prettyPeek();
        response.prettyPrint();
        response.then().assertThat().contentType(ContentType.JSON).statusCode(200);


    }
}
