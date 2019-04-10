package com.rpifinal.hitema.controller;

import android.os.Bundle;
import android.widget.Button;

import com.rpifinal.hitema.R;

import butterknife.BindView;

public class UpdateUserActivity extends BaseActivity {

    @BindView(R.id.update_activity_username_submit) Button updateUsernameButton;
    @BindView(R.id.update_activity_firstName_submit) Button updateFirstNameButton;
    @BindView(R.id.update_activity_lastName_submit) Button updateLastNameButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_user);
    }

    @Override
    public int getFragmentLayout() { return R.layout.activity_profile; }

    public void onClickUsernameButton() {

    }

    public void onClickFirstNameButton() {


    }

    public void onClickLastNameButton() {


    }
}