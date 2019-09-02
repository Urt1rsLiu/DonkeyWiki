package com.urt1rs.donkeywiki.util;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.viewpager.widget.ViewPager;

import com.urt1rs.donkeywiki.R;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


/**
 * File description.
 *
 * @author JULI04
 * @date 2018/6/29
 */

public class StatusBarUtils {
    //华为刘海屏全屏显示FLAG
    public static final int FLAG_NOTCH_SUPPORT = 0x00010000;
    public static final int NOTCH_IN_SCREEN_VOIO = 0x00000020;//是否有凹槽
    public static final int ROUNDED_IN_SCREEN_VOIO = 0x00000008;//是否有圆角

    public static final String TAG = "StatusBarUtils";

    public static void setWindowStatusBarColor(Activity activity, int colorResId) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            activity.getWindow().getDecorView()
                    .setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            activity.getWindow().setStatusBarColor(activity.getResources().getColor(colorResId));
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            //5.0
            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            activity.getWindow().setStatusBarColor(activity.getResources().getColor(R.color.colorPrimary));
        }

    }


    /**
     * 华为手机判断是否刘海屏
     *
     * @param context
     * @return
     */
    public static boolean hasNotchInScreen(Context context) {
        boolean ret = false;
        try {
            ClassLoader cl = context.getClassLoader();
            Class HwNotchSizeUtil = cl.loadClass("com.huawei.android.util.HwNotchSizeUtil");
            Method get = HwNotchSizeUtil.getMethod("hasNotchInScreen");
            ret = (boolean) get.invoke(HwNotchSizeUtil);
        } catch (ClassNotFoundException e) {
            Log.e("test", "hasNotchInScreen ClassNotFoundException");
        } catch (NoSuchMethodException e) {
            Log.e("test", "hasNotchInScreen NoSuchMethodException");
        } catch (Exception e) {
            Log.e("test", "hasNotchInScreen Exception");
        } finally {
            return ret;
        }
    }

    /**
     * 设置应用窗口在华为刘海屏手机使用刘海区
     *
     * @param window 应用页面window对象
     */
    public static void setFullScreenWindowLayoutInDisplayCutout(Window window) {
        if (window == null) {
            return;
        }
        WindowManager.LayoutParams layoutParams = window.getAttributes();
        try {
            Class layoutParamsExCls = Class.forName("com.huawei.android.view.LayoutParamsEx");
            Constructor con = layoutParamsExCls.getConstructor(ViewPager.LayoutParams.class);
            Object layoutParamsExObj = con.newInstance(layoutParams);
            Method method = layoutParamsExCls.getMethod("addHwFlags", int.class);
            method.invoke(layoutParamsExObj, FLAG_NOTCH_SUPPORT);
        } catch (ClassNotFoundException | NoSuchMethodException | IllegalAccessException | InstantiationException
                | InvocationTargetException e) {
            Log.e("test", "hw add notch screen flag api error");
        } catch (Exception e) {
            Log.e("test", "other Exception");
        }
    }


    /**
     * 判断Voio是否有凹槽
     *
     * @param context 上下文对象
     * @return true表示具备此特征，false表示没有此特征
     */
    public static boolean hasNotchInScreenAtVoio(Context context) {
        boolean ret = false;
        try {
            ClassLoader cl = context.getClassLoader();
            Class FtFeature = cl.loadClass("com.util.FtFeature");
            Method get = FtFeature.getMethod("isFeatureSupport", int.class);
            ret = (boolean) get.invoke(FtFeature, NOTCH_IN_SCREEN_VOIO);

        } catch (ClassNotFoundException e) {
            Log.e("test", "hasNotchInScreen ClassNotFoundException");
        } catch (NoSuchMethodException e) {
            Log.e("test", "hasNotchInScreen NoSuchMethodException");
        } catch (Exception e) {
            Log.e("test", "hasNotchInScreen Exception");
        } finally {
            return ret;
        }
    }


}
