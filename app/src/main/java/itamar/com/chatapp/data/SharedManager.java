package itamar.com.chatapp.data;

import android.content.Context;
import android.content.SharedPreferences;


public class SharedManager {
    private static final String TAG = "SharedManager";
    private static final String KEY_TOKEN_USER = "KEY_TOKEN_USER";
    private static final String KEY_TOKEN_FCM = "KEY_TOKEN_FCM";
    private static final String KEY_USER_ID = "KEY_USER_ID";
    private SharedPreferences preferences;


    public SharedManager(Context context) {
        preferences = context.getSharedPreferences(TAG, Context.MODE_PRIVATE);
    }

    public String getTokenUser() {
        return getString(KEY_TOKEN_USER);
    }

    public void setTokenUser(String tokenUser) {
        setString(KEY_TOKEN_USER, tokenUser);
    }

    public String getTokenFcm() {
        return getString(KEY_TOKEN_FCM);
    }

    public void setTokenFcm(String tokenFcm) {
        setString(KEY_TOKEN_FCM, tokenFcm);
    }

    public String getMyId() {
        return getString(KEY_USER_ID);
    }

    public void setMyId(String userId) {
        setString(KEY_USER_ID, userId);
    }


    private String getString(String key, String defValue) {
        return preferences.getString(key, defValue);
    }

    private String getString(String key) {
        return getString(key, null);
    }

    private void setString(String key, String value) {
        preferences.edit().putString(key, value).apply();
    }


}
