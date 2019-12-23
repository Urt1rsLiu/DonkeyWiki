package com.urt1rs.donkeywiki.activity

import android.content.res.Resources
import android.os.Bundle

import androidx.appcompat.app.AppCompatActivity

import com.urt1rs.donkeywiki.R
import com.urt1rs.donkeywiki.util.StatusBarUtils
import com.urt1rs.donkeywiki.util.UIUtils

import butterknife.ButterKnife

/**
 * @author Hongzhi.Liu
 * @date 2019/8/23
 */
abstract class BaseActivity : AppCompatActivity() {

    /**
     * get layout xml resource id
     *
     * @return
     */
    internal abstract val layoutResourceId: Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutResourceId)
        ButterKnife.bind(this)
        StatusBarUtils.setWindowStatusBarColor(this, R.color.colorPrimary)
    }

    override fun getResources(): Resources {
        return UIUtils.setScreenXDpi(super.getResources())
    }


}
