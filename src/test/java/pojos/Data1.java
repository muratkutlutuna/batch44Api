package pojos;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
@JsonIgnoreProperties(ignoreUnknown = true)// bu kismi degerini yamadigimiz urunler icin yazdik
@Data
@AllArgsConstructor
public class Data1 {
    /*
  "data": {
"id": 1,
"employee_name": "Tiger Nixon",
"employee_salary": 320800,
"employee_age": 61,
"profile_image": ""
}
  */
    private int id;
    private String employee_name;
    private int employee_salary;
    private int employee_age;
    private String profile_image;
}
