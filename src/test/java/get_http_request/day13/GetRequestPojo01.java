package get_http_request.day13;

import base_url.DummyBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import pojos.Data1;

import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class GetRequestPojo01 extends DummyBaseUrl {
    /*
    GET Request to the URL http://dummy.restapiexample.com/api/v1/employee/1
                               Status code is 200
    {
     "status": "success",
     "data": {
       "id": 1,
       "employee_name": "Tiger Nixon",
       "employee_salary": 320800,
       "employee_age": 61,
       "profile_image": ""
       },
     "message": "Successfully! Record has been fetched."
     }

    */
    @Test
    public void test01() {
        spec02.pathParams("first", "api", "second", "v1", "third", "employee", "fourth", "1");
        Data1 obj = new Data1(1, "Tiger Nixon", 320800, 61, "");
        Response response = given().contentType(ContentType.JSON)
                .spec(spec02).when().get("/{first}/{second}/{third}/{fourth}");
        response.prettyPrint();

        HashMap<String, Object> actualData = response.as(HashMap.class);

        Assert.assertEquals(obj.getEmployee_age(), ((HashMap)actualData.get("data")).get("employee_age"));
    }
}
