package com.urt1rs.donkeywiki.util

import android.app.Activity
import android.content.Context
import android.os.Build
import android.util.Log
import android.view.View
import android.view.Window
import android.view.WindowManager

import androidx.viewpager.widget.ViewPager

import com.urt1rs.donkeywiki.R

import java.lang.reflect.Constructor
import java.lang.reflect.InvocationTargetException
import java.lang.reflect.Method


/**
 * File description.
 *
 * @author JULI04
 * @date 2018/6/29
 */

object StatusBarUtils {
    //华为刘海屏全屏显示FLAG
    val FLAG_NOTCH_SUPPORT = 0x00010000
    val NOTCH_IN_SCREEN_VOIO = 0x00000020//是否有凹槽
    val ROUNDED_IN_SCREEN_VOIO = 0x00000008//是否有圆角

    val TAG = "StatusBarUtils"

    fun setWindowStatusBarColor(activity: Activity, colorResId: Int) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            activity.window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            activity.window.statusBarColor = activity.resources.getColor(colorResId, null)
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            //5.0
            activity.window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            activity.window.statusBarColor = activity.resources.getColor(R.color.colorPrimary)
        }

    }


    /**
     * 华为手机判断是否刘海屏
     *
     * @param context
     * @return
     */
    fun hasNotchInScreen(context: Context): Boolean {
        var ret = false
        try {
            val cl = context.classLoader
            val HwNotchSizeUtil = cl.loadClass("com.huawei.android.util.HwNotchSizeUtil")
            val get = HwNotchSizeUtil.getMethod("hasNotchInScreen")
            ret = get.invoke(HwNotchSizeUtil) as Boolean
        } catch (e: ClassNotFoundException) {
            Log.e("test", "hasNotchInScreen ClassNotFoundException")
        } catch (e: NoSuchMethodException) {
            Log.e("test", "hasNotchInScreen NoSuchMethodException")
        } catch (e: Exception) {
            Log.e("test", "hasNotchInScreen Exception")
        } finally {
            return ret
        }
    }

    /**
     * 设置应用窗口在华为刘海屏手机使用刘海区
     *
     * @param window 应用页面window对象
     */
    fun setFullScreenWindowLayoutInDisplayCutout(window: Window?) {
        if (window == null) {
            return
        }
        val layoutParams = window.attributes
        try {
            val layoutParamsExCls = Class.forName("com.huawei.android.view.LayoutParamsEx")
            val con = layoutParamsExCls.getConstructor(ViewPager.LayoutParams::class.java)
            val layoutParamsExObj = con.newInstance(layoutParams)
            val method = layoutParamsExCls.getMethod("addHwFlags", Int::class.javaPrimitiveType!!)
            method.invoke(layoutParamsExObj, FLAG_NOTCH_SUPPORT)
        } catch (e: ClassNotFoundException) {
            Log.e("test", "hw add notch screen flag api error")
        } catch (e: NoSuchMethodException) {
            Log.e("test", "hw add notch screen flag api error")
        } catch (e: IllegalAccessException) {
            Log.e("test", "hw add notch screen flag api error")
        } catch (e: InstantiationException) {
            Log.e("test", "hw add notch screen flag api error")
        } catch (e: InvocationTargetException) {
            Log.e("test", "hw add notch screen flag api error")
        } catch (e: Exception) {
            Log.e("test", "other Exception")
        }

    }


    /**
     * 判断Voio是否有凹槽
     *
     * @param context 上下文对象
     * @return true表示具备此特征，false表示没有此特征
     */
    fun hasNotchInScreenAtVoio(context: Context): Boolean {
        var ret = false
        try {
            val cl = context.classLoader
            val FtFeature = cl.loadClass("com.util.FtFeature")
            val get = FtFeature.getMethod("isFeatureSupport", Int::class.javaPrimitiveType!!)
            ret = get.invoke(FtFeature, NOTCH_IN_SCREEN_VOIO) as Boolean

        } catch (e: ClassNotFoundException) {
            Log.e("test", "hasNotchInScreen ClassNotFoundException")
        } catch (e: NoSuchMethodException) {
            Log.e("test", "hasNotchInScreen NoSuchMethodException")
        } catch (e: Exception) {
            Log.e("test", "hasNotchInScreen Exception")
        } finally {
            return ret
        }
    }


}
