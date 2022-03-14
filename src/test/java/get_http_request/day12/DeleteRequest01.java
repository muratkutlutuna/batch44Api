package get_http_request.day12;

import base_url.DummyBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;
import test_data.DummyTestData;

import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class DeleteRequest01 extends DummyBaseUrl {
    /*
  http://dummy.restapiexample.com/api/v1/delete/2 bir DELETE request gönderdiğimde

Dönen response un status kodunun 200 ve body kısmının aşağıdaki gibi olduğunu test edin
{
"status": "success",
"data": "2",
"message": "Successfully! Record has been deleted"
}
   */
    @Test
    public void test01(){
        //URL
        spec02.pathParams("1", "api", "2", "v1", "3", "delete", "4", "2");
        //EXPECTED DATA
        DummyTestData object = new DummyTestData();
        JSONObject expectedData = object.setUpDeleteExpectedData();
        System.out.println("expectedData = " + expectedData);
        //REQUEST RESPOND
        Response response = given()
                .contentType(ContentType.JSON)
                .spec(spec02)
                .when()
                .delete("/{1}/{2}/{3}/{4}");
        response.prettyPrint();
        //DOGRULAMA
        HashMap<String, Object>actualData = response.as(HashMap.class);
        Assert.assertEquals(expectedData.getString("status"), actualData.get("status"));
        Assert.assertEquals(expectedData.getString("data"), actualData.get("data"));
        Assert.assertEquals(expectedData.getString("message"),actualData.get("message"));
        JsonPath json = response.jsonPath();
        Assert.assertEquals(200, response.statusCode());
        Assert.assertEquals(expectedData.getString("status"), json.getString("status"));
        Assert.assertEquals(expectedData.getString("data"), json.getString("data"));
        Assert.assertEquals(expectedData.getString("message"), json.getString("message"));

    }
}
