package get_http_request.day15;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;


@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer {
    /*
    {
    “id”: 110452,
    “firstName”: “Jasmine”,
    “lastName”: “Stehr”,
    “middleInitial”: “V”,
    “email”: “marni.zboncak@yahoo.com”,
    “mobilePhoneNumber”: “463-609-2097”,
    “phoneNumber”: “1-112-497-0270”,
    “zipCode”: “16525”,
    “address”: “14387 Al Ridge5343 Bert Burgs”,
    “city”: “Waltermouth”,
    “ssn”: “761-59-2911”,
    “createDate”: “2021-11-28T21:00:00Z”,
    “zelleEnrolled”: false,
    “country”: {
        “id”: 3,
        “name”: “USA”,
        “states”: null
    },
    “state”: “California”,
    “user”: {
        “id”: 110016,
        “login”: “leopoldo.reinger”,
        “firstName”: “Jasmine”,
        “lastName”: “Stehr”,
        “email”: “marni.zboncak@yahoo.com”,
        “activated”: true,
        “langKey”: “en”,
        “imageUrl”: null,
        “resetDate”: null
    },
    “accounts”: []
}
     */
    private int id;
    private String firstName;
    private String lastName;
    private String middleInitial;
    private String email;
    private String mobilePhoneNumber;
    private String phoneNumber;
    private String zipCode;
    private String address;
    private String city;
    private String ssn;
    private String createdDate;
    private boolean zelleEnrolled;
    private Country country;
    private String state;
    private User user;
    private String accounts;
}
