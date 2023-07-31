package okhttp;

import com.google.gson.Gson;
import dto.AuthRequestDTO;
import dto.AuthResponceDTO;
import dto.ErrorDTO;
import okhttp3.*;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class LoginTests {

    public static final MediaType JSON = MediaType.get("application/json; charset=utf-8");

    Gson gson = new Gson();
    OkHttpClient client = new OkHttpClient();

    @Test
    public void loginPositive() throws IOException {

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


 //


