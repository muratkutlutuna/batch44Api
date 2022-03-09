package get_http_request.day06;

import base_url.GMIBankBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;

public class GetRequest14 extends GMIBankBaseUrl {
    /*
http://www.gmibank.com/api/tp-customers/110472 adresindeki müşteri bilgilerini doğrulayın

"firstName": "Gisele",
"lastName": "Gusikowski",
"email": "marnie.langworth@yahoo.com"
"zipCode": "87796-5904"

"country" "name": "San Jose"
"login": "jani.ernser"
*/
    @Test
    public void test14(){
        spec03.pathParams("1", "tp-customers", "2", "110471");
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
        response.then().assertThat().body("firstName",equalTo("Gisele"),
                "lastName",equalTo("Gusikowski"),
                "email",equalTo("marnie.langworth@yahoo.com"),
                "zipCode",equalTo("87796-5904"),
                "country.name",equalTo("San Jose"),
                "user.login",equalTo("jani.ernser"));
        //JSON PATH iLE
        JsonPath json = response.jsonPath();
        assertEquals("Gisele", json.getString("firstName"));
        assertEquals("Gusikowski", json.getString("lastName"));
        assertEquals("marnie.langworth@yahoo.com", json.getString("email"));
        assertEquals("87796-5904", json.getString("zipCode"));
        assertEquals("San Jose", json.getString("country.name"));
        assertEquals("jani.ernser", json.getString("user.login"));

    }
}
