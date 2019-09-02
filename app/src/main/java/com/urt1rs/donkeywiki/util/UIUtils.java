package com.urt1rs.donkeywiki.util;

import android.app.Activity;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.util.Log;

import androidx.annotation.NonNull;

import com.urt1rs.donkeywiki.DonkeyWikiApplication;
import com.urt1rs.donkeywiki.constant.UIConstants;

public class UIUtils {

    /**
     * 设置自定义的屏幕xdpi 值。
     * 在屏幕宽度为 1080
     * 的情况下，1pt = 1px。当设计稿里头使用px值的时候，可以直接使用pt值来配适其他屏幕
     * case 1080: 1pt = 1px
     * case 其他屏宽（screenWidth）： 1pt = screenWidth / 1080 px
     *
     * @param resources Activity 的resource，可以通过{@link Activity#getResources()} 获得
     */
    public static Resources setScreenXDpi(@NonNull Resources resources) {
        if (UIUtils.isTablet(resources)){
            return setScreenXDpi(resources, UIConstants.DESIGNED_SCREEN_WIDTH_TABLET);
        }else {
            return setScreenXDpi(resources, UIConstants.DESIGNED_SCREEN_WIDTH);
        }
    }

    /**
     * 设置自定义的屏幕xdpi 值。
     * 在屏幕宽度为 1080
     * 的情况下，1pt = 1px。当设计稿里头使用px值的时候，可以直接使用pt值来配适其他屏幕
     *
     * @param resources   Activity 的resource，可以通过{@link Activity#getResources()} 获得
     * @param designWidth 設計稿的屏幕寬度
     */
    public static Resources setScreenXDpi(@NonNull Resources resources, int designWidth) {
        DisplayMetrics activityDisplayMetrics = resources.getDisplayMetrics();
        float xdpi = (float) 72 * activityDisplayMetrics.widthPixels / designWidth;
        activityDisplayMetrics.xdpi = xdpi;
        activityDisplayMetrics.ydpi = xdpi;

        //注： 有些手机Rom的Application和Activity的DisplayMetrics不是同一个对象, 需要都设置
        if (DonkeyWikiApplication.getAppContext() == null) {
            Log.d("yp", "setScreenXDpi: is null");
        } else {
            DisplayMetrics appDisplayMetrics = DonkeyWikiApplication.getAppContext().getResources().getDisplayMetrics();
            appDisplayMetrics.xdpi = xdpi;
            appDisplayMetrics.ydpi = xdpi;
        }
        return resources;
    }

    /**
     * 判断当前设别是否为平板设备（根据屏幕物理尺寸判断）
     * @param resources
     * @return
     */
    public static boolean isTablet(Resources resources){
        return (resources.getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) >= Configuration.SCREENLAYOUT_SIZE_LARGE;
    }
}
