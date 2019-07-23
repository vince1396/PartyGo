package com.rpifinal.hitema.controller;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import com.rpifinal.hitema.R;
import com.rpifinal.hitema.model.User;
import java.util.ArrayList;
import java.util.List;
import api.UserHelper;

public class ConnectedUsersList extends AppCompatActivity {

    ArrayList liste_user;
    List<User> list;
    ListView laVueUser;
    ArrayAdapterUser userAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list_view);

        // création de la liste d'user
        liste_user = new ArrayList<User>();

        UserHelper.getConnectedUsers().addOnSuccessListener(QuerySnapshot-> {

            list = QuerySnapshot.toObjects(User.class);
            liste_user = new ArrayList(list);
            userAdapter = new ArrayAdapterUser(this, liste_user);

            laVueUser = findViewById(R.id.id_lavue);
            laVueUser.setAdapter(userAdapter);
        });
    }
}