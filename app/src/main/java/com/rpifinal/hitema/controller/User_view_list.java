package com.rpifinal.hitema.controller;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.rpifinal.hitema.R;
import com.rpifinal.hitema.model.User;

import java.util.ArrayList;

public class User_view_list extends AppCompatActivity {
    ArrayList liste_user;
    ListView laVueUser;
    ArrayAdapterUser userAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list_view);

        // création de la liste d'user

        liste_user = new ArrayList<User>();
        liste_user.add(new User("1", "email", "username", "firstName1", "lastName"," ", 1, 10, "true"));
        liste_user.add(new User("2", "email", "username", "firstName2", "lastName"," ", 1, 10, "false"));
        liste_user.add(new User("3", "email", "username", "firstName3", "lastName"," ", 1, 10, "false"));

        laVueUser = findViewById(R.id.id_lavue);
        userAdapter = new ArrayAdapterUser(this, liste_user);
        laVueUser.setAdapter(userAdapter);
    }
}