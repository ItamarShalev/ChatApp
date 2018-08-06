package itamar.com.chatapp.request;

import android.app.Application;
import android.content.pm.ApplicationInfo;

import java.io.IOException;

import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Itamar on 02/08/2018.
 */

public abstract class  CallListener<T> implements Callback<T> {


    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        if (response.isSuccessful()) {
            onResponse(response);
        }else{
            onFailure(call,new RuntimeException("Code less or height from 200 - 300"));
        }
    }
    public abstract void onResponse(Response<T> response);

    @Override
    public abstract void onFailure(Call<T> call, Throwable t);
}
