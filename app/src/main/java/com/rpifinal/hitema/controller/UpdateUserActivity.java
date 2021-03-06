package com.rpifinal.hitema.controller;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.rpifinal.hitema.R;
import com.rpifinal.hitema.model.User;
import api.UserHelper;
import butterknife.BindView;
import butterknife.OnClick;

//TODO : Optimisation REGEX

public class UpdateUserActivity extends BaseActivity {

    // =============================================================================================
    // ATTRIBUTS MEMBRES

    private static final String TAG = "UpdateUserActivity";

    // Expressions régulières
    private static final String REGEX_FL_NAME = "[a-zA-Z-]+[:blank]?[a-zA-Z]+";
    private static final String REGEX_USERNAME = "[a-zA-Z0-9_]+";

    // CODE REST
    private static final int UPDATE_USERNAME  = 10;
    private static final int UPDATE_FIRSTNAME = 20;
    private static final int UPDATE_LASTNAME  = 30;

    // Binding UI
    @BindView(R.id.update_activity_title_textView)   TextView mTitleUpdateTextView;
    @BindView(R.id.update_activity_username_field)   EditText mUsernameUpdateField;
    @BindView(R.id.update_activity_firstName_field)  EditText mFirstnameUpdateField;
    @BindView(R.id.update_activity_lastName_field)   EditText mLastnameUpdateField;
    @BindView(R.id.update_activity_username_submit)  Button mUsernameButton;
    @BindView(R.id.update_activity_firstName_submit) Button mFirstnameButton;
    @BindView(R.id.update_activity_lastName_submit)  Button mLastnameButton;
    // =============================================================================================

    // =============================================================================================
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        this.updateUI();
    }

    @Override
    public int getFragmentLayout() {

        return R.layout.activity_update_user;
    }
    // =============================================================================================

    // =============================================================================================
    // ACTIONS

    // TODO : Regrouper onCLick

    //Boutton de retour de la page update vers la page profil
    @OnClick(R.id.back_to_profile)
    public void onClickReturnProfilButton() {

        Intent profile = new Intent(this, ProfileActivity.class);
        startActivity(profile);
    }

    @OnClick({R.id.update_activity_username_submit, R.id.update_activity_firstName_submit, R.id.update_activity_lastName_submit})
    public void onClickUpdate(View view) {
        String uid = getCurrentUser().getUid();
        switch(view.getId())
        {
            case R.id.update_activity_username_submit:
                String username = mUsernameUpdateField.getText().toString();
                checkDataEntry(UPDATE_USERNAME,username,uid);
                break;

            case R.id.update_activity_firstName_submit:
                String firstName = mFirstnameUpdateField.getText().toString();
                checkDataEntry(UPDATE_FIRSTNAME,firstName,uid);

                break;

            case R.id.update_activity_lastName_submit:
                String lastName = mLastnameUpdateField.getText().toString();
                checkDataEntry(UPDATE_LASTNAME,lastName,uid);
                break;
        }
        //Hide keyboard when click on the update button
        closeKeyboard();
    }

    //Method closeKeyboard() that will close/hide the keyboard when click on the valid update button
    private void closeKeyboard() {

        View view = this.getCurrentFocus();
        if (view != null)
        {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    //Hide keyboard when click outside the screen
    /*
    public void hideKeyboard(View view) {
        InputMethodManager inn = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
        inn.hideSoftInputFromWindow(view.getWindowToken(),0);
    }
    */

    // =============================================================================================
    // UI/UX

    public void checkDataEntry(int code,String data,String uid) {

        final boolean namesCondition = data.matches(REGEX_FL_NAME) && !data.equals("") && data.length() > 3;

        switch (code)
        {
                // =================================================================================
                case UPDATE_LASTNAME:

                    if (namesCondition)
                    {
                        String successMessage = getString(R.string.success_update_lastname);
                        UserHelper.updateLastName(data, uid).addOnFailureListener(this.onFailureListener())
                                .addOnSuccessListener(this.onSuccessListener(successMessage));
                    }
                    else
                    {
                        String errorMessage = getString(R.string.error_update_lastname);
                        Toast.makeText(getApplicationContext(), errorMessage, Toast.LENGTH_SHORT).show();
                    }
                    break;
                // =================================================================================
                case UPDATE_FIRSTNAME:

                    if (namesCondition)
                    {
                        String successMessage = getString(R.string.success_update_firstname);
                        UserHelper.updateFirstName(data, uid).addOnFailureListener(this.onFailureListener())
                                .addOnSuccessListener(this.onSuccessListener(successMessage));
                    }
                    else
                    {
                        String errorMessage=getString(R.string.error_update_firstname);
                        Toast.makeText(getApplicationContext(), errorMessage, Toast.LENGTH_SHORT).show();
                    }
                    break;
                // =================================================================================
                case UPDATE_USERNAME:

                    if (data.matches(REGEX_USERNAME) && !data.equals("") && data.length()> 3)
                    {
                        String successMessage = getString(R.string.success_update_username);
                        UserHelper.updateUsername(data, uid).addOnFailureListener(this.onFailureListener()).
                                addOnSuccessListener(this.onSuccessListener(successMessage));
                    }
                    else
                    {
                        String errorMessage=getString(R.string.error_update_username);
                        Toast.makeText(getApplicationContext(), errorMessage, Toast.LENGTH_SHORT).show();
                    }
                    break;
                // =================================================================================
        }
            updateUI();
    }

    public void updateUI() {

        if(this.getCurrentUser() != null)
        {
            UserHelper.getUser(this.getCurrentUser().getUid()).addOnSuccessListener(documentSnapshot -> {

                User currentUser = documentSnapshot.toObject(User.class);

                if(!currentUser.getUsername().isEmpty())
                {
                    this.mUsernameUpdateField.setText(currentUser.getUsername());
                }

                if(!currentUser.getFirstName().isEmpty())
                {
                    this.mFirstnameUpdateField.setText(currentUser.getFirstName());
                }

                if(!currentUser.getLastName().isEmpty())
                {
                    this.mLastnameUpdateField.setText(currentUser.getLastName());
                }
            });
        }

        if(this.getCurrentUser() == null)
        {
            this.mTitleUpdateTextView.setText(getString(R.string.no_user_connected_error));
        }
    }

    public void makeToast() {

        Toast.makeText(getBaseContext(), "OKAY", Toast.LENGTH_SHORT).show();
    }

    private OnSuccessListener onSuccessListener(String resId) {

        return e-> Toast.makeText(getApplicationContext(), resId, Toast.LENGTH_SHORT).show();
    }
    // =============================================================================================
}