package com.rpifinal.hitema.controller;

import android.os.Bundle;
import com.rpifinal.hitema.R;

public class UpdateUserActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_user);
    }

    @Override
    public int getFragmentLayout() { return R.layout.activity_profile; }
}
