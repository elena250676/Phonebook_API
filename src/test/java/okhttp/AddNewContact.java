package okhttp;

import com.google.gson.Gson;
import dto.*;
import okhttp3.*;
import org.slf4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;

public class AddNewContact {
    public static final MediaType JSON = MediaType.get("application/json; charset=utf-8");
    Gson gson = new Gson();
    OkHttpClient client = new OkHttpClient();

    @Test
    public void AddNewContactPositive() throws IOException {

        AuthRequestDTO requestDTO = AuthRequestDTO.builder()
                .username("pavlovae434@gmail.com")
                .password("Alex@2001")
                .build();

        RequestBody requestBody = RequestBody.create(gson.toJson(requestDTO), JSON);
        Request request = new Request.Builder()
                .url("https://contactapp-telran-backend.herokuapp.com/v1/user/login/usernamepassword")
                .post(requestBody)
                .build();
        Response response = client.newCall(request).execute();
        String token = null;
        if (response.isSuccessful()) {
            AuthResponceDTO responceDTO = gson.fromJson(response.body().string(), AuthResponceDTO.class);
            System.out.println(responceDTO.getToken());
            token = responceDTO.getToken();
            System.out.println("Responce code" + response.code());
            Assert.assertTrue(response.isSuccessful());
        } else {
            System.out.println("Responce code" + response.code());
            ErrorDTO errorDTO = gson.fromJson(response.body().string(), ErrorDTO.class);
            System.out.println(errorDTO.getStatus() + "  " + errorDTO.getMessage() + "  " + errorDTO.getError());
            Assert.assertTrue(response.isSuccessful());
        }

        ContactDTO addrequestDTO = ContactDTO.builder()
                .id("123")
                .name("Lena")
                .lastName("Pavlotydva")
                .email("pavlova44444@gmail.com")
                .phone("25436780798")
                .address("Pinsker 50")
                .description("hh")
                .build();

        RequestBody addrequestBody = RequestBody.create(gson.toJson(addrequestDTO), JSON);
        Request addrequest = new Request.Builder()
                .url("https://contactapp-telran-backend.herokuapp.com/v1/contacts")
                .addHeader("Authorization", token)
                .post(addrequestBody)
                .build();
        Response addresponse = client.newCall(addrequest).execute();

        if (addresponse.isSuccessful()) {
            ContactDTO addresponceDTO = gson.fromJson(addresponse.body().string(), ContactDTO.class);

            System.out.println("Responce code" + response.code());
            Assert.assertTrue(addresponse.isSuccessful());
        } else {
            System.out.println("Responce code" + addresponse.code());
            ErrorDTO adderrorDTO = gson.fromJson(addresponse.body().string(), ErrorDTO.class);
            System.out.println(adderrorDTO.getStatus() + "  " + adderrorDTO.getMessage() + "  " + adderrorDTO.getError());
            Assert.assertTrue(addresponse.isSuccessful());
        }
    }
}
