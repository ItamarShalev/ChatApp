package itamar.com.chatapp.chat;



public interface ChatMVP {

    interface View{
        void initView();

    }

    interface Presenter{

        void init();
    }
}
