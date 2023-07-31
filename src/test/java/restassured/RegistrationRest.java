package restassured;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;
import dto.AuthRequestDTO;
import dto.AuthResponceDTO;
import helpers.Helper;
import org.testng.Assert;
import org.testng.annotations.Test;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.Matchers.isEmptyOrNullString;

public class RegistrationRest implements Helper {

    String endpoint = "user/registration/usernamepassword";

    public void precondition() {
        RestAssured.baseURI=BASE_URI;
        RestAssured.basePath = PATH;
    }

    @Test
    public void RegistrationPositive() {
        AuthRequestDTO requestDTO = AuthRequestDTO.builder()
                .username("pavlovae434" + i + "@gmail.com")
                .password("Alex@2001"+i)
                .build();


        Response response = given()
                .contentType(ContentType.JSON)
                .body(requestDTO)
                .post(BASE_URI + "/" + PATH + "/" + endpoint);

        response.then()
                .assertThat()
                .statusCode(200);


    }
}
