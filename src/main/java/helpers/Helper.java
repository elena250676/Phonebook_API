package helpers;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import dto.ErrorDTO;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;

import java.util.Random;

public interface Helper {
    public static final MediaType JSON = MediaType.get("application/json; charset=utf-8");

    Gson gson = new Gson();
    OkHttpClient client = new OkHttpClient();
    String token = "eyJhbGciOiJIUzI1NiJ9.eyJyb2xlcyI6WyJST0xFX1VTRVIiXSwic3ViIjoicGF2bG92YWU0MzRAZ21haWwuY29tIiwiaXNzIjoiUmVndWxhaXQiLCJleHAiOjE2OTEzODU2NjgsImlhdCI6MTY5MDc4NTY2OH0.rZfENNuHBjoUDQsu6Ki8Jqqbn_E0QkR7GJJDYAJRN1U";
    String BASE_URI = "https://contactapp-telran-backend.herokuapp.com";
    String PATH = "v1";
    String authHeader = "Authorization";
    int i = new Random().nextInt(1000) + 1000;



}
