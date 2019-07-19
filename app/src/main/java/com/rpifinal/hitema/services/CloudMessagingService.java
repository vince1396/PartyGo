package com.rpifinal.hitema.services;

import android.util.Log;
import com.google.firebase.messaging.FirebaseMessagingService;
import api.UserHelper;

public class CloudMessagingService extends FirebaseMessagingService {

    @Override
    public void onNewToken(String s) {

        super.onNewToken(s);
    }

    private void sendRegistrationToServer(String uid, String token) {

        UserHelper.updateToken(uid, token).addOnSuccessListener(aVoid ->
                Log.i("CMService", "Token updated"));
    }
}
