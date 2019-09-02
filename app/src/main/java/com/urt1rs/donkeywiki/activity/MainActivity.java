package com.urt1rs.donkeywiki.activity;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import com.urt1rs.donkeywiki.R;
import com.urt1rs.donkeywiki.fragment.HireFragment;

/**
 * @author Hongzhi.Liu
 */
public class MainActivity extends BaseActivity {

    private int mCurrentTab = 0;

    private Fragment[] mFragments = new Fragment[3];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    int getLayoutResourceId() {
        return R.layout.activity_main;
    }

    @Override
    protected void onResume() {
        super.onResume();
        selectTab(mCurrentTab);
    }


    private void selectTab(int tabIndex) {
        if (tabIndex < 0 || tabIndex > 2) {
            return;
        }
        if (null == mFragments[tabIndex]) {
            switch (tabIndex) {
                case 0:
                    mFragments[tabIndex] = new HireFragment();
                    break;
                case 1:
//                    mFragments[tabIndex] = new HireFragment();
                    break;
                case 2:
//                    mFragments[tabIndex] = new HireFragment();
                    break;
                default:
                    break;
            }
            getSupportFragmentManager().beginTransaction().add(R.id.fl_fragment, mFragments[tabIndex], HireFragment.class.getSimpleName()).commitAllowingStateLoss();
        } else if (!mFragments[tabIndex].isAdded()) {
            getSupportFragmentManager().beginTransaction().add(R.id.fl_fragment, mFragments[tabIndex], HireFragment.class.getSimpleName()).commitAllowingStateLoss();
        }
        getSupportFragmentManager().beginTransaction().hide(mFragments[mCurrentTab]).show(mFragments[tabIndex]).commitAllowingStateLoss();
    }
}
