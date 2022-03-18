package get_http_request.day15;

import base_url.GMIBankBaseUrl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import utilities.WriteToText;

import static io.restassured.RestAssured.given;

public class GMIBank03 extends GMIBankBaseUrl {
    /*
    http://www.gmibank.com/api/tp-customers end point'ine
    requerini text est gönderin
    First Name,
    Last Name,
    email,
    mobilePhone,
    Adres
    city
    Bilgildosyasına yazdırın
 */
     @Test
    public void test03() throws JsonProcessingException {
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
         response.prettyPrint();
         ObjectMapper obj = new ObjectMapper();
         Customer[] customers = obj.readValue(response.asString(), Customer[].class);
         String path = "src/test/java/get_http_request/day15/GMIBankTestData/customers.txt";
         WriteToText.saveCustomersData(path, customers);





     }

}
