package api;

import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.rpifinal.hitema.model.User;

public class UserHelper {

    private static final String COLLECTION_NAME = "users";

    // =============================================================================================
    // --- COLLECTION REFERENCE ---
    public static CollectionReference getUsersCollection() {

        return FirebaseFirestore.getInstance().collection(COLLECTION_NAME);
    }
    // =============================================================================================
    // --- CREATE ---
    public static Task<Void> createUser(String uid, String email, String username, String firstName,
                                        String lastName, String urlPicture, int lvl, int xp, String isConnected, String token)
    {
        User userToCreate = new User(uid, email, username, firstName, lastName, urlPicture, lvl, xp, isConnected, token);
        return UserHelper.getUsersCollection().document(uid).set(userToCreate);
    }
    // =============================================================================================
    // --- READ ---
    public static Task<DocumentSnapshot> getUser(String uid) {

        return UserHelper.getUsersCollection().document(uid).get();
    }

    public static Task<QuerySnapshot> getConnectedUsers() {

        return  UserHelper.getUsersCollection().whereEqualTo("isConnected","true").get();

    }

    // =============================================================================================
    // --- UPDATE ---
    public static Task<Void> updateUsername(String username, String uid) {

        return UserHelper.getUsersCollection().document(uid).update("username", username);
    }

    public static Task<Void> updateFirstName(String firstName, String uid) {

        return UserHelper.getUsersCollection().document(uid).update("firstName", firstName);
    }

    public static Task<Void> updateLastName(String lastName, String uid) {

        return UserHelper.getUsersCollection().document(uid).update("lastName", lastName);
    }

    public static Task<Void> updateIsConnected(String isConnected, String uid) {

        return UserHelper.getUsersCollection().document(uid).update("isConnected", isConnected);
    }

    public static Task<Void> updateToken(String token, String uid) {

        return UserHelper.getUsersCollection().document(uid).update("token", token);
    }
    // =============================================================================================
    // --- DELETE ---
    public static Task<Void> deleteUser(String uid) {

        return UserHelper.getUsersCollection().document(uid).delete();
    }
}