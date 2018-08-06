package itamar.com.chatapp.main;

import android.os.Handler;
import android.text.TextUtils;

import java.util.List;

import itamar.com.chatapp.data.User;


public class MainPresenter implements MainMVP.Presenter {
    private MainMVP.View view;
    private User currentUser;
    private List<User> users;
    private String tokenUser;
    private Handler handler;

    public MainPresenter(MainMVP.View view) {
        this.view = view;
        handler = new Handler();
    }

    @Override
    public void init() {
        tokenUser = view.getTokenUser();
        boolean needToRegister = TextUtils.isEmpty(tokenUser);
        if (needToRegister) {
            view.moveToRegisterActivity();
            return;
        }
        view.initViews();
        new Thread(new Runnable() {
            @Override
            public void run() {
                users = view.getAllUsers();
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        view.initUsersList(users);
                    }
                });
            }
        }).start();
    }


}
