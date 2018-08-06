package itamar.com.chatapp.utils;

import android.content.Context;

import itamar.com.chatapp.R;

/**
 * Created by Itamar on 05/08/2018.
 */

public class AppContext {

    public static boolean isRTL(Context context){
        return context.getResources().getBoolean(R.bool.rtl);
    }
}
