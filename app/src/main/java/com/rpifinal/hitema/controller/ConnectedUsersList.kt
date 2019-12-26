package com.rpifinal.hitema.controller

import android.os.Bundle
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import api.UserHelper.connectedUsers
import com.google.firebase.firestore.QuerySnapshot
import com.rpifinal.hitema.R
import com.rpifinal.hitema.model.User
import java.util.*

class ConnectedUsersList : AppCompatActivity() {
    private var listeUser: ArrayList<User>? = null
    private var list: List<User?>? = null
    private val laVueUser: ListView = findViewById(R.id.id_lavue)
    private var userAdapter: ArrayAdapterUser? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_list_view)

        // création de la liste d'user
        listeUser = ArrayList()
        connectedUsers.addOnSuccessListener { QuerySnapshot: QuerySnapshot ->
            list = QuerySnapshot.toObjects(User::class.java)
            listeUser = ArrayList<User>(list!!)
            userAdapter = ArrayAdapterUser(this, listeUser)
            laVueUser.adapter = userAdapter
        }
    }
}