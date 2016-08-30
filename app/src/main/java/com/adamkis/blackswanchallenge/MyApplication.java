package com.adamkis.blackswanchallenge;

import android.app.Application;
import android.content.Context;

/**
 * Created by akis on 30/08/16.
 */
public class MyApplication extends Application {

    private static MyApplication sInstance;

    public static synchronized MyApplication get() {
        return sInstance;
    }

    public static synchronized Context getAppContext(){
        return sInstance.getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
    }

}
