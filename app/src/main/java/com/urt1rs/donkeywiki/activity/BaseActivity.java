package com.urt1rs.donkeywiki.activity;

import android.content.res.Resources;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.urt1rs.donkeywiki.R;
import com.urt1rs.donkeywiki.util.StatusBarUtils;
import com.urt1rs.donkeywiki.util.UIUtils;

import butterknife.ButterKnife;

/**
 * @author Hongzhi.Liu
 * @date 2019/8/23
 */
public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResourceId());
        ButterKnife.bind(this);
        StatusBarUtils.setWindowStatusBarColor(this, R.color.colorPrimary);
    }

    @Override
    public Resources getResources() {
        return UIUtils.setScreenXDpi(super.getResources());
    }

    /**
     * get layout xml resource id
     *
     * @return
     */
    abstract int getLayoutResourceId();


}
