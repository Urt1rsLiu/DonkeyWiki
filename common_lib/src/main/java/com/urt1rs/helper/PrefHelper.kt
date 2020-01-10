package com.urt1rs.donkeywiki.helper

import android.content.Context
import android.content.SharedPreferences

class PrefHelper private constructor(context: Context) : SharedPreferences {

    private val sp: SharedPreferences by lazy {
        context.getSharedPreferences(DEFAULT_NAME, Context.MODE_PRIVATE)
    }

    init {

    }

    companion object {

        const val DEFAULT_NAME = "shared_preference_name"

        private var instance: PrefHelper? = null

        fun getInstance(context: Context): PrefHelper {
            if (null == instance) {
                synchronized(PrefHelper::class.java) {
                    instance = PrefHelper(context)
                }
            }
            return instance!!
        }
    }

    override fun contains(key: String?): Boolean {
        return sp.contains(key)
    }

    override fun getBoolean(key: String?, defValue: Boolean): Boolean {
        return sp.getBoolean(key, defValue)
    }


    override fun getInt(key: String?, defValue: Int): Int {
        return sp.getInt(key, defValue)
    }

    override fun getAll(): MutableMap<String, *> {
        return sp.all
    }

    override fun getLong(key: String?, defValue: Long): Long {
        return sp.getLong(key, defValue)
    }

    override fun getFloat(key: String?, defValue: Float): Float {
        return sp.getFloat(key, defValue)
    }

    override fun getStringSet(key: String, defValues: Set<String>?): Set<String>? {
        return sp.getStringSet(key, defValues)
    }

    override fun getString(key: String?, defValue: String?): String? {
        return sp.getString(key, defValue)
    }

    override fun edit(): SharedPreferences.Editor {
        return sp.edit()
    }


    override fun registerOnSharedPreferenceChangeListener(listener: SharedPreferences.OnSharedPreferenceChangeListener?) {
        return sp.registerOnSharedPreferenceChangeListener(listener)
    }

    override fun unregisterOnSharedPreferenceChangeListener(listener: SharedPreferences.OnSharedPreferenceChangeListener?) {
        return sp.unregisterOnSharedPreferenceChangeListener(listener)
    }


}