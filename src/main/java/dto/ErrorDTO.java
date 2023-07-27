package dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@Builder

public class ErrorDTO {

//     "timestamp": "2023-07-26T08:20:25.846Z",
//             "status": 0,
//             "error": "string",
//             "message": {},
//            "path": "string"

int status;
String error;
String message;

}
