package itamar.com.chatapp.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import java.util.List;

import itamar.com.chatapp.data.Message;
import itamar.com.chatapp.utils.AppContext;
import itamar.com.chatapp.utils.Global;


public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.Holder>{

    private Context context;
    private List<Message> messageList;
    private LayoutInflater layoutInflater;
    private String myUserId;
    private boolean isRTL;

    public ChatAdapter(Context context, List<Message> messageList,String myUserId) {
        this.context = context;
        this.messageList = messageList;
        this.layoutInflater = LayoutInflater.from(context);
        this.myUserId = myUserId;
        isRTL = AppContext.isRTL(context);
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        Message message = messageList.get(position);
        boolean isMyMessage = message.getUserSendId().equals(myUserId);
        int gravity;

        if (isRTL && isMyMessage){
            gravity = Gravity.LEFT;
            //message to left gravity
        }else{
            gravity = Gravity.RIGHT;
            //message to right gravity
        }
        ((RelativeLayout) holder.itemView).setGravity(gravity);

    }

    @Override
    public int getItemCount() {
        return messageList.size();
    }

    static class Holder extends RecyclerView.ViewHolder{

        Holder(View itemView) {
            super(itemView);
        }
    }
}
