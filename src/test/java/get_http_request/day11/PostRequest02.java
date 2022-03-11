package get_http_request.day11;

import base_url.DummyBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;
import test_data.DummyTestData;

import static io.restassured.RestAssured.given;

public class PostRequest02 extends DummyBaseUrl {
      /*
   http://dummy.restapiexample.com/api/v1/create url ine, Request Body olarak
   {
       "name":"Ali Can",
       "salary":"2000",
       "age":"40",
   }
   gönderildiğinde,Status kodun 200 olduğunu ve dönen response body nin,

   {
       "status": "success",
       "data": {
       "id":…
   },
       "message": "Successfully! Record has been added."
   }

   olduğunu test edin
    */
    @Test
    public void test02(){

        //1) URL OLUSTUR
        spec02.pathParams("1", "api", "2", "v1", "3", "create");
        //2) EXPECTED DATA
        DummyTestData object = new DummyTestData();
        JSONObject requestData = object.setUpRequestBody();
        System.out.println("requestData = " + requestData);
        //3) REQUEST VE RESPONSE
        Response response = given()
                .contentType(ContentType.JSON)//burayi .accept(ContentType.JSON) seklinde de yazabilirdim ama burada request datayi girmemis oluyor
                .spec(spec02)
                .body(requestData.toString())//Post isleminde map kullandigimizda toString e gerek yok, ama ben burda JSONObject kullandim o yuzden gerek var.aksi halde testin ciktisinda data'da emptu:"false" getiriyot
                .when()
                .post("/{1}/{2}/{3}");
        response.prettyPeek();
        //4) DOĞRULAMA
        JSONObject expectedData = object.postTestData();
        Assert.assertEquals(200,response.statusCode());
        JsonPath json = response.jsonPath();
        Assert.assertEquals(expectedData.getString("status"), json.getString("status"));
        Assert.assertTrue(expectedData.getJSONObject("data").getInt("id")!=json.getInt("data.id"));
        Assert.assertEquals(expectedData.getString("message"), json.getString("message"));
    }

}
