package com.rpifinal.hitema.services;

import androidx.annotation.Nullable;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.messaging.FirebaseMessagingService;

public class CloudMessagingService extends FirebaseMessagingService {

    @Override
    public void onNewToken(String s) {

        super.onNewToken(s);
    }

    // Récupère l'utilisateur actuellement connecté
    @Nullable
    protected FirebaseUser getCurrentUser() {

        return FirebaseAuth.getInstance().getCurrentUser();
    }

    public static CollectionReference getUsersCollection() {

        return FirebaseFirestore.getInstance().collection("resgistration");
    }

    private Task<Void> sendRegistrationToServer(String uid, String token) {

        return null;
    }
}
