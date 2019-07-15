package com.rpifinal.hitema.controller;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import com.crashlytics.android.Crashlytics;
import com.google.android.material.snackbar.Snackbar;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.ErrorCodes;
import com.firebase.ui.auth.IdpResponse;
import com.rpifinal.hitema.R;
import com.rpifinal.hitema.interfaces.UsernameCallback;

import java.util.Arrays;

import api.UserHelper;
import butterknife.BindView;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    // =============================================================================================
    // Code de vérification de connexion
    private static final int RC_SIGN_IN = 123;

    // Récupération des éléments de la vue au sein du code Java
    @BindView(R.id.main_activity_coordinator_layout) CoordinatorLayout coordinatorLayout;

    // =============================================================================================
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    // Récupération de la vue correspondante à l'acitivité
    @Override
    public int getFragmentLayout() {
        return R.layout.activity_main;
    }

    // Traitement après l'inscription/connexion de l'utilisateur
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        this.handleResponseAfterSignIn(requestCode, resultCode, data);
    }
    // =============================================================================================
    //Affichage d'une snackBar
    private void showSnackBar(CoordinatorLayout coordinatorLayout, String message) {

        Snackbar.make(coordinatorLayout, message, Snackbar.LENGTH_SHORT).show();
    }

    // --------------------
    // ACTIONS
    // --------------------

    // Quand l'utilisateur clique sur le bouton connexion
    @OnClick(R.id.main_activity_button_login)
    public void onClickLoginButton() {

        // Démarrage de l'acitivité de connexion (FirebaseAuth)
        this.startSignInActivity();
    }

    @OnClick(R.id.main_activity_button_crash)
    public void onClickCrashButton() {

        Crashlytics.getInstance().crash();
    }
    // =============================================================================================
    /*
        Création de l'utilisateur dans Firestore en récupérant
        les informations depuis FireAuth
     */
    private void createUserInFirestore() {

        if (this.getCurrentUser() != null)
        {
            String uid        = this.getCurrentUser().getUid();
            String email      = this.getCurrentUser().getEmail();
            String username   = this.getCurrentUser().getDisplayName();
            String firstName  = splitUsername(this.getCurrentUser().getDisplayName())[0];
            String lastName   = splitUsername(this.getCurrentUser().getDisplayName())[1];
            String urlPicture = (this.getCurrentUser().getPhotoUrl() != null) ?
                                this.getCurrentUser().getPhotoUrl().toString() : null;
            int lvl = 1;
            int xp  = 0;
            String isConnected = "true";

            UserHelper.createUser(uid, email, username, firstName, lastName, urlPicture, lvl, xp, isConnected)
                    .addOnFailureListener(this.onFailureListener());
        }
    }

    // On coupe la chaine de caractères provenant de la méthode getDisplayName() afin de récupérer
    // le nom et le prénom séparément
    public String[] splitUsername(String username) {

        return username.split("\\s", 2);
    }

    // Méthode d'inscription/connexion à l'aide de FireBaseUI
    public void startSignInActivity() {

        startActivityForResult(
                AuthUI.getInstance()
                        .createSignInIntentBuilder()
                        .setTheme(R.style.LoginTheme)
                        .setAvailableProviders(
                                Arrays.asList(new AuthUI.IdpConfig.EmailBuilder().build(),
                                              new AuthUI.IdpConfig.GoogleBuilder().build()
                                ))
                        .setLogo(R.drawable.party_go_title_logo)
                        .build(),
                RC_SIGN_IN);
    }

    // Gestion du résultat de la connexion
    private void handleResponseAfterSignIn(int requestCode, int resultCode, Intent data) {

        IdpResponse response = IdpResponse.fromResultIntent(data);

        // Vérification du code
        if(requestCode == RC_SIGN_IN)
        {
            // Si la connexion s'est bien passé
            if(resultCode == RESULT_OK) //SUCCESS
            {
                checkIfUserExists(userExists -> {

                    if(!userExists)
                    {
                        // Création de l'utilisateur en BDD
                        this.createUserInFirestore();
                    }

                });

                // Affichage d'une SnackBar
                UserHelper.updateIsConnected("true", getCurrentUser().getUid());
                this.showSnackBar(this.coordinatorLayout, getString(R.string.connection_succeed));
                Intent profile = new Intent(MainActivity.this, ProfileActivity.class);
                Intent map = new Intent(MainActivity.this, MapsActivity.class);
                startActivity(profile);
            }
            else // En cas d'erreur
            {
                // Si l'utilisateur a annulé la connexion
                if(response == null)
                {
                    showSnackBar(this.coordinatorLayout, getString(R.string.error_authentication_canceled));
                }
                // Si internet n'est pas disponible
                else if(response.getError().getErrorCode() == ErrorCodes.NO_NETWORK)
                {
                    showSnackBar(this.coordinatorLayout, getString(R.string.error_no_internet));
                }
                // En cas d'erreur inconnue
                else if(response.getError().getErrorCode() == ErrorCodes.UNKNOWN_ERROR)
                {
                    showSnackBar(this.coordinatorLayout, getString(R.string.error_unknown_error));
                }
            }
        }
    }

    // Vérification que l'utilisateur n'existe pas déja dans la BDD afin de ne pas écraser
    // un document déja existant
    private void checkIfUserExists(UsernameCallback usernameCallback) {

        UserHelper.getUser(getCurrentUser().getUid()).addOnSuccessListener(documentSnapshot -> {

            boolean userExists = false;

            if(documentSnapshot.exists())
                userExists = true;

            usernameCallback.onCallback(userExists);
        });
    }
    // =============================================================================================
}