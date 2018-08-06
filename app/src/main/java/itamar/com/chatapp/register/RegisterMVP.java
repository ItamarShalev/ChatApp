package itamar.com.chatapp.register;

import android.support.annotation.StringRes;


public interface RegisterMVP {

    interface View{
        void initViews();

        String getPhoneNumber();

        void noInternetAvailable();

        void showError(@StringRes int error);

        void listenToMessages();

        String getString(int resString);

        void sendSms(String phoneNumber, String message);

        void saveUserToken(String token);

        void moveToMainActivity();
    }

    interface Presenter{
        void init();

        void buttonClicked();

        boolean messageReceived(String phoneNumber,String message);

        void readyToListen();
    }
}
