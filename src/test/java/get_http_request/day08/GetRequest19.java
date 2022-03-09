package get_http_request.day08;

import base_url.DummyBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

public class GetRequest19 extends DummyBaseUrl {
    /*
http://dummy.restapiexample.com/api/v1/employees
*/
    @Test
    public void test19() {
        spec02.pathParams("first", "api", "second", "v1", "third", "employees");
        Response response = given()
                .spec(spec02)
                .when()
                .get("/{first}/{second}/{third}");
//        response.prettyPrint();
//        1) Status kodunun 200,
        response.then().assertThat().statusCode(200);
        //2) 10’dan büyük tüm id'leri ekrana yazdırın ve 10’dan büyük 14 id olduğunu,
        JsonPath json = response.jsonPath();
        List<Integer> idList = json.getList("data.id.findAll{it>10}");//data.findAll{it.id>10}.id
        System.out.print("idList = " + idList);
        System.out.println();
//        Groovy java platformu uzerinde calisan bir bilgisayar dilidir.
//        Groovy ile loop kullanmadan response'dan gelen degerleri bir sarta gore alabiliriz.
        //3) 30’dan küçük tüm yaşları ekrana yazdırın ve bu yaşların içerisinde en büyük yaşın 23 olduğunu
        List<Integer> ages = json.getList("data.employee_age.findAll{it<30}");
        System.out.println("ages = " + ages);
        int greatestAge = ages.stream().reduce(0, Math::max);
        System.out.println("greatestAge = " + greatestAge);
        //4) Maası 350000 den büyük olan tüm employee name'leri ekrana yazdırın
        List<String> topSalaryName = json.getList("data.findAll{it.employee_salary>350000}.employee_name");
        System.out.println("topSalaryName = " + topSalaryName);
//     ve bunların içerisinde "Charde Marshall" olduğunu test edin
        Assert.assertTrue(topSalaryName.contains("Charde Marshall"));





    }
}
