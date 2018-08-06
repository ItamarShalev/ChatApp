package itamar.com.chatapp.register;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.telephony.SmsManager;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import itamar.com.chatapp.BuildConfig;
import itamar.com.chatapp.R;
import itamar.com.chatapp.data.SharedManager;
import itamar.com.chatapp.main.MainActivity;
import itamar.com.chatapp.utils.Global;
import itamar.com.chatapp.utils.PermissionHandler;

public class RegisterActivity extends AppCompatActivity implements RegisterMVP.View {

    private RegisterPresenter registerPresenter;

    private EditText phoneNumberEditText;
    private BroadcastReceiver broadcastReceiverSms;
    private PermissionHandler permissionHandler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        registerPresenter = new RegisterPresenter(this);
        registerPresenter.init();
    }


    @Override
    public void initViews() {
        Button sendPhoneNumberButton = findViewById(R.id.send_phone_number_button);
        phoneNumberEditText = findViewById(R.id.phone_number_edit_text);

        sendPhoneNumberButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerPresenter.buttonClicked();
            }
        });


        permissionHandler = new PermissionHandler(this, 1);
        if (BuildConfig.DEBUG) {
            registerPresenter.buttonClicked();
        }


    }

    @Override
    public String getPhoneNumber() {
        if (BuildConfig.DEBUG) {
            return "0524161124";
        }
        return phoneNumberEditText.getText().toString();
    }

    @Override
    public void noInternetAvailable() {
        Toast.makeText(this, "noInternetAvailable", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showError(int error) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void listenToMessages() {
        permissionHandler.initMin(
                new Runnable() {
                    @Override
                    public void run() {
                        registerBroadcast();
                    }
                },
                new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(RegisterActivity.this, "No have permission", Toast.LENGTH_SHORT).show();
                    }
                }, "android.permission.SEND_SMS", "android.permission.RECEIVE_SMS");
        permissionHandler.requestPermission();
    }

    private void registerBroadcast() {
        broadcastReceiverSms = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                Pair<String, String> detailsMessage = Global.getDetailsMessageFromBundle(intent.getExtras());
                if (detailsMessage != null) {
                    String numberPhoneFrom = detailsMessage.first;
                    String messageFrom = detailsMessage.second;
                    if (registerPresenter.messageReceived(numberPhoneFrom, messageFrom)) {
                        unregisterReceiver(this);
                    }
                }
            }
        };
        registerReceiver(broadcastReceiverSms, new IntentFilter("android.provider.Telephony.SMS_RECEIVED"));
        registerPresenter.readyToListen();

    }


    @Override
    public void sendSms(String phoneNumber, String message) {
        SmsManager sms = SmsManager.getDefault();
        sms.sendTextMessage(phoneNumber, null, message, null, null);
    }

    @Override
    public void saveUserToken(String token) {
        new SharedManager(getApplicationContext()).setTokenUser(token);
    }

    @Override
    public void moveToMainActivity() {
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(broadcastReceiverSms);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        permissionHandler.onRequestPermissionsResult(requestCode, permissions, grantResults,true);
    }
}

