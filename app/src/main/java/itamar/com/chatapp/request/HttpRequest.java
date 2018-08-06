package itamar.com.chatapp.request;

import android.support.annotation.MainThread;
import android.support.transition.Visibility;

import java.util.List;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;


public interface HttpRequest {

    @POST("registerService/register")
    @Headers(HttpServer.NO_NEED_AUTH)
    Call<String> registerPhoneNumber(@Body String phoneNumber);
}