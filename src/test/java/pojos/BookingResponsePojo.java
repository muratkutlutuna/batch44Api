package pojos;

import lombok.*;
import lombok.Data;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class BookingResponsePojo {
    /*
      {
        "bookingid": 11,
                "booking": {
                            "firstname": "Ali",
                            "lastname": "Can",
                            "totalprice": 500,
                            "depositpaid": true,
                            "bookingdates": {
                                            "checkin": "2022-03-01",
                                            "checkout": "2022-03-11"
                                            }
                          }
      }
     */

    //1) private değişkenleri oluştur.
    private int bookingId;
    private BookingPojo bookingPojo;


}
