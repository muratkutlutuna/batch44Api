package get_http_request.day12;

import base_url.JsonPlaceHolderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;
import test_data.JsonPlaceHolderTestData;

import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class PutRequest01 extends JsonPlaceHolderBaseUrl {
    /*
https://jsonplaceholder.typicode.com/todos/198 URL ine aşağıdaki body gönerdiğimde

{
"userId": 21,
"title": "Wash the dishes",
"completed": false
}
Dönen response un status kodunun 200 ve body kısmının aşağıdaki gibi olduğunu test edin
{
"userId": 21,
"title": "Wash the dishes",
"completed": false,
"id": 198
}
*/
    @Test
    public void test01(){
        //URL
        spec04.pathParams("1", "todos", "2", "198");
        //EXPECTED DATA
        JsonPlaceHolderTestData object = new JsonPlaceHolderTestData();
        JSONObject expectedRequest = object.setUpPutData();
        System.out.println("expectedRequest = " + expectedRequest);
        //REQUEST RESPOND
        Response response = given()
                .contentType(ContentType.JSON)
                .spec(spec04)
                .body(expectedRequest.toString())
                .when()
                .put("/{1}/{2}");
        response.prettyPrint();
        //DOGRULAMA
        //json
        JsonPath json = response.jsonPath();
        Assert.assertEquals(200,response.statusCode());
        Assert.assertEquals(expectedRequest.getInt("userId"), json.getInt("userId"));
        Assert.assertEquals(expectedRequest.getString("title"), json.getString("title"));
        Assert.assertEquals(expectedRequest.getBoolean("completed"), json.getBoolean("completed"));

        //de-serialization
        HashMap<String, Object> actualRequest = response.as(HashMap.class);
        Assert.assertEquals(expectedRequest.getInt("userId"),actualRequest.get("userId"));
        Assert.assertEquals(expectedRequest.getString("title"), actualRequest.get("title"));
        Assert.assertEquals(expectedRequest.getBoolean("completed"), actualRequest.get("completed"));

    }
}
