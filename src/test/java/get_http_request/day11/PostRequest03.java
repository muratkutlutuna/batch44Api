package get_http_request.day11;

import base_url.JsonPlaceHolderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;
import test_data.JsonPlaceHolderTestData;

import java.lang.reflect.Type;
import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class PostRequest03 extends JsonPlaceHolderBaseUrl {
    /*
   https://jsonplaceholder.typicode.com/todos URL ine aşağıdaki body gönderildiğinde,
    {
    "userId": 55,
    "title": "Tidy your room",
    "completed": false
  }
    Dönen response un Status kodunun 201 ve response body nin aşağıdaki gibi olduğunu test edin
  {
    "userId": 55,
    "title": "Tidy your room",
    "completed": false,
    "id": …
   }
*/
    @Test
    public void test03(){
        //1)URL
        spec04.pathParams("1", "todos");
        //2)EXPECTED DATA
        JsonPlaceHolderTestData object = new JsonPlaceHolderTestData();
        JSONObject requestedAndExpectedData = object.requestedAndExpectedData();
        System.out.println("requestedAndExpectedData = " + requestedAndExpectedData);
        //3)REQUEST RESPOND
        Response response = given()
                .contentType(ContentType.JSON)
                .spec(spec04)
                .body(requestedAndExpectedData.toString())
                .when()
                .post("/{1}");
        response.prettyPrint();
        //4)DOGRULAMA
        //matchers ile

        //json formatiyla
        Assert.assertEquals(response.getStatusCode(), requestedAndExpectedData.getInt("statusCode"));
        response.then().assertThat().statusCode(requestedAndExpectedData.getInt("statusCode"));//expected data yi Map formatinda getirseydik calismayabilirdi, hatta Casting isterdi, ama JSONObject formatinda calistiriyor
        JsonPath json = response.jsonPath();
        Assert.assertEquals(requestedAndExpectedData.getInt("userId"), json.getInt("userId"));
        Assert.assertEquals(requestedAndExpectedData.getString("title"), json.getString("title"));
        Assert.assertEquals(requestedAndExpectedData.getBoolean("completed"),json.getBoolean("completed"));
        //de-serialization ile
        HashMap<String, Object>actualData = response.as(HashMap.class);
        System.out.println("actualData = " + actualData);
        Assert.assertEquals(requestedAndExpectedData.getBoolean("completed"),actualData.get("completed"));
        Assert.assertEquals(requestedAndExpectedData.getInt("userId"), actualData.get("userId"));
        Assert.assertEquals(requestedAndExpectedData.getString("title"), actualData.get("title"));
    }
}
