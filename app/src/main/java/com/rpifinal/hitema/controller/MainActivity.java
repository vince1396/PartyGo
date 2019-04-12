package com.rpifinal.hitema.controller;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.widget.Button;

import com.Company.DemoPhoton.UnityPlayerActivity;
import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.ErrorCodes;
import com.firebase.ui.auth.IdpResponse;
import com.rpifinal.hitema.R;

import java.util.Arrays;

import butterknife.BindView;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    // =============================================================================================
    // Code de vérification de connexion
    private static final int RC_SIGN_IN = 123;

    // Récupération des éléments de la vue au sein du code Java
    @BindView(R.id.main_activity_coordinator_layout) CoordinatorLayout coordinatorLayout;
    @BindView(R.id.main_activity_button_login) Button mLoginButton;
    @BindView(R.id.main_activity_button_signin) Button mSignInButton;

    // =============================================================================================
    // Récupération de la vue correspondante à l'acitivité
    @Override
    public int getFragmentLayout() { return R.layout.activity_main; }

    // =============================================================================================
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

    // Démarrage de la démo Unity
    @OnClick(R.id.main_activity_button_signin)
    public void onClickSignInButton() {

        Intent i = new Intent(MainActivity.this, UnityPlayerActivity.class);
        startActivity(i);
    }

    // --------------------
    // NAVIGATION
    // --------------------

    // Méthode d'inscription/connexion à l'aide de FireBaseUI
    public void startSignInActivity(){

        startActivityForResult(
                AuthUI.getInstance()
                        .createSignInIntentBuilder()
                        .setTheme(R.style.LoginTheme)
                        .setAvailableProviders(
                                Arrays.asList(new AuthUI.IdpConfig.EmailBuilder().build(),
                                              new AuthUI.IdpConfig.GoogleBuilder().build()
                                ))
                        .setLogo(R.drawable.ic_logo_auth)
                        .build(),
                RC_SIGN_IN);
    }

    private void handleResponseAfterSignIn(int requestCode, int resultCode, Intent data) {

        IdpResponse response = IdpResponse.fromResultIntent(data);

        // Vérification du code
        if(requestCode == RC_SIGN_IN)
        {
            // Si la connexion s'est bien passé
            if(resultCode == RESULT_OK) //SUCCESS
            {
                // Affichage d'une SnackBar
                this.showSnackBar(this.coordinatorLayout, getString(R.string.connection_succeed));
                //Intent profile = new Intent(MainActivity.this, ProfileActivity.class);
                Intent maps = new Intent(MainActivity.this, MapsActivity.class);
                maps.putExtra("latitute", 48.825913);
                maps.putExtra("longitude", 2.267375);
                startActivity(maps);
                // Démarrage de MapsActivity
            }
            else // En cas d'erreur
            {
                // Si l'utilisateur a annulé la connexion
                if(response == null)
                {
                    showSnackBar(this.coordinatorLayout, getString(R.string.error_authentication_canceled));
                }
                // Si internet n'est pas disponible<
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
}