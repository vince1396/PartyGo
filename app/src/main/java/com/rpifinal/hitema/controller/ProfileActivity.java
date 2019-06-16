package com.rpifinal.hitema.controller;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.SACGGames.PartyGoMiniGames.UnityPlayerActivity;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnSuccessListener;
import com.rpifinal.hitema.R;
import com.rpifinal.hitema.model.User;

import api.UserHelper;
import butterknife.BindView;
import butterknife.OnClick;

public class ProfileActivity extends BaseActivity {

    // =============================================================================================
    // Code pour chaque requête HTTP
    private static final int SIGN_OUT_TASK = 10;
    private static final int DELETE_USER_TASK = 20;
    private static final int UPDATE_USERNAME = 30;

    private User mUser;

    // Récupération des éléments de la vue au sein du code Java
    @BindView(R.id.profile_activity_view_picture) ImageView mImageViewProfile;
    @BindView(R.id.profile_activity_view_name) TextView mTextViewName;
    @BindView(R.id.profile_activity_view_email) TextView mTextViewEmail;
    // =============================================================================================

    // =============================================================================================
    // Récupération de la vue correspondante à l'activité
    @Override
    public int getFragmentLayout() { return R.layout.activity_profile; }

    @Override
        protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        // A la création de l'activité on met à jour la vue
        this.updateUIWhenCreating();
        this.getObjectCurrentUser();
    }
    // =============================================================================================

    // =============================================================================================
    // ACTIONS

    // Quand l'utilisateur clique sur déconnexion
    @OnClick(R.id.profile_activity_logout_button)
    public void onClickLogoutButton() {

        this.signOutUserFromFirebase();
    }

    // Quand l'utilisateur clique sur suppression du compte
    @OnClick(R.id.profile_activity_delete_button)
    public void onClickDeleteButton() {

        AlertDialog.Builder builder = new AlertDialog.Builder(ProfileActivity.this);
        builder.setCancelable(true);
        builder.setTitle("Confirmation");
        builder.setMessage("Voulez vous vraiment supprimer votre compte ?");

        //Si la reponse est non
        builder.setNegativeButton("NON", (dialog, whichButton) -> dialog.cancel());
        //si la reponse est oui
        builder.setPositiveButton("OUI", (dialog, whichButton) -> deleteUserFromFirebase());

        builder.show();

    }

    // Quand l'utilisateur clique sur Modifier informations
    @OnClick(R.id.profile_activity_update_button)
    public void onClickUpdateButton() {

        Intent update = new Intent(ProfileActivity.this, UpdateUserActivity.class);
        startActivity(update);
    }

    @OnClick(R.id.profile_activity_game1)
    public void onClickGame1() {

        Intent intentGame1 = new Intent(ProfileActivity.this, UnityPlayerActivity.class);
        intentGame1.putExtra("miniGame", "MiniGame1");
        intentGame1.putExtra("randomRoom", "1001");

        if(this.getCurrentUser() != null)
        {
            String username = this.mUser.getUsername();
            intentGame1.putExtra("username", username);
        }
        startActivity(intentGame1);
    }

    @OnClick(R.id.profile_activity_game2)
    public void onClickGame2() {

        Intent intentGame2 = new Intent(ProfileActivity.this, UnityPlayerActivity.class);
        intentGame2.putExtra("miniGame", "MiniGame2");
        intentGame2.putExtra("randomRoom", "2002");

        if(this.getCurrentUser() != null)
        {
            String username = this.mUser.getUsername();
            intentGame2.putExtra("username", username);
        }
        intentGame2.putExtra("username", "username");
        startActivity(intentGame2);
    }

    @OnClick(R.id.profile_activity_game3)
    public void onClickGame3() {

        Intent intentGame3 = new Intent(ProfileActivity.this, UnityPlayerActivity.class);
        intentGame3.putExtra("miniGame", "MiniGame3");
        intentGame3.putExtra("randomRoom", "333s3");

        if(this.getCurrentUser() != null)
        {
            String username = this.mUser.getUsername();
            intentGame3.putExtra("username", username);
        }
        intentGame3.putExtra("username", "username");
        startActivity(intentGame3);
    }
    // =============================================================================================

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
            String email = TextUtils.isEmpty(
                    this.getCurrentUser()
                            .getEmail()) ? getString(R.string.info_no_email_found) : this.getCurrentUser().getEmail();
            String name = this.getCurrentUser().getDisplayName();

            // Insertion de l'email dans la vue
            this.mTextViewEmail.setText(email);
            this.mTextViewName.setText(name);
        }
    }
    // =============================================================================================

    // =============================================================================================
    // --------------------
    // REST REQUESTS
    // --------------------

    protected void getObjectCurrentUser() {

        UserHelper.getUser(getCurrentUser().getUid()).addOnSuccessListener(documentSnapshot ->
                this.mUser = documentSnapshot.toObject(User.class));
    }

    // Méthode de déconnexion (FirebaseAuth)
    private void signOutUserFromFirebase(){

        AuthUI.getInstance()
                .signOut(this)
                .addOnSuccessListener(this, this.updateUIAfterRESTRequestsCompleted(SIGN_OUT_TASK));
    }

    // Méthode de suppression de compte (FirebaseAuth)
    private void deleteUserFromFirebase(){

        if (this.getCurrentUser() != null) {

            UserHelper.deleteUser(this.getCurrentUser()
                        .getUid())
                        .addOnFailureListener(this.onFailureListener());

            AuthUI.getInstance()
                    .delete(this)
                    .addOnSuccessListener(this, this.updateUIAfterRESTRequestsCompleted(DELETE_USER_TASK));
        }
    }

    // Cette méthode est appelée à la fin de la déconnexion ou de la suppression pour terminer l'activité
    private OnSuccessListener<Void> updateUIAfterRESTRequestsCompleted(final int origin) {

        return aVoid -> {
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
        };
    }
    // =============================================================================================
}