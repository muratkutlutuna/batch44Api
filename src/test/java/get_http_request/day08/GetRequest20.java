package get_http_request.day08;

import base_url.JsonPlaceHolderBaseUrl;
import io.restassured.response.Response;
import org.hamcrest.Matcher;
import org.junit.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class GetRequest20 extends JsonPlaceHolderBaseUrl {
     /*
https://jsonplaceholder.typicode.com/todos/2
2) respose body'de,
         "completed": değerinin false
         "title": değerinin "quis ut nam facilis et officia qui"
         "userId" sinin 1 ve
    header değerlerinden
         "via" değerinin "1.1 vegur" ve
         "Server" değerinin "cloudflare" olduğunu test edin…
*/
    @Test
    public void test20(){
        //1-)URL OLUSTUR
        spec04.pathParams("1", "todos", "2", "2");
        //2-)EXPECTED DATA OLUSTUR
        HashMap<String,Object> expectedData = new HashMap<String,Object>();
        expectedData.put("statusCode", 200);
        expectedData.put("completed", false);
        expectedData.put("title", "quis ut nam facilis et officia qui");
        expectedData.put("userId", 1);
        expectedData.put("via", "1.1 vegur");

        //3-)REQUEST VE RESPONE
        Response response = given().spec(spec04).when().get("/{1}/{2}");
        response.prettyPeek();

        //4-)DOGRULAMA
        //Matchers ile
        response.then().assertThat().statusCode((Integer) expectedData.get("statusCode"));
        response.then().assertThat().body("completed", equalTo(false),
                "title", equalTo("quis ut nam facilis et officia qui"),
                "userId", equalTo(1)).headers("via", equalTo("1.1 vegur"),
                "server", equalTo("cloudflare"));
        //Jsonpath ile

        //serialization ile

        //java assert ile

    }
}
