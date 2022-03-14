package get_http_request.day13;

import base_url.HerokuAppBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import pojos.BookingDatesPojo;
import pojos.BookingPojo;

import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class PostRequest02 extends HerokuAppBaseUrl {
    /*
   {
     "bookingid": 11,
             "booking": {
                         "firstname": "Ali",
                         "lastname": "Can",
                         "totalprice": 500,
                         "depositpaid": true,
                         "bookingdates": {
                                         "checkin": "2022-03-01",
                                         "checkout": "2022-03-11"
                                         }
                       }
   }
  */
    @Test
    public void test02() {
        //1)URL OLUSTUR
        spec05.pathParams("1", "booking");
        //2)EXPECTED DATA
        BookingDatesPojo objDate = new BookingDatesPojo("2022-03-01", "2022-03-11");
        BookingPojo objPojo = new BookingPojo("Tahsin", "Can", 500, true, objDate);
        System.out.println("objPojo = " + objPojo);
        //3)REQUEST VE RESPONSE
        Response response = given()
                .spec(spec05)
                .contentType(ContentType.JSON)//bazen okumuyor okumasi icin content type yazdiriyoruz
                .auth()
                .basic("admin", "password123")
                .body(objPojo)//bilgileri okuyabilmesi icin to string yapiyoruz
                .when()
                .post("/{1}");

        response.prettyPeek();
        HashMap<String, Object> actualData = response.as(HashMap.class);

        Assert.assertEquals(200, response.statusCode());


    }
}
