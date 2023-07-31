package okhttp;

import com.google.gson.Gson;
import dto.*;
import helpers.Helper;
import okhttp3.*;
import org.slf4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;

public class AddNewContact implements Helper {
    //public static final MediaType JSON = MediaType.get("application/json; charset=utf-8");
    //Gson gson = new Gson();
    //OkHttpClient client = new OkHttpClient();
    String endpoint = "contacts";
    @Test
    public void AddNewContactPositive() throws IOException {

        ContactDTO contactDTO = ContactDTO.builder()
                //.id("123")
                .name("Leggggna")
                .lastName("Pavlotydva")
                .email("pavlova44444@gmail.com")
                .phone("25436780798")
                .address("Pinfffsker 50/60")
                .description("hh")
                .build();

        RequestBody requestBody = RequestBody.create(gson.toJson(contactDTO), JSON);
        Request request = new Request.Builder()
                .url(BASE_URI + "/" + PATH + "/" + endpoint)
                .addHeader(authHeader, token)
                .post(requestBody)
                .build();
        Response response = client.newCall(request).execute();
        ContactResponceDTo contactResponseDTO = gson.fromJson(response.body().string(),ContactResponceDTo.class);

        Assert.assertTrue(response.isSuccessful());
        String message = contactResponseDTO.getMessage();
        System.out.println(message);
        String id = message.substring(message.lastIndexOf(" ") + 1);
        System.out.println(id);




    }
}