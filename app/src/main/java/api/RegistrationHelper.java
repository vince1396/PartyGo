package api;

import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.rpifinal.hitema.controller.BaseActivity;
import com.rpifinal.hitema.model.Registration;
import com.rpifinal.hitema.model.User;

public class RegistrationHelper {

    private static final String COLLECTION_NAME = "registration";

    // =============================================================================================
    // --- COLLECTION REFERENCE ---
    public static CollectionReference getRegistrationCollection() {

        return FirebaseFirestore.getInstance().collection(COLLECTION_NAME);
    }
    // =============================================================================================
    // --- CREATE ---
    public static Task<Void> createRegistration(String uid, String token) {

        Registration registrationToCreate = new Registration(uid, token);
        return RegistrationHelper.getRegistrationCollection().document(uid).set(registrationToCreate);
    }
    // =============================================================================================
    // --- READ ---
    public static Task<DocumentSnapshot> getRegistration(String uid) {

        return RegistrationHelper.getRegistrationCollection().document(uid).get();
    }
    // =============================================================================================
    // --- UPDATE ---
    public static Task<Void> updateRegistration(String uid, String token) {

        return RegistrationHelper.getRegistrationCollection().document(uid).update("token", token);
    }
    // =============================================================================================
    // --- DELETE ---
    public static Task<Void> deleteRegistration(String uid) {

        return UserHelper.getUsersCollection().document(uid).delete();
    }
}
