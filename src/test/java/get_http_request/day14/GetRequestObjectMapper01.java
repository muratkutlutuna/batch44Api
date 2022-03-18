package get_http_request.day14;

import base_url.JsonPlaceHolderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import utilities.JsonUtil;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class GetRequestObjectMapper01 extends JsonPlaceHolderBaseUrl {
      /*https://jsonplaceholder.typicode.com/todos/198 url’ine bir get request gönderildiğinde,
      Dönen response ’un status kodunun 200 ve body kısmının
{
"userId": 10,
"id": 198,
"title": "quis eius est sint explicabo",
"completed": true
}
      Olduğunu Object Mapper kullanarak test edin*/
    @Test
    public void test(){
        //1)URL OLUSTUR
        spec04.pathParams("1", "todos", "2", "198");
        //2)EXPECTED DATA
        String jsonData = "{\n" +
                "\"userId\": 10,\n" +
                "\"id\": 198,\n" +
                "\"title\": \"quis eius est sint explicabo\",\n" +
                "\"completed\": true\n" +
                "}";
        System.err.println("jsonData = " + jsonData);
        Map<String,Object>expectedData = JsonUtil.convertJsonToJava(jsonData, Map.class);
        System.out.println("expectedData = " + expectedData);
        //3)REQUEST AND RESPONSE
        Response response = given().contentType(ContentType.JSON).spec(spec04).when().get("/{1}/{2}");
        response.prettyPrint();
        //4)DOGRULAMA
        //de-serialization
        HashMap<String,Object>actualData2 = response.as(HashMap.class);
        System.out.println("actualData2 = " + actualData2);
        //JsonUtil reusable classini kullanarak de-serialization yaptik.
        Map<String,Object>actualData = JsonUtil.convertJsonToJava(response.asString(),Map.class);
        System.out.println("actualData = " + actualData);

        Map<String, Object>expected = JsonUtil.convertJsonToJava(jsonData, LinkedHashMap.class);
        System.out.println("expected = " + expected);
        Map<String, Object>actual = JsonUtil.convertJsonToJava(jsonData, LinkedHashMap.class);
        System.out.println("actual = " + actual);
        Assert.assertEquals(expected, actual);

        Assert.assertEquals(expectedData.get("userId"), actualData.get("userId"));
        Assert.assertEquals(expectedData.get("id"), actualData.get("title"));
        Assert.assertEquals(expectedData.get("title"), actualData.get("title"));
        Assert.assertEquals(expectedData.get("completed"), actualData.get("completed"));

        //Matchers
        response.then().
                assertThat().
                statusCode(200).
                body("userId", equalTo(expectedData.get("userId"))
                        ,"id",equalTo(expectedData.get("id"))
                        ,"title",equalTo(expectedData.get("title"))
                        ,"completed",equalTo(expectedData.get("completed")));



    }
}
