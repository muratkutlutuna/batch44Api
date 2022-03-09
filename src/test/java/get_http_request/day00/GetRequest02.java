package get_http_request.day00;

import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;

public class GetRequest02 {
    @Test
    public void test02() {
        String url = "https://reqres.in/api/users";
        Response response = given().when().get(url);
//        response.prettyPrint();       //response'taki body'i getirir
//        response.prettyPeek();        //response'taki herseyi getirir
        response.then().log().all();    //response'taki herseyi getirir

        //headers
        response.then().assertThat().statusCode(200).contentType("application/json; charset=utf-8").statusLine("HTTP/1.1 200 OK");

        //body
        response.then().body("data[0].first_name", equalTo("George"),
                "data[0].last_name", equalTo("Bluth"),
                "data[0].email", containsString("george.bluth"));

        response.then().body("data[1].id", equalTo(2)
                , "data[1].email", equalTo("janet.weaver@reqres.in")
                , "data[1].first_name", equalTo("Janet")
                , "data[1].last_name", equalTo("Weaver")
                , "data[1].avatar", equalTo("https://reqres.in/img/faces/2-image.jpg"));





    }
}
