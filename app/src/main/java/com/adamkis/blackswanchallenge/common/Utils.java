package com.adamkis.blackswanchallenge.common;

import android.util.Log;

/**
 * Created by akis on 30/08/16.
 */
public class Utils {

    public static final String LOG_TAG = "BlackSwan";

    public static void log(String string) {
        longLog(LOG_TAG, string);
    }

    public static void longLog( String string){
        longLog(LOG_TAG, string);
    }

    public static void longLog(String tag, String string){
        StringBuffer sb = new StringBuffer(string);
        if (sb.length() > 4000) {
            int chunkCount = sb.length() / 4000;
            for (int i = 0; i <= chunkCount; i++) {
                int max = 4000 * (i + 1);
                if (max >= sb.length()) {
                    log(tag, sb.substring(4000 * i));
                } else {
                    log(tag, sb.substring(4000 * i, max));
                }
            }
        } else {
            log(tag, sb.toString());
        }
    }

    public static void log(String tag, String string) {
        Log.i(tag, string);
    }

}
