package get_http_request.day07;

import base_url.GMIBankBaseUrl;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class GetRequest17 extends GMIBankBaseUrl {
    //   http://www.gmibank.com/api/tp-customers/114351 adresindeki müşteri bilgilerini doğrulayın
    //
    //{
    //   "firstName": "Della",
    //   "lastName": "Heaney",
    //   "email": "ricardo.larkin@yahoo.com",
    //   "mobilePhoneNumber": "123-456-7893",
    //}
    @Test
    public void test17(){
        spec03.pathParams("1", "tp-customers", "2", "114351");
        Response response = given()
                .spec(spec03)
                .header("Authorization", "Bearer " + generateToken())
                .when()
                .get("/{1}/{2}")
                .then()
                .extract()
                .response();
        Map<String, Object> expectedData = new HashMap<>();
        expectedData.put("firstName","Della");
        expectedData.put("lastName","Heaney");
        expectedData.put("email","ricardo.larkin@yahoo.com");
        expectedData.put("mobilePhoneNumber","123-456-7893");
        Map<String,Object>actualData = response.as(HashMap.class);
        Assert.assertEquals(expectedData.get("firstName"), actualData.get("firstName"));
        Assert.assertEquals(expectedData.get("lastName"), actualData.get("lastName"));
        Assert.assertEquals(expectedData.get("email"), actualData.get("email"));
        Assert.assertEquals(expectedData.get("mobilePhoneNumber"), actualData.get("mobilePhoneNumber"));
        Map<String,Object>actualData2=response.as(Map.class);
        Assert.assertEquals(expectedData.get("firstName"), actualData2.get("firstName"));
        Assert.assertEquals(expectedData.get("lastName"), actualData2.get("lastName"));
        Assert.assertEquals(expectedData.get("email"), actualData2.get("email"));
        Assert.assertEquals(expectedData.get("mobilePhoneNumber"), actualData2.get("mobilePhoneNumber"));

    }
}
