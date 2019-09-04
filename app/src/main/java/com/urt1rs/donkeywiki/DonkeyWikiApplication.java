package com.urt1rs.donkeywiki;

import android.app.Application;
import android.content.Context;
import android.os.Bundle;

public class DonkeyWikiApplication extends Application {

    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
    }

    public static Context getAppContext() {
        return mContext;
    }

}
