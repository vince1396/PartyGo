package com.rpifinal.hitema.controller;

import android.content.Context;
import android.os.Bundle;
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

public class UpdateUserActivity extends BaseActivity {

    // =============================================================================================
    // ATTRIBUTS MEMBRES
    private static final String TAG = "UpdateUserActivity";
    private static final String REGEX_LN = "[a-zA-Z-]+[:blank]?[a-zA-Z]+";
    private static final String REGEX_FN  = "[a-zA-Z-]+[:blank]?[a-zA-Z]+";

    private static final String REGEX_USERNAME = "[a-zA-Z0-9_]+";

    private static final int UPDATE_USERNAME  = 10;
    private static final int UPDATE_FIRSTNAME = 20;
    private static final int UPDATE_LASTNAME  = 30;


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
    public int getFragmentLayout() { return R.layout.activity_update_user; }
    // =============================================================================================

    // =============================================================================================
    // ACTIONS

    @OnClick(R.id.update_activity_username_submit)
    public void onClickUsernameButton() {

        String uid = getCurrentUser().getUid();
        String data = mUsernameUpdateField.getText().toString();
        checkDataEntry(UPDATE_USERNAME,data,uid);
    }

    @OnClick(R.id.update_activity_firstName_submit)
    public void onClickFirstNameButton() {

        String uid = getCurrentUser().getUid();
        String data = mFirstnameUpdateField.getText().toString();
        checkDataEntry(UPDATE_FIRSTNAME,data,uid);
    }

    @OnClick(R.id.update_activity_lastName_submit)
    public void onClickLastNameButton() {

        String uid = getCurrentUser().getUid();
        String data = mLastnameUpdateField.getText().toString();
        checkDataEntry(UPDATE_LASTNAME,data,uid);

    }


    // =============================================================================================
    public void checkDataEntry(int code,String data,String uid){
        String successMessage;
        String errorMessage;
        int duration = Toast.LENGTH_LONG;
        Context context=getApplicationContext();


        switch (code){
                case UPDATE_LASTNAME:
                    if (data.matches(REGEX_LN) && !data.equals("") && data.length()> 3)
                    {
                        successMessage = getString(R.string.success_update_lastname);
                        UserHelper.updateLastName(data, uid).addOnFailureListener(this.onFailureListener()).addOnSuccessListener(this.onSuccessListener(successMessage));
                    }
                    else
                    {
                        errorMessage=getString(R.string.error_update_lastname);
                        Toast.makeText(context, errorMessage, duration).show();
                    }
                    break;
                case UPDATE_FIRSTNAME:
                    if (data.matches(REGEX_FN) && !data.equals("") && data.length()> 3)
                    {
                        successMessage = getString(R.string.success_update_firstname);
                        UserHelper.updateFirstName(data, uid).addOnFailureListener(this.onFailureListener()).addOnSuccessListener(this.onSuccessListener(successMessage));
                    }
                    else
                    {
                        errorMessage=getString(R.string.error_update_firstname);
                        Toast.makeText(context, errorMessage, duration).show();
                    }
                    break;
                case UPDATE_USERNAME:
                    if (data.matches(REGEX_USERNAME) && !data.equals("") && data.length()> 3)
                    {
                        successMessage = getString(R.string.success_update_username);
                        UserHelper.updateUsername(data, uid).addOnFailureListener(this.onFailureListener()).addOnSuccessListener(this.onSuccessListener(successMessage));
                    }
                    else {
                        errorMessage=getString(R.string.error_update_username);
                        Toast.makeText(context, errorMessage, duration).show();
                    }
                    break;
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

                /*Button[] arrayButton = {
                        this.mUsernameButton,
                        this.mFirstnameButton,
                        this.mLastnameButton};

                EditText[] arrayEditText = {
                        this.mUsernameUpdateField,
                        this.mFirstnameUpdateField,
                        this.mLastnameUpdateField};*/

                /*int i = 0;
                for (Button b: arrayButton)
                {
                    checkButtonState(arrayEditText[i], arrayButton[i]);
                    i++;
                }*/
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

    private OnSuccessListener onSuccessListener(String resId)
    {
        return e-> Toast.makeText(getApplicationContext(), resId, Toast.LENGTH_SHORT).show();
    }
    // =============================================================================================

    /*public void checkButtonState(EditText editText, Button button)
    {
        button.setEnabled(false);
        button.setBackgroundResource(R.color.disable_button);
        
        String currentValue = editText.getText().toString();
        
        if(!editText.getText().toString().isEmpty() && !editText.getText().toString().equals(currentValue))
        {
            button.setEnabled(true);
            button.setBackgroundResource(R.color.enable_button);
        }
    }*/
}