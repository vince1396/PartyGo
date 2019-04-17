package com.rpifinal.hitema.controller;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnSuccessListener;
import com.rpifinal.hitema.R;

import api.UserHelper;
import butterknife.BindView;
import butterknife.OnClick;

public class ProfileActivity extends BaseActivity {

    // Code pour chaque requête HTTP
    private static final int SIGN_OUT_TASK = 10;
    private static final int DELETE_USER_TASK = 20;
    private static final int UPDATE_USERNAME = 30;

    // Récupération des éléments de la vue au sein du code Java
    @BindView(R.id.profile_activity_view_picture) ImageView mImageViewProfile;
    @BindView(R.id.profile_activity_view_name) TextView mTextViewName;
    @BindView(R.id.profile_activity_view_email) TextView mTextViewEmail;

    // =============================================================================================

    // Récupération de la vue correspondante à l'activité
    @Override
    public int getFragmentLayout() { return R.layout.activity_profile; }

    @Override
        protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        // A la création de l'activité on met à jour la vue
        this.updateUIWhenCreating();
    }

    // =============================================================================================

    // Quand l'utilisateur clique sur déconnexion
    @OnClick(R.id.profile_activity_logout_button)
    public void onClickLogoutButton() {

        this.signOutUserFromFirebase();
    }

    // Quand l'utilisateur clique sur suppression du compte
    @OnClick(R.id.profile_activity_delete_button)
    public void onClickDeleteButton() {

        this.deleteUserFromFirebase();
    }

    // Quand l'utilisateur clique sur Modifier informations
    @OnClick
    public void onClickUpdateButton() {

        Intent update = new Intent(ProfileActivity.this, UpdateUserActivity.class);
        startActivity(update);
    }

    // =============================================================================================

    // Méthode mettant à jour la vue (Appelée à la création de l'activité)
    private void updateUIWhenCreating(){

        // Vérification que l'utilisateur actuel n'est pas vide
        if (this.getCurrentUser() != null)
        {
            // Si une l'utilisateur possède une photo
            if (this.getCurrentUser().getPhotoUrl() != null)
            {
                // Utilisation de Glide pour intégrer la photo dans la vue
                Glide.with(this)
                        .load(this.getCurrentUser().getPhotoUrl())
                        .apply(RequestOptions.circleCropTransform())
                        .into(mImageViewProfile);
            }

            // Récupération de l'email de l'utilisateur en vérifiant qu'il n'est pas NULL
            String email = TextUtils.isEmpty(this.getCurrentUser().getEmail()) ? getString(R.string.info_no_email_found) : this.getCurrentUser().getEmail();

            // Insertion de l'email dans la vue
            this.mTextViewEmail.setText(email);
        }
    }

    // --------------------
    // REST REQUESTS
    // --------------------

    // Méthode de déconnexion (FirebaseAuth)
    private void signOutUserFromFirebase(){

        AuthUI.getInstance()
                .signOut(this)
                .addOnSuccessListener(this, this.updateUIAfterRESTRequestsCompleted(SIGN_OUT_TASK));
    }

    // Méthode de suppression de compte (FirebaseAuth)
    private void deleteUserFromFirebase(){

        if (this.getCurrentUser() != null) {

            UserHelper.deleteUser(this.getCurrentUser().getUid()).addOnFailureListener(this.onFailureListener());

            AuthUI.getInstance()
                    .delete(this)
                    .addOnSuccessListener(this, this.updateUIAfterRESTRequestsCompleted(DELETE_USER_TASK));
        }
    }

    // Cette méthode est appelée à la fin de la déconnexion ou de la suppression pour terminer l'activité
    private OnSuccessListener<Void> updateUIAfterRESTRequestsCompleted(final int origin) {

        return new OnSuccessListener<Void>()
        {
            @Override
            public void onSuccess(Void aVoid)
            {
                switch (origin){
                    case SIGN_OUT_TASK:
                        finish();
                        break;
                    case DELETE_USER_TASK:
                        finish();
                        break;
                    default:
                        break;
                }
            }
        };
    }
}