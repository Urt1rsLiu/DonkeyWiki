package com.urt1rs.donkeywiki

import android.app.Application
import android.content.Context
import android.os.Bundle

class DonkeyWikiApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        appContext = applicationContext
    }

    companion object {

        var appContext: Context? = null
            private set
    }

}
