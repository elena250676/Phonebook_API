package restassured;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import dto.AuthRequestDTO;
import dto.AuthResponceDTO;
import dto.ErrorDTO;
import helpers.Helper;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.jayway.restassured.RestAssured.given;

public class LoginTests implements Helper {

    String endpoint = "user/login/usernamepassword";

    @BeforeMethod
    public void precondition() {
        RestAssured.baseURI=BASE_URI;
        RestAssured.basePath = PATH;
    }

    @Test
    public void loginPositive() {
        AuthRequestDTO requestDTO = AuthRequestDTO.builder()
                .username("pavlovae434@gmail.com")
                .password("Alex@2001")
                .build();

        AuthResponceDTO responseDTO = given()
                .body(requestDTO)
                .contentType(ContentType.JSON)
                .when()
                .post(endpoint)
                .then()
                .assertThat().statusCode(200)
                .extract()
                .as(AuthResponceDTO.class);

        System.out.println(responseDTO.getToken());

    }
    @Test
    public void loginNegativeWrongEmail(){
        AuthRequestDTO requestDTO = AuthRequestDTO.builder()
                .username("pavlovae434gmail.com")
                .password("Alex@2001")
                .build();

        ErrorDTO errorDTO = given()
                .body(requestDTO)
                .contentType(ContentType.JSON)
                .when()
                .post(endpoint)
                .then()
                .assertThat().statusCode(401)
                .extract()
                .as(ErrorDTO.class);

        System.out.println(errorDTO.getMessage());
    }
}