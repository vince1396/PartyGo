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
    private static final int RC_SIGN_IN = 123;

    // Binding view
    @BindView(R.id.main_activity_coordinator_layout) CoordinatorLayout coordinatorLayout;
    @BindView(R.id.main_activity_button_login) Button mLoginButton;
    @BindView(R.id.main_activity_button_signin) Button mSignInButton;

    // =============================================================================================
    // Initialize layout
    @Override
    public int getFragmentLayout() { return R.layout.activity_main; }

    // =============================================================================================
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        this.handleResponseAfterSignIn(requestCode, resultCode, data);
    }

    // =============================================================================================
    private void showSnackBar(CoordinatorLayout coordinatorLayout, String message) {

        Snackbar.make(coordinatorLayout, message, Snackbar.LENGTH_SHORT).show();
    }

    // --------------------
    // ACTIONS
    // --------------------

    @OnClick(R.id.main_activity_button_login)
    public void onClickLoginButton() {

        this.startSignInActivity();
    }

    @OnClick(R.id.main_activity_button_signin)
    public void onClickSignInButton() {
        Intent i = new Intent(MainActivity.this, UnityPlayerActivity.class);
        startActivity(i);
    }

    // --------------------
    // NAVIGATION
    // --------------------

    // 2 - Launch Sign-In Activity
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

        if(requestCode == RC_SIGN_IN)
        {
            if(resultCode == RESULT_OK) //SUCCESS
            {
                this.
                showSnackBar(this.coordinatorLayout, getString(R.string.connection_succeed));
                Intent profile = new Intent(MainActivity.this, ProfileActivity.class);
                startActivity(profile);
            }
            else //ERROR
            {
                if(response == null)
                {
                    showSnackBar(this.coordinatorLayout, getString(R.string.error_authentication_canceled));
                }
                else if(response.getError().getErrorCode() == ErrorCodes.NO_NETWORK)
                {
                    showSnackBar(this.coordinatorLayout, getString(R.string.error_no_internet));
                }
                else if(response.getError().getErrorCode() == ErrorCodes.UNKNOWN_ERROR)
                {
                    showSnackBar(this.coordinatorLayout, getString(R.string.error_unknown_error));
                }
            }
        }
    }
}