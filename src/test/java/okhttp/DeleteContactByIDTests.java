package okhttp;

import dto.ContactDTO;
import dto.ContactResponceDTo;
import helpers.Helper;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;

public class DeleteContactByIDTests implements Helper {
    String endpoint = "contacts";
    String id;

    @BeforeMethod
    public void precondition() throws IOException {

        ContactDTO contactDTO = ContactDTO.builder()
                .name("QA38")
                .lastName("Automation")
                .email("qa38_" + i + "@mail.com")
                .phone("12345678" + i)
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
    public void deleteContactByIDPositive() throws IOException {
        Request request = new Request.Builder()
                .url(BASE_URI + "/" + PATH + "/" + endpoint + "/" + id)
                .addHeader(authHeader, token)
                .delete()
                .build();

        Response response = client.newCall(request).execute();

        ContactResponceDTo contactResponseDTO = gson.fromJson(response.body().string(), ContactResponceDTo.class);

        Assert.assertTrue(response.isSuccessful());
        String message = contactResponseDTO.getMessage();
        System.out.println(message);
    }
}