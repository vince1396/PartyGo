package oldFiles

import android.app.Activity
import android.content.Intent
import androidx.coordinatorlayout.widget.CoordinatorLayout
/*import com.rpifinal.hitema.partyGo.data.user.model.UserHelper.createUser
import com.rpifinal.hitema.partyGo.data.user.model.UserHelper.getUser
import com.rpifinal.hitema.partyGo.data.user.model.UserHelper.updateIsConnected*/
import com.crashlytics.android.Crashlytics
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.AuthUI.IdpConfig.EmailBuilder
import com.firebase.ui.auth.AuthUI.IdpConfig.GoogleBuilder
import com.firebase.ui.auth.ErrorCodes
import com.firebase.ui.auth.IdpResponse
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.firestore.DocumentSnapshot
import com.rpifinal.hitema.R
import oldFiles.interfaces.UsernameCallback

//@Suppress("UNUSED_VARIABLE")
/*
class MainActivity : BaseActivity() {
    // Récupération des éléments de la vue au sein du code Java
    @JvmField
    //@BindView(R.id.main_activity_coordinator_layout)
    var coordinatorLayout: CoordinatorLayout? = null

    // Récupération de la vue correspondante à l'acitivité
    override val fragmentLayout: Int
        get() = R.layout.activity_main

    // Traitement après l'inscription/connexion de l'utilisateur
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        handleResponseAfterSignIn(requestCode, resultCode, data)
    }

    // =============================================================================================
    //Affichage d'une snackBar
    private fun showSnackBar(coordinatorLayout: CoordinatorLayout?, message: String) {
        Snackbar.make(coordinatorLayout!!, message, Snackbar.LENGTH_SHORT).show()
    }

    // --------------------
    // ACTIONS
    // --------------------
    // Quand l'utilisateur clique sur le bouton connexion
    //@OnClick(R.id.main_activity_btn_login)
    fun onClickLoginButton() { // Démarrage de l'acitivité de connexion (FirebaseAuth)
        startSignInActivity()
    }

    //@OnClick(R.id.main_activity_button_crash)
    fun onClickCrashButton() {
        Crashlytics.getInstance().crash()
    }

    // =============================================================================================
    */
/*
        Création de l'utilisateur dans Firestore en récupérant
        les informations depuis FireAuth
     *//*

    private fun createUserInFirestore() {
        if (this.currentUser != null) {
            val uid = currentUser!!.uid
            val email = currentUser!!.email
            val username = currentUser!!.displayName
            val firstName = splitUsername(currentUser!!.displayName)[0]
            val lastName = splitUsername(currentUser!!.displayName)[1]
            val urlPicture = if (currentUser!!.photoUrl != null) currentUser!!.photoUrl.toString() else null
            val lvl = 1
            val xp = 0
            val isConnected = "true"
            createUser(uid, email, username, firstName, lastName, urlPicture, lvl, xp, isConnected, TOKEN)
                    .addOnFailureListener(onFailureListener())
        }
    }

    // On coupe la chaine de caractères provenant de la méthode getDisplayName() afin de récupérer
    // le nom et le prénom séparément
    private fun splitUsername(username: String?): Array<String> {
        return username!!.split("\\s").toTypedArray()
    }

    // Méthode d'inscription/connexion à l'aide de FireBaseUI
    private fun startSignInActivity() {
        startActivityForResult(
                AuthUI.getInstance()
                        .createSignInIntentBuilder()
                        .setTheme(R.style.LoginTheme)
                        .setAvailableProviders(
                                listOf(EmailBuilder().build(),
                                        GoogleBuilder().build()
                                ))
                        .setLogo(R.drawable.party_go_title_logo)
                        .build(),
                RC_SIGN_IN)
    }

    // Gestion du résultat de la connexion
    private fun handleResponseAfterSignIn(requestCode: Int, resultCode: Int, data: Intent?) {
        val response = IdpResponse.fromResultIntent(data)
        // Vérification du code
        if (requestCode == RC_SIGN_IN) { // Si la connexion s'est bien passé
            if (resultCode == Activity.RESULT_OK) //SUCCESS
            {
                checkIfUserExists(object : UsernameCallback {
                    override fun onCallback(userExists: Boolean) {
                        if (!userExists) { // Création de l'utilisateur en BDD
                            createUserInFirestore()
                        }
                    }
                })
                // Affichage d'une SnackBar
                updateIsConnected("true", currentUser!!.uid)
                showSnackBar(coordinatorLayout, getString(R.string.connection_succeed))
                val profile = Intent(this@MainActivity, ProfileActivity::class.java)
                val mainMenu = Intent(this@MainActivity, MainMenu::class.java)
                startActivity(mainMenu)
            } else  // En cas d'erreur
            { // Si l'utilisateur a annulé la connexion
                when {
                    response == null -> {
                        showSnackBar(coordinatorLayout, getString(R.string.error_authentication_canceled))
                    }
                    response.error!!.errorCode == ErrorCodes.NO_NETWORK -> {
                        showSnackBar(coordinatorLayout, getString(R.string.error_no_internet))
                    }
                    response.error!!.errorCode == ErrorCodes.UNKNOWN_ERROR -> {
                        showSnackBar(coordinatorLayout, getString(R.string.error_unknown_error))
                    }
                }
            }
        }
    }

    // Vérification que l'utilisateur n'existe pas déja dans la BDD afin de ne pas écraser
    // un document déja existant
    private fun checkIfUserExists(usernameCallback: UsernameCallback) {
        getUser(currentUser!!.uid).addOnSuccessListener { documentSnapshot: DocumentSnapshot ->
            var userExists = false
            if (documentSnapshot.exists()) userExists = true
            usernameCallback.onCallback(userExists)
        }
    } // =============================================================================================

    companion object {
        // =============================================================================================
    // Code de vérification de connexion
        private const val RC_SIGN_IN = 123
    }
}*/
