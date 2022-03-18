package get_http_request.day15;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;


@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Country {
    /*
    "country": {
        "id": 3,
        "name": "USA",
        "states": null
    }
     */
    private int id;
    private String name;
    private String states;
}
