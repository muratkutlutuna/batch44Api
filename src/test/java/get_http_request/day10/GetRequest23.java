package get_http_request.day10;

import base_url.DummyBaseUrl;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import test_data.DummyTestData;

import java.util.*;

import static com.fasterxml.jackson.databind.type.LogicalType.Map;
import static io.restassured.RestAssured.given;

public class GetRequest23 extends DummyBaseUrl {
    /*
http://dummy.restapiexample.com/api/v1/employees url ine bir istek gönderildiğinde
  olduğunu test edin.
*/
    @Test
    public void test23(){
        //1)URL OLUSTUR
        spec02.pathParams("1", "api", "2", "v1", "3", "employees");
        //2)EXPECTED DATA OLUSTUR
        DummyTestData expectedObje = new DummyTestData();
        HashMap<String, Object> expectedData = expectedObje.setUpTestData();
        //3)REQUEST VE RESPONSE
        Response response = given().spec(spec02).when().get("/{1}/{2}/{3}");
//        response.prettyPeek();
        //Status kodun 200 olduğunu,
        //14. Çalışan isminin "Haley Kennedy" olduğunu,
        //Çalışan sayısının 24 olduğunu,
        //Sondan 3. çalışanın maaşının 675000 olduğunu
        //40,21 ve 19 yaslarında çalışanlar olup olmadığını
        //10. Çalışan bilgilerinin bilgilerinin aşağıdaki gibi
        //
        //{
        //        "id": 10,
        //        "employee_name": "Sonya Frost",
        //        "employee_salary": 103600,
        //        "employee_age": 23,
        //        "profile_image": ""
        // }
        //4)DOGRULAMA
        //1.yol De-serialization
        HashMap<String, Object> actualData = response.as(HashMap.class);
        System.out.println("actualData = " + actualData);
        Assert.assertEquals(expectedData.get("statusCode"), response.getStatusCode());
        Assert.assertEquals(expectedData.get("ondorduncucalisan"),((Map)((List)actualData.get("data")).get(13)).get("employee_name"));
        Assert.assertEquals(expectedData.get("calisansayisi"), ((List) actualData.get("data")).size());
        //sondan 3. calisanin maasinin 675000 oldugunu
        //1.yol
        Assert.assertEquals(expectedData.get("sondanucuncucalisaninmaasi"),
                ((Map) ((List) actualData.get("data"))
                        .get(((List) actualData
                                .get("data"))
                                .size() - 3))
                        .get("employee_salary"));
        //2.yol
        int dataSize = ((List)actualData.get("data")).size();
        Assert.assertEquals(expectedData.get("sondanucuncucalisaninmaasi"),
                ((Map) ((List) actualData.get("data"))
                        .get(dataSize - 3))
                        .get("employee_salary"));
        //40,21,19 yaslarinda calisanlar olup olmadigini kontrol edecegiz
        //1.yol
        List<Integer> actualYasList = new ArrayList<>();
        for (int i = 0; i < dataSize; i++) {
            actualYasList.add((Integer) ((Map)((List) actualData.get("data")).get(i)).get("employee_age"));
        }
        Assert.assertTrue(actualYasList.containsAll((Collection<?>) expectedData.get("arananyaslar")));
        Assert.assertEquals(((Map)expectedData.get("onuncu")).get("id"),((Map)((List)actualData.get("data")).get(9)).get("id"));
        Assert.assertEquals(((Map)expectedData.get("onuncu")).get("employee_name"),((Map)((List)actualData.get("data")).get(9)).get("employee_name"));
        Assert.assertEquals(((Map)expectedData.get("onuncu")).get("employee_salary"),((Map)((List)actualData.get("data")).get(9)).get("employee_salary"));
        Assert.assertEquals(((Map)expectedData.get("onuncu")).get("employee_age"),((Map)((List)actualData.get("data")).get(9)).get("employee_age"));
        Assert.assertEquals(((Map)expectedData.get("onuncu")).get("profile_image"),((Map)((List)actualData.get("data")).get(9)).get("profile_image"));



        //2.yol




    }
}
