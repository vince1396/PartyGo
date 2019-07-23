package com.rpifinal.hitema.controller;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnSuccessListener;
import com.rpifinal.hitema.R;
import com.rpifinal.hitema.model.User;
import api.UserHelper;
import butterknife.OnClick;

public class MainMenu extends BaseActivity {

    private static final int SIGN_OUT_TASK = 11;
    private User mUser;

    @Override
    public int getFragmentLayout() {

        return R.layout.activity_main_menu;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
    }
    @OnClick({R.id.main_activity_btn_profil,R.id.play_text,R.id.profile_activity_logout_button})
    public void onClickMenu(View view){
        Intent activity=new Intent();
        switch (view.getId()){
            case R.id.main_activity_btn_profil:
                activity = new Intent(MainMenu.this, ProfileActivity.class);
                break;
            case R.id.play_text:
                activity = new Intent(MainMenu.this, ConnectedUsersList.class);
                break;
        }
        startActivity(activity);
    }

    @OnClick(R.id.profile_activity_logout_button)
    public void onClickLogoutButton() {
        UserHelper.updateIsConnected("false", getCurrentUser().getUid()).addOnSuccessListener(aVoid ->
                this.signOutUserFromFirebase());

    }

    // Méthode de déconnexion (FirebaseAuth)
    private void signOutUserFromFirebase() {
        AuthUI.getInstance()
                .signOut(this)
                .addOnSuccessListener(this, this.updateUIAfterRESTRequestsCompleted());
    }

    private OnSuccessListener<Void> updateUIAfterRESTRequestsCompleted() {

        return aVoid -> {
            switch (MainMenu.SIGN_OUT_TASK){
                case SIGN_OUT_TASK:
                    finish();
                    break;

                default:
                    break;
            }
        };
    }
}
