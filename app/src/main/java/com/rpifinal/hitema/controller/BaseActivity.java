package com.rpifinal.hitema.controller;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.rpifinal.hitema.R;

import butterknife.ButterKnife;

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        this.setContentView(this.getFragmentLayout());
        ButterKnife.bind(this); //Configure Butterknife
    }

    public abstract int getFragmentLayout();

    // =============================================================================================
    @Nullable
    protected FirebaseUser getCurrentUser(){
        return FirebaseAuth.getInstance().getCurrentUser();
    }

    protected Boolean isCurrentUserLogged(){
        return (this.getCurrentUser() != null);
    }

    protected OnFailureListener onFailureListener(){
        return new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(), getString(R.string.error_unknown_error), Toast.LENGTH_LONG).show();
            }
        };
    }

}