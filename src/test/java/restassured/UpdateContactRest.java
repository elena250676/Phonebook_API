package restassured;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;
import dto.ContactDTO;
import dto.ContactResponceDTo;
import helpers.Helper;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;

public class UpdateContactRest implements Helper {

    String endpoint = "contacts";
    String id;

    @BeforeMethod

    public void precondition() {
        RestAssured.baseURI=BASE_URI;
        RestAssured.basePath = PATH;


            ContactDTO contactDTO = ContactDTO.builder()
                    .name("QA38")
                    .lastName("Automation")
                    .email("pavlovaggg@mail.ru")
                    .phone("12345789678")
                    .address("Rehovot")
                    .description("Students")
                    .build();

            Response response = given()
                    .contentType(ContentType.JSON)
                    .body(contactDTO)
                    .post(BASE_URI + "/" + PATH + "/" + endpoint);

            ContactResponceDTo contactResponseDTO = response.as(ContactResponceDTo.class);
            //id = contactResponseDTO.toString().getId();

            response.then()
                    .assertThat()
                    .statusCode(200)
                    .body("message", containsString("Contact was added!"));
       // System.out.println(message);
        }

        @Test
        public void UpdateContactPositive() {
            ContactDTO updatedContact = ContactDTO.builder()
                    .name("fgfgfgfg")
                    .build();

            Response response = given()
                    .contentType(ContentType.JSON)
                    .body(updatedContact)
                    .put(BASE_URI + "/" + PATH + "/" + endpoint + "/" + id);

            response.then()
                    .assertThat()
                    .statusCode(200);

            System.out.println("Contact ID: " + id);
            //System.out.println("Response code: " + response.code());
        }
    }


