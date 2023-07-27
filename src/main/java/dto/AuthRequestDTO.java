package dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@Builder

public class AuthRequestDTO {
   // "username": "string",
   //         "password": "/~!Q6.&LpDvLs6}qDKy*7{t\\dVZ,iirc`(}bV*V3W%B,Z?*Q& ObQ:09#!i:6*gpnG(;x-"
String username;
String password;

}
