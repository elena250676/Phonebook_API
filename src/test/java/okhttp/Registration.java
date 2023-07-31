package okhttp;

import com.google.gson.Gson;
import dto.AuthRequestDTO;
import dto.AuthResponceDTO;
import dto.ErrorDTO;
import helpers.Helper;
import okhttp3.*;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class Registration implements Helper {


    String endpoint = "user/registration/usernamepassword";

    @Test
    public void regPositive() throws IOException {
        AuthRequestDTO requestDTO = AuthRequestDTO.builder()
                .username("pavlovae434" + i + "@gmail.com")
                .password("Alex@2001")
                .build();

        RequestBody requestBody = RequestBody.create(gson.toJson(requestDTO), JSON);

        Request request = new Request.Builder()
                .url(BASE_URI + "/" + PATH + "/" + endpoint)
                .post(requestBody)
                .build();

        Response response = client.newCall(request).execute();


        System.out.println("Responce code" + response.code());
        Assert.assertTrue(response.isSuccessful());

    }

    @Test
    public void registrationNegativeWrongEmail() throws IOException {

        AuthRequestDTO requestDTO = AuthRequestDTO.builder()
                .username("pavlovae434" + i + "gmail.com")
                .password("Alex@2001")
                .build();

        RequestBody requestBody = RequestBody.create(gson.toJson(requestDTO), JSON);

        Request request = new Request.Builder()
                .url(BASE_URI + "/" + PATH + "/" + endpoint)
                .post(requestBody)
                .build();
        Response response = client.newCall(request).execute();
        System.out.println("Response code is: " + response.code());
        ErrorDTO errorDTO = gson.fromJson(response.body().string(), ErrorDTO.class);
        System.out.println(errorDTO.getStatus() + " " + errorDTO.getMessage() + " " + errorDTO.getError());
        Assert.assertTrue(!response.isSuccessful());


    }
}
//eyJhbGciOiJIUzI1NiJ9.eyJyb2xlcyI6WyJST0xFX1VTRVIiXSwic3ViIjoicGF2bG92YWU0MzQxNTQzQGdtYWlsLmNvbSIsImlzcyI6IlJlZ3VsYWl0IiwiZXhwIjoxNjkwOTc3OTQ0LCJpYXQiOjE2OTAzNzc5NDR9.cVAeyAb0NE_DUnFv6qrw0QrgtKPxf3ntN7ftpa5C_1I