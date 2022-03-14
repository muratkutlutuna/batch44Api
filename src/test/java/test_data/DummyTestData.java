package test_data;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DummyTestData {
    /*
http://dummy.restapiexample.com/api/v1/employees url ine bir istek gönderildiğinde
Status kodun 200 olduğunu,
14. Çalışan isminin "Haley Kennedy" olduğunu,
Çalışan sayısının 24 olduğunu,
Sondan 3. çalışanın maaşının 675000 olduğunu
40,21 ve 19 yaslarında çalışanlar olup olmadığını
10. Çalışan bilgilerinin bilgilerinin aşağıdaki gibi

{
    "id": 10,
    "employee_name": "Sonya Frost",
    "employee_salary": 103600,
    "employee_age": 23,
    "profile_image": ""
}

olduğunu test edin.
*/
    public HashMap<String, Object> setUpTestData() {
        List<Integer> yaslar = new ArrayList<>();
        yaslar.add(40);
        yaslar.add(21);
        yaslar.add(19);
        HashMap<String, Object> onuncu = new HashMap<>();
        onuncu.put("id", 10);
        onuncu.put("employee_name", "Sonya Frost");
        onuncu.put("employee_salary", 103600);
        onuncu.put("employee_age", 23);
        onuncu.put("profile_image", "");
        HashMap<String, Object> expectedData = new HashMap<>();
        expectedData.put("ondorduncucalisan", "Haley Kennedy");
        expectedData.put("statusCode", 200);
        expectedData.put("calisansayisi", 24);
        expectedData.put("sondanucuncucalisaninmaasi", 675000);
        expectedData.put("arananyaslar", yaslar);
        expectedData.put("onuncu", onuncu);
        return expectedData;
    }

    public JSONObject setUpRequestBody() {
        JSONObject requestBody = new JSONObject();
        requestBody.put("name", "Ali Can");
        requestBody.put("salary", "2000");
        requestBody.put("age", "40");
        return requestBody;
    }
    public JSONObject postTestData(){
        JSONObject data = new JSONObject();
        data.put("name","Ali Can");
        data.put("salary","2000");
        data.put("age","40");
        data.put("id",0);
        JSONObject expectedData = new JSONObject();
        expectedData.put("status", "success");
        expectedData.put("data", data);
        expectedData.put("message", "Successfully! Record has been added.");
        return expectedData;
    }

    //"status": "success",
    //"data": "2",
    //"message": "Successfully! Record has been deleted"
    //DeleteRequest01
    public JSONObject setUpDeleteExpectedData() {
        JSONObject expectedData = new JSONObject();
        expectedData.put("status", "success");
        expectedData.put("data", "2");
        expectedData.put("message", "Successfully! Record has been deleted");
        return expectedData;
    }
}
