package get_http_request.day09;

import base_url.HerokuAppBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import test_data.HerokuAppTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class GetRequest22 extends HerokuAppBaseUrl {
    /*
https://restful-booker.herokuapp.com/booking/48
       {
           "firstname": "Ali",
           "lastname": "Can",
           "totalprice": 500,
           "depositpaid": true,
           "bookingdates": {
               "checkin": "2022-02-01",
               "checkout": "2022-02-11"
          }
       }
1) JsonPath
2) De-Serialization
*/
    @Test
    public void test22(){
        //1)URL OLUSTUR
        spec05.pathParams("1","booking","2","14");
        //2) EXPOECTED DATA OLUSTUR
        HerokuAppTestData expectedDataObject = new HerokuAppTestData();
        HashMap<String, Object> expectedData = expectedDataObject.setUpTestData();
        System.out.println("expectedData = " + expectedData);
        //3) REQUEST AND RESPOND
        Response response = given().spec(spec05).when().get("/{1}/{2}");
        response.prettyPeek();
        //4)DOGRULAMA
        //1.yol de-serialization
        HashMap<String, Object> actualData = response.as(HashMap.class);
        System.out.println("actualData = " + actualData);
        Assert.assertEquals(expectedData.get("firstname"),actualData.get("firstname"));
        Assert.assertEquals(expectedData.get("lastname"),actualData.get("lastname"));
        Assert.assertEquals(expectedData.get("totalprice"),actualData.get("totalprice"));
        Assert.assertEquals(expectedData.get("depositpaid"),actualData.get("depositpaid"));
        Assert.assertEquals(expectedData.get("bookingdates"),actualData.get("bookingdates"));
        Assert.assertEquals(((Map)expectedData.get("bookingdates")).get("checkin"),((Map)actualData.get("bookingdates")).get("checkin"));
        Assert.assertEquals(((Map)expectedData.get("bookingdates")).get("checkout"),((Map)actualData.get("bookingdates")).get("checkout"));
//        2.yol Matchers
        response.then().assertThat()
                .body("firstname", equalTo(expectedData.get("firstname")),
                        "lastname", equalTo(expectedData.get("lastname")),
                        "totalprice", equalTo(expectedData.get("totalprice")),
                        "depositpaid",equalTo(expectedData.get("depositpaid")),
                        "bookingdates",equalTo(expectedData.get("bookingdates")),
                        "bookingdates.checkin",equalTo(((Map)expectedData.get("bookingdates")).get("checkin")),
                        "bookingdates.checkout",equalTo(((Map)expectedData.get("bookingdates")).get("checkout")));
//        3.yol JsonPath ile
        JsonPath json = response.jsonPath();
        Assert.assertEquals(expectedData.get("firstname"), json.getString("firstname"));
        Assert.assertEquals(expectedData.get("lastname"), json.getString("lastname"));
        Assert.assertEquals(expectedData.get("totalprice"), json.getInt("totalprice"));
        Assert.assertEquals(expectedData.get("depositpaid"), json.getBoolean("depositpaid"));
        Assert.assertEquals(((Map)expectedData.get("bookingdates")).get("checkin"), json.getString("bookingdates.checkin"));
        Assert.assertEquals(((Map)expectedData.get("bookingdates")).get("checkout"), json.getString("bookingdates.checkout"));


    }

}
