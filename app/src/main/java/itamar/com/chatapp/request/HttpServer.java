package itamar.com.chatapp.request;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;

import itamar.com.chatapp.data.Storage;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class HttpServer {

    //public static final String BASE_URL = "http://localhost/";
    public static final String BASE_URL = "https://heroapps.co.il/";
    private static HttpRequest httpRequest;
    public static final String NO_NEED_AUTH = "NO_NEED_AUTH";

    private class MyInterceptor implements Interceptor {

        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();

            if (request.header(NO_NEED_AUTH) != null) {
                request = request.newBuilder()
                        .removeHeader(NO_NEED_AUTH)
                        .build();
            } else {
                String token = Storage.getInstance().getToken();
                request = request.newBuilder()
                        .header("Authorization", token)
                        .build();
            }
            return chain.proceed(request);
        }

    }


    private static Retrofit createRetrofit() {

        HttpLoggingInterceptor loggingInterceptor =
                new HttpLoggingInterceptor()
                        .setLevel(HttpLoggingInterceptor.Level.BODY);


        OkHttpClient httpClient = new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor).build();

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(httpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();

        return retrofit;
    }

    public static HttpRequest getInstance() {
        if (httpRequest == null) {
            httpRequest = createRetrofit().create(HttpRequest.class);
        }
        return httpRequest;
    }


}
