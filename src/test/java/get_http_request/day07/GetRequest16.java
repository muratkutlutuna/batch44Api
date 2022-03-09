package get_http_request.day07;

import base_url.JsonPlaceHolderBaseUrl;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static utilities.Authentication.generateToken;

public class GetRequest16 extends JsonPlaceHolderBaseUrl {
    //   https://jsonplaceholder.typicode.com/todos/7
    //
    //   {
    //   "userId": 1,
    //   "id": 7,
    //   "title": "illo expedita consequatur quia in",
    //   "completed": false
    //}
    @Test
    public void test16() {
        //1-url olusturma
        spec04.pathParams("1","todos","2","7");
        //2-expected(beklenen) data olustur
        Map<String,Object> expectedData = new HashMap<>();
        expectedData.put("userId",1);
        expectedData.put("id",7);
        expectedData.put("title","illo expedita consequatur quia in");
        expectedData.put("completed",false);
        System.out.println("expectedData = " + expectedData);
        //3-request ve response
        Response response = given()
                .spec(spec04)
                .when()
                .get("/{1}/{2}");

        response.prettyPrint();
        //datayi java'dan -> JSON'a serialization
        //datayi JSON'dan -> java'ya de-serialization

        Map<String,Object>actualData = response.as(HashMap.class);//de-serialization
//        System.out.println("actualData = " + actualData);
        //4-dogrulama
        Assert.assertEquals(expectedData.get("userId"), actualData.get("userId"));
        Assert.assertEquals(expectedData.get("id"), actualData.get("id"));        Assert.assertEquals(expectedData.get("userId"), actualData.get("userId"));
        Assert.assertEquals(expectedData.get("title"), actualData.get("title"));
        Assert.assertEquals(expectedData.get("completed"), actualData.get("completed"));

    }
}
