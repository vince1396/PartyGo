package com.rpifinal.hitema.controller;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.rpifinal.hitema.R;
import butterknife.ButterKnife;

/*
    BASE ACTIVITY

    Chaque nouvelle activité doit hériter de cette classe à la place de AppCompatActivity
    Elle permettra la réutilisation de code souvent utilisée au sein du projet et
    hérite elle-même de AppCompatActivity.
*/

public abstract class BaseActivity extends AppCompatActivity {

    protected String TOKEN;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        this.setContentView(this.getFragmentLayout());
        ButterKnife.bind(this); //Configure Butterknife
        getInstanceToken();
    }

    /* Méthode abstraite :
       A surcharger dans les classes filles afin de récupérer
       la vue de chaque activité */
    public abstract int getFragmentLayout();

    // =============================================================================================

    // Récupère l'utilisateur actuellement connecté
    @Nullable
    protected FirebaseUser getCurrentUser() {

        return FirebaseAuth.getInstance().getCurrentUser();
    }

    // Vérifie que l'utilisateur est bien connecté
    protected Boolean isCurrentUserLogged() {
        return (this.getCurrentUser() != null);
    }

    // Méthode qui retournera un message en cas d'erreur d'un traitement
    protected OnFailureListener onFailureListener() {

        return e -> Toast.makeText(getApplicationContext(), getString(R.string.error_unknown_error), Toast.LENGTH_LONG).show();
    }

    protected Task<InstanceIdResult> getInstanceToken() {

       return FirebaseInstanceId.getInstance().getInstanceId().addOnCompleteListener(task -> {

                    if (!task.isSuccessful())
                    {
                        Log.w("Token", "getInstanceId failed", task.getException());
                        return;
                    }

                    // Get new Instance ID token
                    this.TOKEN = task.getResult().getToken();
                });
    }
}