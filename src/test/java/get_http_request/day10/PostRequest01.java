package get_http_request.day10;

import base_url.HerokuAppBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;
import test_data.HerokuAppTestData;

import static io.restassured.RestAssured.given;

public class PostRequest01 extends HerokuAppBaseUrl {
     /*
   https://restful-booker.herokuapp.com/booking
   { "firstname": "Ali",
              "lastname": "Can",
              "totalprice": 500,
              "depositpaid": true,
              "bookingdates": {
                  "checkin": "2022-03-01",
                  "checkout": "2022-03-11"
               }
}
gönderildiğinde, Status kodun 200 olduğunu ve dönen response body nin ,
}
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
olduğunu test edin
    */
    @Test
    public void test01(){
        //1)URL OLUSTUR
        spec05.pathParams("1", "booking");
        //2)EXPECTED DATA
        HerokuAppTestData testData = new HerokuAppTestData();
        JSONObject expectedRequestData = testData.setUpTestAndRequestData();
        System.out.println("expectedRequestData = " + expectedRequestData);
        //3)REQUEST VE RESPONSE
        Response response = given()
                .contentType(ContentType.JSON)//bazen okumuyor okumasi icin content type yazdiriyoruz
                .auth()
                .basic("admin","password123")
                .spec(spec05)
                .body(expectedRequestData.toString())//bilgileri okuyabilmesi icin to string yapiyoruz
                .when()
                .post("/{1}");

        response.prettyPeek();
        Assert.assertEquals(200,response.statusCode());

        JsonPath json = response.jsonPath();
        Assert.assertEquals(expectedRequestData.getString("firstname"), json.getString("booking.firstname"));
        Assert.assertEquals(expectedRequestData.getString("lastname"), json.getString("booking.lastname"));
        Assert.assertEquals(expectedRequestData.getInt("totalprice"), json.getInt("booking.totalprice"));
        Assert.assertEquals(expectedRequestData.getBoolean("depositpaid"), json.getBoolean("booking.depositpaid"));

        Assert.assertEquals(expectedRequestData.getJSONObject("bookingdates").getString("checkin"),
                json.getString("booking.bookingdates.checkin"));
        Assert.assertEquals(expectedRequestData.getJSONObject("bookingdates").getString("checkout"),
                json.getString("booking.bookingdates.checkout"));









    }

}
