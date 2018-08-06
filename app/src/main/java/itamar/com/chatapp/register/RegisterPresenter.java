package itamar.com.chatapp.register;

import android.content.Context;
import android.widget.Toast;

import itamar.com.chatapp.R;
import itamar.com.chatapp.request.CallListener;
import itamar.com.chatapp.request.HttpRequest;
import itamar.com.chatapp.request.HttpServer;
import itamar.com.chatapp.utils.Global;
import retrofit2.Call;
import retrofit2.Response;

public class RegisterPresenter implements RegisterMVP.Presenter {

    public static final int BASIC_LENGTH = 8;
    private RegisterMVP.View view;
    private HttpRequest httpRequest;
    private String keyString;
    private String phoneNumber;

    public RegisterPresenter(RegisterMVP.View view) {
        this.view = view;
        httpRequest = HttpServer.getInstance();
    }


    @Override
    public void init() {
        view.initViews();


    }

    @Override
    public void buttonClicked() {
        phoneNumber = view.getPhoneNumber();
        if (phoneNumber.startsWith("0")) {
            phoneNumber = phoneNumber.replace("0", "+972");
        }
        if (!checkPhoneNumberValid(phoneNumber)) {
            view.showError(R.string.error_valid_phone_number);
        } else {
            view.listenToMessages();
        }
    }

    @Override
    public boolean messageReceived(String phoneNumber, String message) {
        if (!phoneNumber.contains(this.phoneNumber.substring(7))) {
            return false;
        } else {
            if (message.contains(keyString)) {
                Call<String> call = httpRequest.registerPhoneNumber(phoneNumber);
                call.enqueue(new CallListener<String>() {
                    @Override
                    public void onResponse(Response<String> response) {
                        view.saveUserToken(response.body());
                        view.moveToMainActivity();
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        view.noInternetAvailable();
                    }
                });
                Toast.makeText(((Context) view), "Have Context", Toast.LENGTH_SHORT).show();
                return true;
            }
            return false;

        }
    }

    @Override
    public void readyToListen() {
        String messageCode = view.getString(R.string.welcome_code);
        keyString = Global.getRandomString(BASIC_LENGTH);
        view.sendSms(phoneNumber, messageCode + keyString);
    }

    private boolean checkPhoneNumberValid(String phoneNumber) {
        if (phoneNumber.length() != 13) {
            return false;
        }
        char[] chars = phoneNumber.toCharArray();
        for (int i = 1; i < chars.length; i++) {
            if (!Character.isDigit(chars[i])) {
                return false;
            }
        }
        return true;
    }


}
