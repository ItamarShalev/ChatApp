package itamar.com.chatapp.chat;

import android.text.TextUtils;

/**
 * Created by Itamar on 19/07/2018.
 */

public class ChatPresenter implements ChatMVP.Presenter {

    private ChatMVP.View view;
    public ChatPresenter(ChatMVP.View view){
        this.view = view;
    }

    @Override
    public void init() {

    }
}
