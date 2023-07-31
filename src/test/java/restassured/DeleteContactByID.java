package restassured;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;
import dto.ContactDTO;
import dto.ContactResponceDTo;
import helpers.Helper;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;

public class DeleteContactByID implements Helper {

    String endpoint = "contacts";
    String id;

    @BeforeMethod
    public void precondition() {
        RestAssured.baseURI = BASE_URI;
        RestAssured.basePath = PATH;

        ContactDTO contactDTO = ContactDTO.builder()
                //.id("123")
                .name("Leggggnffffffffffa")
                .lastName("Pavlotydva")
                .email("pavlova44444@gmail.com")
                .phone("25436780798")
                .address("Pinfffsker 50/60")
                .description("hh")
                .build();

        Response response = given()
                .contentType(ContentType.JSON)
                .header(authHeader, token)
                .body(contactDTO)
                .post(BASE_URI + "/" + PATH + "/" + endpoint);
        ContactResponceDTo contactResponceDTo = response.as(ContactResponceDTo.class);
        response.then()
                .assertThat()
                .statusCode(200)
                .body("message", containsString("Contact was added!"));

        String message = contactResponceDTo.getMessage();
        System.out.println(message);
        id = message.substring(message.lastIndexOf(" ") + 1);

    }

    @Test
    public void DeleteContactByIDRestPositive() throws IOException {

        given()
                .contentType(ContentType.JSON)
                .header(authHeader, token)
                .delete(BASE_URI + "/" + PATH + "/" + endpoint + "/" + id)
                .then()
                .assertThat()
                .statusCode(200)
                .body("message", containsString("Contact was deleted!"));
        System.out.println("Contact with id:     " + id +"was deleted!");


    }
}
