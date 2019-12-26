package api

import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import com.rpifinal.hitema.model.User

object UserHelper {
    private const val COLLECTION_NAME = "users"
    // =============================================================================================
    // --- COLLECTION REFERENCE ---
    @JvmStatic
    val usersCollection: CollectionReference
        get() = FirebaseFirestore.getInstance().collection(COLLECTION_NAME)

    // =============================================================================================
    // --- CREATE ---
    @JvmStatic
    fun createUser(uid: String?, email: String?, username: String?, firstName: String?,
                   lastName: String?, urlPicture: String?, lvl: Int, xp: Int, isConnected: String?, token: String?): Task<Void> {
        val userToCreate = User(uid, email, username, firstName, lastName, urlPicture, lvl, xp, isConnected, token)
        return usersCollection.document(uid!!).set(userToCreate)
    }

    // =============================================================================================
    // --- READ ---
    @JvmStatic
    fun getUser(uid: String?): Task<DocumentSnapshot> {
        return usersCollection.document(uid!!).get()
    }

    @JvmStatic
    val connectedUsers: Task<QuerySnapshot>
        get() = usersCollection.whereEqualTo("isConnected", "true").get()

    // =============================================================================================
    // --- UPDATE ---
    @JvmStatic
    fun updateUsername(username: String?, uid: String?): Task<Void> {
        return usersCollection.document(uid!!).update("username", username)
    }

    @JvmStatic
    fun updateFirstName(firstName: String?, uid: String?): Task<Void> {
        return usersCollection.document(uid!!).update("firstName", firstName)
    }

    @JvmStatic
    fun updateLastName(lastName: String?, uid: String?): Task<Void> {
        return usersCollection.document(uid!!).update("lastName", lastName)
    }

    @JvmStatic
    fun updateIsConnected(isConnected: String?, uid: String?): Task<Void> {
        return usersCollection.document(uid!!).update("isConnected", isConnected)
    }

    @JvmStatic
    fun updateToken(token: String?, uid: String?): Task<Void> {
        return usersCollection.document(uid!!).update("token", token)
    }

    // =============================================================================================
    // --- DELETE ---
    @JvmStatic
    fun deleteUser(uid: String?): Task<Void> {
        return usersCollection.document(uid!!).delete()
    }
}