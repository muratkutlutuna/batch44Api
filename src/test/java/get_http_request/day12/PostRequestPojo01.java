package get_http_request.day12;

import base_url.JsonPlaceHolderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;
import pojos.JsonPlaceHolderPojo;
import test_data.JsonPlaceHolderTestData;

import static io.restassured.RestAssured.given;

public class PostRequestPojo01 extends JsonPlaceHolderBaseUrl {
    /*
   https://jsonplaceholder.typicode.com/todos url 'ine bir request gönderildiğinde
    Request body{
    "userId": 21,
    "id": 201,
    "title": "Tidy your room",
    "completed": false
    }
    Status kodun 201, response body 'nin ise

    {
    "userId": 21,
    "id": 201,
    "title": "Tidy your room",
    "completed": false
    }
    */
    @Test
    public void test01(){
        //URL
        spec04.pathParams("1", "todos");
        //EXPECTED DATA
        JsonPlaceHolderPojo expectedData = new JsonPlaceHolderPojo(21, 201, "Tidyyour room", false);
        System.out.println("expectedData = " + expectedData);
        //REQUEST RESPOND
        Response response = given().contentType(ContentType.JSON).spec(spec04)
                .body(expectedData)
                .when()
                .post("/{1}");
        response.prettyPrint();
        //ASSERTION
        //de-serialization
        JsonPlaceHolderPojo actualData = response.as(JsonPlaceHolderPojo.class);
        Assert.assertEquals(201, response.statusCode());
        Assert.assertEquals(expectedData.getId(), actualData.getId());
        Assert.assertEquals(expectedData.getTitle(), actualData.getTitle());
        Assert.assertEquals(expectedData.getUserId(), actualData.getUserId());
        Assert.assertEquals(expectedData.isCompleted(), actualData.isCompleted());





    }
}
