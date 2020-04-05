package oldFiles

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.iid.FirebaseInstanceId
import com.google.firebase.iid.InstanceIdResult
import com.rpifinal.hitema.R
//import butterknife.ButterKnife

/*
    BASE ACTIVITY

    Chaque nouvelle activité doit hériter de cette classe à la place de AppCompatActivity
    Elle permettra la réutilisation de code souvent utilisée au sein du projet et
    hérite elle-même de AppCompatActivity.
*/

abstract class BaseActivity : AppCompatActivity() {
    protected var TOKEN: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.setContentView(fragmentLayout)
        //ButterKnife.bind(this) //Configure Butterknife
        instanceToken
    }

    /* Méthode abstraite :
       A surcharger dans les classes filles afin de récupérer
       la vue de chaque activité */
    abstract val fragmentLayout: Int

    // =============================================================================================
// Récupère l'utilisateur actuellement connecté
    protected val currentUser: FirebaseUser?
        get() = FirebaseAuth.getInstance().currentUser

    // Vérifie que l'utilisateur est bien connecté
    protected val isCurrentUserLogged: Boolean
        get() = currentUser != null

    // Méthode qui retournera un message en cas d'erreur d'un traitement
    protected fun onFailureListener(): OnFailureListener {
        return OnFailureListener { e: Exception? -> Toast.makeText(applicationContext, getString(R.string.error_unknown_error), Toast.LENGTH_LONG).show() }
    }

    // Get new Instance ID token
    private val instanceToken: Task<InstanceIdResult>
        get() = FirebaseInstanceId.getInstance().instanceId.addOnCompleteListener { task: Task<InstanceIdResult> ->
            if (!task.isSuccessful) {
                Log.w("Token", "getInstanceId failed", task.exception)
                return@addOnCompleteListener
            }
            // Get new Instance ID token
            TOKEN = task.result!!.token
        }
}