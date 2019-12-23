package com.urt1rs.donkeywiki.activity

import android.os.Bundle

import androidx.fragment.app.Fragment

import com.urt1rs.donkeywiki.R
import com.urt1rs.donkeywiki.fragment.HireFragment

/**
 * @author Hongzhi.Liu
 */
class MainActivity : BaseActivity() {

    private val mCurrentTab = 0

    private val mFragments = Array<Fragment?>(3) { null }

    internal override val layoutResourceId: Int
        get() = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onResume() {
        super.onResume()
        selectTab(mCurrentTab)
    }


    private fun selectTab(tabIndex: Int) {
        if (tabIndex < 0 || tabIndex > 2) {
            return
        }
        if (null == mFragments[tabIndex]) {
            when (tabIndex) {
                0 -> mFragments[tabIndex] = HireFragment()
                1 -> {
                    //                    mFragments[tabIndex] = new HireFragment();
                }
                2 -> {
                    //                    mFragments[tabIndex] = new HireFragment();
                }
                else -> {
                }
            }
            supportFragmentManager.beginTransaction().add(R.id.fl_fragment, mFragments[tabIndex]!!, HireFragment::class.java.simpleName).commitAllowingStateLoss()
        } else if (!mFragments[tabIndex]!!.isAdded) {
            supportFragmentManager.beginTransaction().add(R.id.fl_fragment, mFragments[tabIndex]!!, HireFragment::class.java.simpleName).commitAllowingStateLoss()
        }
        supportFragmentManager.beginTransaction().hide(mFragments[mCurrentTab]!!).show(mFragments[tabIndex]!!).commitAllowingStateLoss()
    }
}
