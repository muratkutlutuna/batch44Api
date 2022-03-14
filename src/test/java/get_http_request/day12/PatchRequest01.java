package get_http_request.day12;

import base_url.JsonPlaceHolderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;
import test_data.JsonPlaceHolderTestData;

import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class PatchRequest01 extends JsonPlaceHolderBaseUrl {
    /*
      https://jsonplaceholder.typicode.com/todos/198 URL ine aşağıdaki body gönderdiğimde
     {
        "title": "Batch44"
       }
   Dönen response un status kodunun 200 ve body kısmının aşağıdaki gibi olduğunu test edin
   {
   "userId": 10,
   "title": "Batch44"
   "completed": true,
   "id": 198
   }
       */
    @Test
    public void test01(){
        //URL
        spec04.pathParams("1", "todos", "2", "198");
        //EXPECTED DATA
        JsonPlaceHolderTestData obj = new JsonPlaceHolderTestData();
        JSONObject expectedRequest = obj.setUpPatchRequestData();
        System.out.println("expectedRequest = " + expectedRequest);
        //REQUEST RESPOND
        Response response = given()
                .contentType(ContentType.JSON)
                .spec(spec04)
                .body(expectedRequest.toString())
                .when()
                .patch("/{1}/{2}");
        response.prettyPrint();
        //DOGRULAMA
        HashMap<String, Object> actualData = response.as(HashMap.class);
        JSONObject expectedData = obj.patchExpectedData();
        Assert.assertEquals(expectedData.getInt("userId"), actualData.get("userId"));
        Assert.assertEquals(expectedData.getString("title"), actualData.get("title"));
        Assert.assertEquals(expectedData.getBoolean("completed"), actualData.get("completed"));
        Assert.assertEquals(expectedData.getInt("id"), actualData.get("id"));



    }
}
