package com.gc.listaspamcallchecker;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by Navas on 26/9/17.
 */

public class PhoneSpamChecker {

    public static void checkNumber(final String number, final OnPhoneCheckedListener listener){
        new AsyncTask<Void,Void,Boolean>(){

            @Override
            protected Boolean doInBackground(Void... params) {
                try {
                    URL url = new URL("https://www.listaspam.com/busca.php?Telefono="+number);
                    HttpsURLConnection urlConnection = (HttpsURLConnection) url.openConnection();
                    urlConnection.connect();
                    if(urlConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                        InputStreamReader in = new InputStreamReader((InputStream)  urlConnection.getContent());
                        BufferedReader buff = new BufferedReader(in);
                        StringBuffer stringBuffer = new StringBuffer();
                        for(String line=buff.readLine(); line != null;line=buff.readLine()) {
                            stringBuffer.append(line);
                        }
                        String response = stringBuffer.toString();
                        return response.contains("media-list");
                    }
                    else
                        return false;
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPostExecute(Boolean check) {
                listener.onPhoneChecked(check);
            }
        }.execute();

    }

    public interface OnPhoneCheckedListener{
        void onPhoneChecked(boolean checked);
    }
}
