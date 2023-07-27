package okhttp;

import com.google.gson.Gson;
import dto.AuthRequestDTO;
import dto.AuthResponceDTO;
import dto.ErrorDTO;
import okhttp3.*;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class Registration {

    public static final MediaType JSON = MediaType.get("application/json; charset=utf-8");

    Gson gson = new Gson();
    OkHttpClient client = new OkHttpClient();

    @Test
    public void RegistrationPositive() throws IOException {
        int i = (int) (System.currentTimeMillis() / 1000) % 3600;
        AuthRequestDTO requestDTO = AuthRequestDTO.builder()
                .username("pavlovae434"+i+"@gmail.com")
                .password("Alex@2001")
                .build();

        RequestBody requestBody = RequestBody.create(gson.toJson(requestDTO), JSON);
        Request request = new Request.Builder()
                .url("https://contactapp-telran-backend.herokuapp.com/v1/user/registration/usernamepassword")
                .post(requestBody)
                .build();
        Response response = client.newCall(request).execute();
        if (response.isSuccessful()) {
            AuthResponceDTO responceDTO = gson.fromJson(response.body().string(), AuthResponceDTO.class);
            System.out.println(responceDTO.getToken());
            System.out.println("Responce code"+response.code());
            Assert.assertTrue(response.isSuccessful());
        }else{
            System.out.println("Responce code"+response.code());
            ErrorDTO errorDTO = gson.fromJson(response.body().string(), ErrorDTO.class);
            System.out.println(errorDTO.getStatus()+"  "+errorDTO.getMessage()+"  "+errorDTO.getError());
            Assert.assertTrue(response.isSuccessful());
        }
    }
}
//eyJhbGciOiJIUzI1NiJ9.eyJyb2xlcyI6WyJST0xFX1VTRVIiXSwic3ViIjoicGF2bG92YWU0MzQxNTQzQGdtYWlsLmNvbSIsImlzcyI6IlJlZ3VsYWl0IiwiZXhwIjoxNjkwOTc3OTQ0LCJpYXQiOjE2OTAzNzc5NDR9.cVAeyAb0NE_DUnFv6qrw0QrgtKPxf3ntN7ftpa5C_1I