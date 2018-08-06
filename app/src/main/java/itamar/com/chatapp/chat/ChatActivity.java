package itamar.com.chatapp.chat;

import android.support.annotation.MainThread;
import android.support.annotation.UiThread;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import itamar.com.chatapp.R;

public class ChatActivity extends AppCompatActivity implements ChatMVP.View {

    private ChatPresenter chatPresenter;


    @Override
    @MainThread
    @UiThread

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        chatPresenter = new ChatPresenter(this);
        chatPresenter.init();
    }

    @Override
    public void initView() {
    }

}
