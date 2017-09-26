package com.gc.listaspamcallchecker;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.TelephonyManager;
import android.widget.Toast;

/**
 * Created by Navas on 26/9/17.
 */

public class CallReceiver extends BroadcastReceiver {

    public static String number;
    @Override
    public void onReceive(final Context context, Intent intent) {
        String stateStr = intent.getExtras().getString(TelephonyManager.EXTRA_STATE);
        String number = intent.getExtras().getString(TelephonyManager.EXTRA_INCOMING_NUMBER);
        if("RINGING".equals(stateStr) && number != null) {
            CallReceiver.number = number;
            context.startService(new Intent(context, CheckPhoneService.class));
        }
    }
}
