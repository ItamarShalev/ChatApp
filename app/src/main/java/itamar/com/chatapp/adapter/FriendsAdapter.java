package itamar.com.chatapp.adapter;

import android.content.Context;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiManager;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.telephony.SmsManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import itamar.com.chatapp.data.User;

/**
 * Created by Itamar on 03/08/2018.
 */

public class FriendsAdapter extends RecyclerView.Adapter<FriendsAdapter.Holder> {
    private List<User> userList;
    private Context context;
    private LayoutInflater layoutInflater;

    public FriendsAdapter(List<User> userList, Context context, LayoutInflater layoutInflater) {
        this.userList = userList;
        this.context = context;
        SmsManager smsManager = SmsManager.getDefault();
        this.layoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    static class Holder extends ChatAdapter.Holder{
        public Holder(View itemView) {
            super(itemView);
        }
    }
}
