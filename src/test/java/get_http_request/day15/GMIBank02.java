package get_http_request.day15;

import base_url.GMIBankBaseUrl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import utilities.ReadText;
import utilities.WriteToText;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

public class GMIBank02 extends GMIBankBaseUrl {
   /*
http://www.gmibank.com/api/tp-customers end point'ine
request gönderin
 1) Tüm Customer bilgilerini ekrana yazdırırn.

 2) Tüm Customer emaillerini ekrana yazdırın.

 3) Tüm Customer emaillerini text dosyası olarak kaydedin

 4) dönen reponse'ta winonaabernathy@gmail.com, MerrillPrice@gmail.com, LesleyKing@gmail.com
    E-maillerinin olduğunu doğrulayın
 */
    @Test
    public void test02() throws JsonProcessingException {
        spec03.pathParams("1","tp-customers");
        Response response = given()
                .spec(spec03)
                .header("Authorization","Bearer "+generateToken())
                .when()
                .get("/{1}")
                .then()
                .contentType(ContentType.JSON)
                .extract()
                .response();
        //response.prettyPrint();
        // 1) Tüm Customer bilgilerini ekrana yazdırırn.
        ObjectMapper obj = new ObjectMapper();
        Customer[] customers = obj.readValue(response.asString(), Customer[].class);
        for (int i = 0; i < customers.length; i++)
            System.out.println((1+i)+".customer = " + customers[i]);
        // 2) Tüm Customer emaillerini ekrana yazdırın.
        for (int i = 0; i < customers.length; i++)
            System.out.println((1+i)+".customer = " + customers[i].getEmail());
        // 3) Tüm Customer emaillerini text dosyası olarak kaydedin
        String path = "src/test/java/get_http_request/day15/GMIBankTestData/EmailList.txt";
        WriteToText.saveEmailData(path,customers);
        // 4) dönen reponse'ta winonaabernathy@gmail.com, MerrillPrice@gmail.com, LesleyKing@gmail.com
        //    E-maillerinin olduğunu doğrulayın
        List<String> expected = new ArrayList<String>();
        expected.add("winonaabernathy@gmail.com");
        expected.add("MerrillPrice@gmail.com");
        expected.add("LesleyKing@gmail.com");
        List<String> actual = ReadText.readCustomerEmailList(path);
        Assert.assertTrue("EMAIL'S ARE NOT MATCHED", actual.containsAll(expected));


    }

}
