package test_data;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class JsonPlaceHolderTestData {
    public Map<String, Object> setUpTestData() {
        HashMap<String, Object> expectedData = new HashMap<String, Object>();
        expectedData.put("statusCode", 200);
        expectedData.put("completed", false);
        expectedData.put("title", "quis ut nam facilis et officia qui");
        expectedData.put("userId", 1);
        expectedData.put("via", "1.1 vegur");
        expectedData.put("server", "cloudflare");
        return expectedData;
    }
    public JSONObject requestedAndExpectedData(){
        JSONObject data = new JSONObject();
        data.put("userId", 55);
        data.put("title", "Tidy your room");
        data.put("completed", false);
        data.put("statusCode", 201);
        return data;
    }

}

