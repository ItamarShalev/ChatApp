package itamar.com.chatapp.main;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.ViewOutlineProvider;

import java.util.List;

import itamar.com.chatapp.data.User;


public interface MainMVP {

    interface View{
        void initViews();

        String getTokenUser();
        void moveToRegisterActivity();

        User getCurrentUser();
        void moveToEnter(@NonNull Bundle bundle);
        boolean iAlreadyRegister();
        List<User> getAllUsers();
        void initUsersList(List<User> users);


    }

    interface Presenter{
        void init();

    }
}
