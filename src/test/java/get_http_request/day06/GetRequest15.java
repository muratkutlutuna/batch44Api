package get_http_request.day06;

import base_url.GMIBankBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;

public class GetRequest15 extends GMIBankBaseUrl {
    /*
https://www.gmibank.com/api/tp-customers/85694
        "login": "dino.kohler",
    "firstName": "Winona",
    "lastName": "Abernathy",
    "email": "winonaabernathy@gmail.com"

 */
    @Test
    public void test15(){
        spec03.pathParams("1", "tp-customers", "2", "85694");
        Response response = given()
                .spec(spec03)
                .header("Authorization", "Bearer " +generateToken())
                .when()
                .get("/{1}/{2}")
                .then()
                .extract()
                .response();
        response.prettyPrint();
        //Matcher ile
        response.then().assertThat().body("user.firstName",equalTo("Winona"),
                "user.lastName",equalTo("Abernathy"),
                "user.email",equalTo("winonaabernathy@gmail.com"),
                "user.login",equalTo("dino.kohler"));
        //JSON PATH iLE
        JsonPath json = response.jsonPath();
        assertEquals("Winona", json.getString("firstName"));
        assertEquals("Abernathy", json.getString("lastName"));
        assertEquals("winonaabernathy@gmail.com", json.getString("email"));
        assertEquals("dino.kohler", json.getString("user.login"));
    }
}
