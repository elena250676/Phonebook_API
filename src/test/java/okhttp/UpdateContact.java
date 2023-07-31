package okhttp;

import com.google.gson.JsonSyntaxException;
import dto.ContactDTO;
import dto.ContactResponceDTo;
import dto.ErrorDTO;
import helpers.Helper;
import okhttp3.FormBody;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;

public class UpdateContact implements Helper {
    String endpoint = "contacts";
    String id;

    @BeforeMethod
    public void precondition() throws IOException {

        ContactDTO contactDTO = ContactDTO.builder()
                .name("QA38")
                .lastName("Automation")
                .email("pavlovaggg@mail.ru")
                .phone("12345789678")
                .address("Rehovot")
                .description("Students")
                .build();

        RequestBody requestBody = RequestBody.create(gson.toJson(contactDTO), JSON);

        Request request = new Request.Builder()
                .url(BASE_URI + "/" + PATH + "/" + endpoint)
                .addHeader(authHeader, token)
                .post(requestBody)
                .build();

        Response response = client.newCall(request).execute();

        ContactResponceDTo contactResponseDTO = gson.fromJson(response.body().string(), ContactResponceDTo.class);

        Assert.assertTrue(response.isSuccessful());
        String message = contactResponseDTO.getMessage();
        System.out.println(message);
        id = message.substring(message.lastIndexOf(" ") + 1);
    }

    @Test
    public void UpdateContactPositive() throws IOException {
        ContactDTO updatedContact = ContactDTO.builder()
                .name("fgfgfgfg")
                .build();

        RequestBody requestBody = RequestBody.create(gson.toJson(updatedContact), JSON);
        Request request = new Request.Builder()
                .url(BASE_URI + "/" + PATH + "/" + endpoint + "/" +id)
                .addHeader(authHeader, token)
                .put(requestBody)
                .build();

        Response response = client.newCall(request).execute();
        ContactResponceDTo contactResponseDTO = gson.fromJson(response.body().string(), ContactResponceDTo.class);

       // Assert.assertTrue(response.isSuccessful());
       // String message = contactResponseDTO.getMessage();
       // System.out.println(message);
        //String id = message.substring(message.lastIndexOf(" ") + 1);
        System.out.println(id);
        System.out.println(response.code());
    }
}
