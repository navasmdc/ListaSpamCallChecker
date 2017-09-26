package com.gc.listaspamcallchecker;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.widget.Toast;

/**
 * Created by Navas on 26/9/17.
 */

public class CheckPhoneService extends Service {


    @Override
    public void onCreate() {
        super.onCreate();
        PhoneSpamChecker.checkNumber(CallReceiver.number, new PhoneSpamChecker.OnPhoneCheckedListener() {
            @Override
            public void onPhoneChecked(boolean checked) {
                String message;
                if(CallReceiver.number.equals("658829125")){
                    message = "KEEP \nKALM\nIT'S YOUR LOVELY TROLL";
                }else{
                    message = checked ? "spam\nSPAM\nSPAAAAAAAM" : "IT'S YOUR LUCKY DAY\n IT'S NOT SPAM";
                }
                Toast.makeText(CheckPhoneService.this, message, Toast.LENGTH_LONG).show();
            }
        });

    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
