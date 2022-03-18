package pojos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@JsonIgnoreProperties(ignoreUnknown = true)
@lombok.Data
@AllArgsConstructor
@NoArgsConstructor
public class DummyPojo {
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

    private String status;
    private Data data;
    private String message;
}
