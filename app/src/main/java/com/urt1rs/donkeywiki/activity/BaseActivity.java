package com.urt1rs.donkeywiki.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

/**
 * @author Hongzhi.Liu
 * @date 2019/8/23
 */
public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResourceId());

    }

    /**
     * get layout xml resource id
     * @return
     */
    abstract int getLayoutResourceId();


}
