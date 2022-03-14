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
    //{
    //"userId": 21,
    //"title": "Wash the dishes",
    //"completed": false
    //}
    public JSONObject requestedAndExpectedData(){
        JSONObject data = new JSONObject();
        data.put("userId", 55);
        data.put("title", "Tidy your room");
        data.put("completed", false);
        data.put("statusCode", 201);
        return data;
    }
//Dönen response un status kodunun 200 ve body kısmının aşağıdaki gibi olduğunu test edin
//{
//"userId": 21,
//"title": "Wash the dishes",
//"completed": false,
//"id": 198
//}
    //PutRequest01
    public JSONObject setUpPutData(){
        JSONObject expectedRequest = new JSONObject();
        expectedRequest.put("userId", 21);
        expectedRequest.put("title", "Wash the dishes");
        expectedRequest.put("completed", false);
        return expectedRequest;
    }
    // https://jsonplaceholder.typicode.com/todos/198 URL ine aşağıdaki body gönderdiğimde
    //     {
    //        "title": "Batch44"
    //       }
    //PatchRequest01
    public JSONObject setUpPatchRequestData(){
        JSONObject expectedRequest = new JSONObject();
        expectedRequest.put("title","Batch44");
        return expectedRequest;
    }
//   {
//   "userId": 10,
//   "title": "Batch44"
//   "completed": true,
//   "id": 198
//   }
    //PatchRequest01
    public JSONObject patchExpectedData(){
        JSONObject expectedData = new JSONObject();
        expectedData.put("userId", 10);
        expectedData.put("title", "Batch44");
        expectedData.put("completed", true);
        expectedData.put("id", 198);
        return expectedData;
    }
}

