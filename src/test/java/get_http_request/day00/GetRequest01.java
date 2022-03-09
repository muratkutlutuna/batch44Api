package get_http_request.day00;

import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class GetRequest01 {
    @Test
    public void test01(){
        String url = "https://restful-booker.herokuapp.com/booking";

        Response response =given().when().get(url);
        //System.out.println("response = " + response.asPrettyString());
        //response.prettyPrint();

        System.out.println("response.statusCode() = " + response.statusCode());
        System.out.println("response.contentType() = " + response.contentType());
        System.out.println("response.statusLine() = " + response.statusLine());
        System.out.println("response.time() = " + response.time());
        Assert.assertEquals(200, response.statusCode());
        Assert.assertEquals("application/json; charset=utf-8",response.contentType());
        Assert.assertEquals("HTTP/1.1 200 OK", response.statusLine());

        response
                .then()
                .assertThat()       //hard asserttur
                .statusCode(200)
                .contentType("application/json; charset=utf-8")
                .statusLine("HTTP/1.1 200 OK");

    }
}
