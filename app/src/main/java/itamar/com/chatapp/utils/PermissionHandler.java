package itamar.com.chatapp.utils;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;


public class PermissionHandler {

    private Activity activity;
    private int requestCode;
    private Runnable actionWhenHave;
    private Runnable actionWhenNoHave;
    private Runnable actionNoRequestAnymore;
    private Runnable beforeRequest;
    private String[] permissions;
    private int status =  0;
    private int indexPermission;
    private int length;


    public PermissionHandler(Activity activity, int requestCode) {
        this.activity = activity;
        this.requestCode = requestCode;
    }

    public void initAll(Runnable actionWhenHave, Runnable actionWhenNoHave, Runnable actionNoRequestAnymore, Runnable beforeRequest, String... permissions){
        this.actionWhenHave = actionWhenHave;
        this.actionWhenNoHave = actionWhenNoHave;
        this.actionNoRequestAnymore = actionNoRequestAnymore;
        this.beforeRequest = beforeRequest;
        this.permissions = permissions;
        status = 1;
    }

    public void initMin(Runnable actionWhenHave, Runnable actionWhenNoHave,String... permissions){
        initAll(actionWhenHave,actionWhenNoHave,null,null,permissions);
    }
    public void initWithBedore(Runnable actionWhenHave, Runnable beforeRequest,String... permissions){
        initAll(actionWhenHave,actionWhenNoHave,null,beforeRequest,permissions);
    }


    private static boolean needRequestRuntimePermissions() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.M;
    }

    public void requestPermission() {
        if (status == 0){
            throw new RuntimeException("init not call()");
        }
        if (status != 1){
            throw new RuntimeException("wait to onRequestPermissionsResult();");
        }
        length = permissions.length;
        if (!needRequestRuntimePermissions()) {
            actionWhenHave.run();
        } else {
            boolean result = true;
            for (String permission : permissions) {
                int resultNumber = ContextCompat.checkSelfPermission(activity, permission);
                if (resultNumber != PackageManager.PERMISSION_GRANTED) {
                    result = false;
                    break;
                }
            }
            if (result) {
                actionWhenHave.run();
            } else {
                status = 2;
/*
                ActivityCompat.requestPermissions(activity, new String[]{permissions[indexPermission]}, requestCode);
*/
                ActivityCompat.requestPermissions(activity,permissions, requestCode);

            }
        }

    }

    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults, boolean allMustToBeTrue) {
        if (status == 0){
            throw new RuntimeException("init not call()");

        }
        if (status != 2){
            throw new RuntimeException("requestPermission not call()");
        }
        boolean result;
        if (allMustToBeTrue) {
            result = true;
            for (int grantResult : grantResults) {
                if (grantResult != PackageManager.PERMISSION_GRANTED) {
                    result = false;
                    break;
                }
            }
        }else{
            result = false;
            for (int grantResult : grantResults) {
                if (grantResult == PackageManager.PERMISSION_GRANTED) {
                    result = true;
                    break;
                }
            }
        }
        if (result) {
            actionWhenHave.run();
        } else {
            actionWhenNoHave.run();
        }
    }

}
