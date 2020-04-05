package oldFiles

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import oldFiles.api.UserHelper.deleteUser
import oldFiles.api.UserHelper.getUser
import oldFiles.api.UserHelper.updateIsConnected
import com.SACGGames.PartyGoMiniGames.UnityPlayerActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.firebase.ui.auth.AuthUI
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.firestore.DocumentSnapshot
import com.rpifinal.hitema.R
import oldFiles.model.User

class ProfileActivity : BaseActivity() {
    private var mUser: User? = null
    // Récupération des éléments de la vue au sein du code Java
    @JvmField
    //@BindView(R.id.profile_activity_view_picture)
    var mImageViewProfile: ImageView? = null
    @JvmField
    //@BindView(R.id.profile_activity_view_name)
    var mTextViewName: TextView? = null
    @JvmField
    //@BindView(R.id.profile_activity_view_email)
    var mTextViewEmail: TextView? = null

    // =============================================================================================
    // =============================================================================================
    // Récupération de la vue correspondante à l'activité
    override val fragmentLayout: Int
        get() = R.layout.activity_profile

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // A la création de l'activité on met à jour la vue
        updateUIWhenCreating()
        objectCurrentUser
    }

    // =============================================================================================
// =============================================================================================
// ACTIONS
//Boutton de retour de la page update vers la page profil
// Quand l'utilisateur clique sur déconnexion
    //@OnClick(R.id.profile_activity_logout_button)
    fun onClickLogoutButton() {
        updateIsConnected("false", currentUser!!.uid).addOnSuccessListener { signOutUserFromFirebase() }
    }

    // Quand l'utilisateur clique sur suppression du compte
    //@OnClick(R.id.profile_activity_delete_button)
    fun onClickDeleteButton() {
        val builder = AlertDialog.Builder(this@ProfileActivity)
        builder.setCancelable(true)
        builder.setTitle("Confirmation")
        builder.setMessage("Voulez vous vraiment supprimer votre compte ?")
        //Si la reponse est non
        builder.setNegativeButton("NON") { dialog: DialogInterface, whichButton: Int -> dialog.cancel() }
        //si la reponse est oui
        builder.setPositiveButton("OUI") { dialog: DialogInterface?, whichButton: Int -> deleteUserFromFirebase() }
        // Affichage
        builder.show()
    }

    // Quand l'utilisateur clique sur Modifier informations
    //@OnClick(R.id.profile_activity_update_button)
    fun onClickUpdateButton() {
        val update = Intent(this@ProfileActivity, UpdateUserActivity::class.java)
        startActivity(update)
    }

    //@OnClick(R.id.home_button)
    fun onClickHomeButton() {
        val home = Intent(this@ProfileActivity, MainMenu::class.java)
        startActivity(home)
    }

    //@OnClick(R.id.profile_activity_game1, R.id.profile_activity_game2, R.id.profile_activity_game3, R.id.profile_activity_game4, R.id.profile_activity_game5)
    fun onClickGame(view: View) {
        when (view.id) {
            R.id.profile_activity_game1 -> lauchGame(GAME1)
            R.id.profile_activity_game2 -> lauchGame(GAME2)
            R.id.profile_activity_game3 -> lauchGame(GAME3)
            R.id.profile_activity_game4 -> lauchGame(GAME4)
            R.id.profile_activity_game5 -> lauchGame(GAME5)
        }
    }

    private fun lauchGame(game: Int) { //Random r = new Random();
//int room = 100 + r.nextInt(10000 - 100);
        val startGame = Intent(this@ProfileActivity, UnityPlayerActivity::class.java)
        startGame.putExtra("game", game)
        startGame.putExtra("room", 20)
        if (this.currentUser != null) {
            startGame.putExtra("username", mUser!!.username)
        } else {
            startGame.putExtra("username", "No value")
        }
        startActivity(startGame)
    }

    // =============================================================================================
// =============================================================================================
// Méthode mettant à jour la vue (Appelée à la création de l'activité)
    private fun updateUIWhenCreating() { // Vérification que l'utilisateur actuel n'est pas vide
        if (this.currentUser != null) { // Si une l'utilisateur possède une photo
            if (currentUser!!.photoUrl != null) { // Utilisation de Glide pour intégrer la photo dans la vue
                Glide.with(this)
                        .load(currentUser!!.photoUrl)
                        .apply(RequestOptions.circleCropTransform())
                        .into(mImageViewProfile!!)
            }
            // Récupération de l'email de l'utilisateur en vérifiant qu'il n'est pas NULL
            val email: String = if (TextUtils.isEmpty(
                            currentUser!!.email)) getString(R.string.info_no_email_found) else currentUser!!.email.toString()
            val name = currentUser!!.displayName
            // Insertion de l'email dans la vue
            mTextViewEmail!!.text = email
            mTextViewName!!.text = name
        }
    }

    // =============================================================================================
// =============================================================================================
// --------------------
// REST REQUESTS
// --------------------
    private val objectCurrentUser: Unit
        get() {
            getUser(currentUser!!.uid).addOnSuccessListener { documentSnapshot: DocumentSnapshot -> mUser = documentSnapshot.toObject(User::class.java) }
        }

    // Méthode de déconnexion (FirebaseAuth)
    private fun signOutUserFromFirebase() {
        AuthUI.getInstance()
                .signOut(this)
                .addOnSuccessListener(this, updateUIAfterRESTRequestsCompleted(SIGN_OUT_TASK))
    }

    // Méthode de suppression de compte (FirebaseAuth)
    private fun deleteUserFromFirebase() {
        if (this.currentUser != null) {
            deleteUser(currentUser!!.uid)
                    //TODO : Fix this motherfucker
                    // .addOnFailureListener(onFailureListener())
            AuthUI.getInstance()
                    .delete(this)
                    .addOnSuccessListener(this, updateUIAfterRESTRequestsCompleted(DELETE_USER_TASK))
        }
    }

    // Cette méthode est appelée à la fin de la déconnexion ou de la suppression pour terminer l'activité
    private fun updateUIAfterRESTRequestsCompleted(origin: Int): OnSuccessListener<Void> {
        return OnSuccessListener { aVoid: Void? ->
            val mainAct = Intent(this, MainActivity::class.java)
            when (origin) {
                SIGN_OUT_TASK -> startActivity(mainAct)
                DELETE_USER_TASK -> startActivity(mainAct)
                else -> {
                }
            }
        }
    } // =============================================================================================

    companion object {
        // =============================================================================================
// Code pour chaque requête HTTP
        private const val SIGN_OUT_TASK = 10
        private const val DELETE_USER_TASK = 20
        private const val UPDATE_USERNAME = 30
        // Code Jeux
        private const val GAME1 = 100
        private const val GAME2 = 200
        private const val GAME3 = 300
        private const val GAME4 = 400
        private const val GAME5 = 500
    }
}