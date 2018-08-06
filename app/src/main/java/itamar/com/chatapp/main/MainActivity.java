package itamar.com.chatapp.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import itamar.com.chatapp.R;
import itamar.com.chatapp.data.SharedManager;
import itamar.com.chatapp.data.User;
import itamar.com.chatapp.register.RegisterActivity;


public class MainActivity extends AppCompatActivity implements MainMVP.View{

    private MainPresenter mainPresenter;
    private SharedManager sharedManager;
    private RecyclerView friendsRecyclerView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainPresenter = new MainPresenter(this);
        sharedManager = new SharedManager(getApplicationContext());
        mainPresenter.init();
    }

    @Override
    public void initViews() {
        friendsRecyclerView = findViewById(R.id.friends_recycler_view);

    }


    @Override
    public String getTokenUser() {
        return sharedManager.getTokenUser();
    }

    @Override
    public void moveToRegisterActivity() {
        startActivity(new Intent(this, RegisterActivity.class));
        finish();
    }

    @Override
    public User getCurrentUser() {
        return null;
    }

    @Override
    public void moveToEnter(@NonNull Bundle bundle) {
        Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
        intent.putExtras(bundle);
        startActivity(intent);
        finish();
    }

    @Override
    public boolean iAlreadyRegister() {
        return false;
    }

    @Override
    public List<User> getAllUsers() {
        return null;
    }

    @Override
    public void initUsersList(List<User> users) {

    }
}
