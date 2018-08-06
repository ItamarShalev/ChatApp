package itamar.com.chatapp.data;



public class Message<T> {
    private int id;
    private T messageBody;
    private String userSendId;
    private String userReceiverId;
    private long sendTime;
    private long receiverTime;
    private long readTime;
    private int statusMessage;

    public Message(int id, T messageBody, String userSendId, String userReceiverId, long sendTime, long receiverTime, long readTime, int statusMessage) {
        this.id = id;
        this.messageBody = messageBody;
        this.userSendId = userSendId;
        this.userReceiverId = userReceiverId;
        this.sendTime = sendTime;
        this.receiverTime = receiverTime;
        this.readTime = readTime;
        this.statusMessage = statusMessage;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public T getMessageBody() {
        return messageBody;
    }

    public void setMessageBody(T messageBody) {
        this.messageBody = messageBody;
    }

    public String getUserSendId() {
        return userSendId;
    }

    public void setUserSendId(String userSendId) {
        this.userSendId = userSendId;
    }

    public String getUserReceiverId() {
        return userReceiverId;
    }

    public void setUserReceiverId(String userReceiverId) {
        this.userReceiverId = userReceiverId;
    }

    public long getSendTime() {
        return sendTime;
    }

    public void setSendTime(long sendTime) {
        this.sendTime = sendTime;
    }

    public long getReceiverTime() {
        return receiverTime;
    }

    public void setReceiverTime(long receiverTime) {
        this.receiverTime = receiverTime;
    }

    public long getReadTime() {
        return readTime;
    }

    public void setReadTime(long readTime) {
        this.readTime = readTime;
    }

    public int getStatusMessage() {
        return statusMessage;
    }

    public void setStatusMessage(int statusMessage) {
        this.statusMessage = statusMessage;
    }
}
