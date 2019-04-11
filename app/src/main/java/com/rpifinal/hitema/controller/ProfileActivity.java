package com.rpifinal.hitema.controller;

import android.app.Notification;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
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

    //FOR DATA
    //Identify each Http Request
    private static final int SIGN_OUT_TASK = 10;
    private static final int DELETE_USER_TASK = 20;
    private static final int UPDATE_USERNAME = 30;

    @BindView(R.id.profile_activity_view_picture) ImageView mImageViewProfile;
    @BindView(R.id.profile_activity_view_name) TextView mTextViewName;
    @BindView(R.id.profile_activity_view_email) TextView mTextViewEmail;
    @BindView(R.id.profile_activity_button_update) TextView mButtonUpdate;

    private Notification.MessagingStyle.Message textInputEditTextUsername;


    // =============================================================================================
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

    @OnClick(R.id.profile_activity_delete_button)
    public void onClickDeleteButton() {

        this.deleteUserFromFirebase();
    }

    @OnClick
    public void onClickUpdateButton() {

        Intent update = new Intent(ProfileActivity.this, UpdateUserActivity.class);
        startActivity(update);
    }

    // =============================================================================================

    // --------------------
    // REST REQUESTS
    // --------------------

    // User sign out method
    private void signOutUserFromFirebase(){

        AuthUI.getInstance()
                .signOut(this)
                .addOnSuccessListener(this, this.updateUIAfterRESTRequestsCompleted(SIGN_OUT_TASK));
    }

    // User deletion method
    private void deleteUserFromFirebase(){

        if (this.getCurrentUser() != null) {

            UserHelper.deleteUser(this.getCurrentUser().getUid()).addOnFailureListener(this.onFailureListener());

            AuthUI.getInstance()
                    .delete(this)
                    .addOnSuccessListener(this, this.updateUIAfterRESTRequestsCompleted(DELETE_USER_TASK));
        }
    }

    // Update User Username
    @RequiresApi(api = Build.VERSION_CODES.N)
    private void updateUsernameInFirebase(){

        String username = this.textInputEditTextUsername.getText().toString();

        if (this.getCurrentUser() != null)
        {
            if (!username.isEmpty() && !username.equals(getString(R.string.info_no_username_found)))
            {
                UserHelper.updateUsername(
                        username,
                        this.getCurrentUser().getUid()).addOnFailureListener(
                                this.onFailureListener()).addOnSuccessListener(
                                        this.updateUIAfterRESTRequestsCompleted(UPDATE_USERNAME));
            }
        }
    }

    // Arranging method that updating UI with Firestore data
    private void updateUIWhenCreating(){

        if (this.getCurrentUser() != null)
        {
            if (this.getCurrentUser().getPhotoUrl() != null)
            {
                Glide.with(this)
                        .load(this.getCurrentUser().getPhotoUrl())
                        .apply(RequestOptions.circleCropTransform())
                        .into(mImageViewProfile);
            }

            String email = TextUtils.isEmpty(this.getCurrentUser().getEmail()) ? getString(R.string.info_no_email_found) : this.getCurrentUser().getEmail();

            this.mTextViewEmail.setText(email);

            // Get additional data from Firestore (isMentor & Username)
            /*UserHelper.getUser(this.getCurrentUser().getUid()).addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                @Override
                public void onSuccess(DocumentSnapshot documentSnapshot) {
                    User currentUser = documentSnapshot.toObject(User.class);
                    String username = TextUtils.isEmpty(currentUser.getUsername()) ? getString(R.string.info_no_username_found) : currentUser.getUsername();

                    textViewName.setText(username);
                }
            });*/
        }
    }

    //Create OnCompleteListener called after tasks ended
    private OnSuccessListener<Void> updateUIAfterRESTRequestsCompleted(final int origin) {

        return new OnSuccessListener<Void>()
        {
            @Override
            public void onSuccess(Void aVoid)
            {
                switch (origin){
                    case UPDATE_USERNAME:
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