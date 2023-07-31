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

public class DeleteAllContacts implements Helper {
    String endpoint = "contacts/clear";


    @Test
    public void deleteAllContactsPositive() throws IOException {
        Request request = new Request.Builder()
                .url(BASE_URI + "/" + PATH + "/" + endpoint )
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
