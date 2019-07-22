package com.rpifinal.hitema.controller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.rpifinal.hitema.R;

import butterknife.OnClick;

public class MainMenu extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
    }

    @OnClick(R.id.main_activity_btn_login)
    public void onClickProfilButton() {

        Intent profil = new Intent(MainMenu.this, ProfileActivity.class);
        startActivity(profil);
    }
}
