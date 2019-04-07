package com.rpifinal.hitema.controller;

import android.app.Notification;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import  android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.rpifinal.hitema.R;

import api.UserHelper;
import butterknife.BindView;
import butterknife.OnClick;
import models.User;

public class ProfileActivity extends BaseActivity {

    //FOR DATA
    //Identify each Http Request
    private static final int SIGN_OUT_TASK = 10;
    private static final int DELETE_USER_TASK = 20;

    @BindView(R.id.profile_activity_view_picture) ImageView imageViewProfile;
    @BindView(R.id.profile_activity_view_name) TextView textViewName;
    @BindView(R.id.profile_activity_view_email) TextView textViewEmail;
    private View progressBar;
    private Notification.MessagingStyle.Message textInputEditTextUsername;
    //@BindView(R.id.profile_activity_logout_button) Button ButtonLogout;
    //@BindView(R.id.profile_activity_delete_button) Button ButtonDelete;

    @Override
    public int getFragmentLayout() { return R.layout.activity_profile; }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        this.updateUIWhenCreating();
    }

    @OnClick(R.id.profile_activity_logout_button)
    public void onClickLogoutButton() {

        this.signOutUserFromFirebase();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @OnClick(R.id.profile_activity_button_update)
    public void onClickUpdateButton() { this.updateUsernameInFirebase(); }


    @OnClick(R.id.profile_activity_delete_button)
    public void onClickDeleteButton() {

        this.deleteUserFromFirebase();
    }



    // --------------------
    // REST REQUESTS
    // --------------------
    // 1 - Create http requests (SignOut & Delete)

    private void signOutUserFromFirebase(){

        AuthUI.getInstance()
                .signOut(this)
                .addOnSuccessListener(this, this.updateUIAfterRESTRequestsCompleted(SIGN_OUT_TASK));
    }

    private void deleteUserFromFirebase(){

        if (this.getCurrentUser() != null) {
            //4 - We also delete user from firestore storage
            UserHelper.deleteUser(this.getCurrentUser().getUid()).addOnFailureListener(this.onFailureListener());

            AuthUI.getInstance()
                    .delete(this)
                    .addOnSuccessListener(this, this.updateUIAfterRESTRequestsCompleted(DELETE_USER_TASK));
        }
    }

    // 3 - Update User Username
    @RequiresApi(api = Build.VERSION_CODES.N)
    private void updateUsernameInFirebase(){

        this.progressBar.setVisibility(View.VISIBLE);
        String username = this.textInputEditTextUsername.getText().toString();

        if (this.getCurrentUser() != null){
            if (!username.isEmpty() &&  !username.equals(getString(R.string.info_no_username_found))){
                UserHelper.updateUsername(username, this.getCurrentUser().getUid()).addOnFailureListener(this.onFailureListener()).addOnSuccessListener(this.updateUIAfterRESTRequestsCompleted(UPDATE_USERNAME));
            }
        }
    }
    // 6 - Arranging method that updating UI with Firestore data
    private void updateUIWhenCreating(){

        if (this.getCurrentUser() != null){

            if (this.getCurrentUser().getPhotoUrl() != null) {
                Glide.with(this)
                        .load(this.getCurrentUser().getPhotoUrl())
                        .apply(RequestOptions.circleCropTransform())
                        .into(imageViewProfile);
            }

            String email = TextUtils.isEmpty(this.getCurrentUser().getEmail()) ? getString(R.string.info_no_email_found) : this.getCurrentUser().getEmail();

            this.textViewEmail.setText(email);

            // 7 - Get additional data from Firestore (isMentor & Username)
            UserHelper.getUser(this.getCurrentUser().getUid()).addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                @Override
                public void onSuccess(DocumentSnapshot documentSnapshot) {
                    User currentUser = documentSnapshot.toObject(User.class);
                    String username = TextUtils.isEmpty(currentUser.getUsername()) ? getString(R.string.info_no_username_found) : currentUser.getUsername();

                    textViewName.setText(username);
                }
            });
        }
    }


    //Create OnCompleteListener called after tasks ended
    private OnSuccessListener<Void> updateUIAfterRESTRequestsCompleted(final int origin){

        return new OnSuccessListener<Void>()
        {
            @Override
            public void onSuccess(Void aVoid) {

                switch (origin){
                    // 8 - Hiding Progress bar after request completed
                    case UPDATE_USERNAME:
                        progressBar.setVisibility(View.INVISIBLE);
                        break;
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