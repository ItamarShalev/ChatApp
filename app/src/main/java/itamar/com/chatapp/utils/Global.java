package itamar.com.chatapp.utils;

import android.content.Context;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.text.TextUtils;
import android.util.Pair;

import java.util.Random;

import itamar.com.chatapp.R;

/**
 * Created by Itamar on 29/07/2018.
 */

public class Global {


    /**
     * @param bundle contains pdus
     * @return null if make some error, or pair, first is phoneNumberFrom, and second is the message text
     */
    public static Pair<String, String> getDetailsMessageFromBundle(Bundle bundle) {
        Pair<String,String> pair = null;
        String lastNumber = null;
        StringBuilder messageFrom = new StringBuilder();
        if (bundle != null) {
            try {
                Object[] pdus = (Object[]) bundle.get("pdus");
                SmsMessage[] msgs = new SmsMessage[pdus.length];
                for (int i = 0; i < msgs.length; i++) {
                    msgs[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);
                    String numberPhoneFrom = msgs[i].getOriginatingAddress();
                    if (i == 0){
                        lastNumber = numberPhoneFrom;
                        messageFrom.append(msgs[i].getMessageBody());
                    }else if (lastNumber.equals(numberPhoneFrom)){
                        messageFrom.append(msgs[i].getMessageBody());
                    }else{
                        break;
                    }
                }
            } catch (Exception e) {
//                            Log.d("Exception caught",e.getMessage());
            }


        }
        String message = messageFrom.toString();
        if (!TextUtils.isEmpty(message)){
            pair = new Pair<>(lastNumber,message);
        }
        return pair;
    }



    public static String getRandomString(int length) {
        String saltChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder result = new StringBuilder();
        Random rnd = new Random();
        while (result.length() < length) {
            int index = (int) (rnd.nextFloat() * saltChars.length());
            result.append(saltChars.charAt(index));
        }
        return result.toString();

    }
}
