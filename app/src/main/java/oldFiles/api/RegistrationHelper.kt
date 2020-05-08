package oldFiles.api

//import oldFiles.api.UserHelper.usersCollection
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import oldFiles.model.Registration

object RegistrationHelper {
    private const val COLLECTION_NAME = "registration"
    // =============================================================================================
    // --- COLLECTION REFERENCE ---
    val registrationCollection: CollectionReference
        get() = FirebaseFirestore.getInstance().collection(COLLECTION_NAME)

    // =============================================================================================
    // --- CREATE ---
    fun createRegistration(uid: String?, token: String?): Task<Void> {
        val registrationToCreate = Registration(uid, token)
        return registrationCollection.document(uid!!).set(registrationToCreate)
    }

    // =============================================================================================
    // --- READ ---
    fun getRegistration(uid: String?): Task<DocumentSnapshot> {
        return registrationCollection.document(uid!!).get()
    }

    // =============================================================================================
    // --- UPDATE ---
    fun updateRegistration(uid: String?, token: String?): Task<Void> {
        return registrationCollection.document(uid!!).update("token", token)
    }

    // =============================================================================================
    // --- DELETE ---
/*    fun deleteRegistration(uid: String?): Task<Void> {
        //return usersCollection.document(uid!!).delete()
    }*/
}