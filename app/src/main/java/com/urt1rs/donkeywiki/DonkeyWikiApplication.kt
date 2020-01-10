package com.urt1rs.donkeywiki

import android.app.Application
import android.content.Context
import android.os.Bundle
import com.urt1rs.donkeywiki.helper.PrefHelper

class DonkeyWikiApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        appContext = applicationContext
    }

    companion object {
        lateinit var appContext: Context
    }

}
