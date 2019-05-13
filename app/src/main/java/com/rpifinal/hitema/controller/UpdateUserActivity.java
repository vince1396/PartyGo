package com.rpifinal.hitema.controller;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.rpifinal.hitema.R;
import com.rpifinal.hitema.model.User;
import api.UserHelper;
import butterknife.BindView;
import butterknife.OnClick;

public class UpdateUserActivity extends BaseActivity {

    //TODO : TextWatcher, addOnTextChangeListener

    // =============================================================================================
    // ATTRIBUTS MEMBRES
    private static final String TAG = "UpdateUserActivity";

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
        String username = mUsernameUpdateField.getText().toString();

        UserHelper.updateUsername(username, uid).addOnFailureListener(this.onFailureListener());
    }

    @OnClick(R.id.update_activity_firstName_submit)
    public void onClickFirstNameButton() {

        String uid = getCurrentUser().getUid();
        String username = mFirstnameUpdateField.getText().toString();

        UserHelper.updateFirstName(username, uid).addOnFailureListener(this,onFailureListener());
    }

    @OnClick(R.id.update_activity_lastName_submit)
    public void onClickLastNameButton() {

        String uid = getCurrentUser().getUid();
        String username = mLastnameUpdateField.getText().toString();

        UserHelper.updateLastName(username, uid).addOnFailureListener(this.onFailureListener());
    }
    // =============================================================================================
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
                        this.mLastnameUpdateField};

                int i = 0;
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