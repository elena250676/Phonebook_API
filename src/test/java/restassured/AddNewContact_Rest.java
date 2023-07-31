package restassured;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.matcher.ResponseAwareMatcher;
import com.jayway.restassured.response.Response;
import dto.AuthResponceDTO;
import dto.ContactDTO;
import dto.ContactResponceDTo;
import helpers.Helper;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;

import static com.google.common.base.Predicates.equalTo;
import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;

public class AddNewContact_Rest implements Helper {
    String endpoint = "contacts";

    @BeforeMethod
    public void precondition() {
        RestAssured.baseURI = BASE_URI;
        RestAssured.basePath = PATH;
    }

    @Test
    public void AddNewContactRestPositive() throws IOException {

        ContactDTO contactDTO = ContactDTO.builder()
                //.id("123")
                .name("Leggggnffffffffffa")
                .lastName("Pavlotydva")
                .email("pavlova44444@gmail.com")
                .phone("25436780798")
                .address("Pinfffsker 50/60")
                .description("hh")
                .build();

         given()
                .contentType(ContentType.JSON)
                .header(authHeader, token)
                .body(contactDTO)
                .when()
                .post(BASE_URI + "/" + PATH + "/" + endpoint)
                .then()
                .assertThat().statusCode(200)
                .extract().response();
        System.out.println("Contact is added!");

    }
}
