package com.rpifinal.hitema.controller;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnSuccessListener;
import com.rpifinal.hitema.R;

import butterknife.BindView;
import butterknife.OnClick;

public class ProfileActivity extends BaseActivity {

    //FOR DATA
    //Identify each Http Request
    private static final int SIGN_OUT_TASK = 10;
    private static final int DELETE_USER_TASK = 20;

    @BindView(R.id.profile_activity_view_picture) ImageView imageViewProfile;
    @BindView(R.id.profile_activity_view_name) TextView textViewName;
    @BindView(R.id.profile_activity_view_email) TextView textViewEmail;
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
            AuthUI.getInstance()
                    .delete(this)
                    .addOnSuccessListener(this, this.updateUIAfterRESTRequestsCompleted(DELETE_USER_TASK));
        }
    }


    //Update UI when activity is creating
    private void updateUIWhenCreating(){

        if (this.getCurrentUser() != null)
        {
            //Get picture URL from Firebase
            if (this.getCurrentUser().getPhotoUrl() != null)
            {
                Glide.with(this)
                    .load(this.getCurrentUser().getPhotoUrl())
                    .apply(RequestOptions.circleCropTransform())
                    .into(imageViewProfile);
            }
            //Get email & username from Firebase
            String email = TextUtils.isEmpty(this.getCurrentUser().getEmail()) ? getString(R.string.info_no_email_found) : this.getCurrentUser().getEmail();
            String username = TextUtils.isEmpty(this.getCurrentUser().getDisplayName()) ? getString(R.string.info_no_username_found) : this.getCurrentUser().getDisplayName();

            //Update views with data
            this.textViewName.setText(username);
            this.textViewEmail.setText(email);
        }
    }

    //Create OnCompleteListener called after tasks ended
    private OnSuccessListener<Void> updateUIAfterRESTRequestsCompleted(final int origin){

        return new OnSuccessListener<Void>()
        {
            @Override
            public void onSuccess(Void aVoid) {

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