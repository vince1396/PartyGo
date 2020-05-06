package com.rpifinal.hitema.partyGo.data.model

import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import oldFiles.model.User

object UserHelper {
    private const val COLLECTION_NAME = "users"
    // =============================================================================================
    // --- COLLECTION REFERENCE ---
    private val usersCollection: CollectionReference
        get() = FirebaseFirestore.getInstance().collection(COLLECTION_NAME)

    // =============================================================================================
    // --- CREATE ---
    fun createUser(uid: String?, email: String?, username: String?, firstName: String?,
                   lastName: String?, urlPicture: String?, lvl: Int, xp: Int, isConnected: String?, token: String?): Task<Void> {
        val userToCreate = User(uid, email, username, firstName, lastName, urlPicture, lvl, xp, isConnected, token)
        return usersCollection.document(uid!!).set(userToCreate)
    }

    // =============================================================================================
    // --- READ ---

    fun getUser(uid: String?): Task<DocumentSnapshot> {
        return usersCollection.document(uid!!).get()
    }

    val connectedUsers: Task<QuerySnapshot>
        get() = usersCollection.whereEqualTo("isConnected", "true").get()

    // =============================================================================================
    // --- UPDATE ---
    fun updateUsername(username: String?, uid: String?): Task<Void> {
        return usersCollection.document(uid!!).update("username", username)
    }

    fun updateFirstName(firstName: String?, uid: String?): Task<Void> {
        return usersCollection.document(uid!!).update("firstName", firstName)
    }

    fun updateLastName(lastName: String?, uid: String?): Task<Void> {
        return usersCollection.document(uid!!).update("lastName", lastName)
    }

    fun updateIsConnected(isConnected: String?, uid: String?): Task<Void> {
        return usersCollection.document(uid!!).update("isConnected", isConnected)
    }

    fun updateToken(token: String?, uid: String?): Task<Void> {
        return usersCollection.document(uid!!).update("token", token)
    }

    // =============================================================================================
    // --- DELETE ---
    fun deleteUser(uid: String?): Task<Void> {
        return usersCollection.document(uid!!).delete()
    }
}