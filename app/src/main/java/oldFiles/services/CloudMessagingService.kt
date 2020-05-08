package oldFiles.services

import android.util.Log
//import oldFiles.api.UserHelper.updateToken
import com.google.firebase.messaging.FirebaseMessagingService

class CloudMessagingService : FirebaseMessagingService() {
    override fun onNewToken(s: String) {
        super.onNewToken(s)
    }

/*    private fun sendRegistrationToServer(uid: String, token: String) {
        updateToken(uid, token).addOnSuccessListener { aVoid: Void? -> Log.i("CMService", "Token updated") }
    }*/
}