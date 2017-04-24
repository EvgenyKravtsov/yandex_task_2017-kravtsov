package com.yandex_task_2017_kravtsov;

import android.app.Application;
import android.content.Context;

public final class AppController extends Application {

    private static Context appContext;

    //// APPLICATION

    @Override
    public void onCreate() {
        super.onCreate();
        appContext = getApplicationContext();
    }

    ////

    public static Context getAppContext() {
        return appContext;
    }
}
