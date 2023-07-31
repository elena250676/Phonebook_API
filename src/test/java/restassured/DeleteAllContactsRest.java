package restassured;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import helpers.Helper;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;

public class DeleteAllContactsRest implements Helper {

    String endpoint = "contacts/clear";

    @BeforeMethod
    public void precondition() {
        RestAssured.baseURI = BASE_URI;
        RestAssured.basePath = PATH;
    }

    @Test
    public void deleteAllContactsPositive() throws IOException {

        given()
                .contentType(ContentType.JSON)
                .header(authHeader, token)
                .delete(BASE_URI + "/" + PATH + "/" + endpoint)
                .then()
                .assertThat()
                .statusCode(200);
                //.body("message", containsString("Contact was deleted!"));
        System.out.println("Contacts were deleted!");


    }

}

