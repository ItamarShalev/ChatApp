package itamar.com.chatapp.data;




public class User {

    
    private String numberPhone;
    private String userName;
    private Message lastMessage;

    @Override
    public String toString() {
        return lastMessage.toString();
    }
}
