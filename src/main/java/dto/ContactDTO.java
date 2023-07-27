package dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Setter
@Getter
@ToString
@Builder
public class ContactDTO {


//    "id": "string",
//            "name": "string",
//            "lastName": "string",
//            "email": "string",
//            "phone": "49937885399770",
//            "address": "string",
//            "description": "string"

    String id;
    String name;
    String lastName;
    String email;
    String phone;
    String address;
    String description;
}
