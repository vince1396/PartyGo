package oldFiles

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import oldFiles.api.UserHelper.getUser
import oldFiles.api.UserHelper.updateFirstName
import oldFiles.api.UserHelper.updateLastName
import oldFiles.api.UserHelper.updateUsername
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.firestore.DocumentSnapshot
import com.rpifinal.hitema.R
import oldFiles.model.User

//TODO : Optimisation REGEX
class UpdateUserActivity : BaseActivity() {
    // Binding UI
    @JvmField
    //@BindView(R.id.update_activity_title_textView)
    var mTitleUpdateTextView: TextView? = null
    @JvmField
    //@BindView(R.id.update_activity_username_field)
    var mUsernameUpdateField: EditText? = null
    @JvmField
    //@BindView(R.id.update_activity_firstName_field)
    var mFirstnameUpdateField: EditText? = null
    @JvmField
    //@BindView(R.id.update_activity_lastName_field)
    var mLastnameUpdateField: EditText? = null
    @JvmField
    //@BindView(R.id.update_activity_username_submit)
    var mUsernameButton: Button? = null
    @JvmField
    //@BindView(R.id.update_activity_firstName_submit)
    var mFirstnameButton: Button? = null
    @JvmField
    //@BindView(R.id.update_activity_lastName_submit)
    var mLastnameButton: Button? = null

    // =============================================================================================
    // =============================================================================================
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        updateUI()
    }

    override val fragmentLayout: Int
        get() = R.layout.activity_update_user

    // =============================================================================================
    // =============================================================================================
    // ACTIONS
    // TODO : Regrouper onCLick
    //Boutton de retour de la page update vers la page profil
    //@OnClick(R.id.back_to_profile)
    fun onClickReturnProfilButton() {
        val profile = Intent(this, ProfileActivity::class.java)
        startActivity(profile)
    }

    //@OnClick(R.id.update_activity_username_submit, R.id.update_activity_firstName_submit, R.id.update_activity_lastName_submit)
    fun onClickUpdate(view: View) {
        val uid = currentUser!!.uid
        when (view.id) {
            R.id.update_activity_username_submit -> {
                val username = mUsernameUpdateField!!.text.toString()
                checkDataEntry(UPDATE_USERNAME, username, uid)
            }
            R.id.update_activity_firstName_submit -> {
                val firstName = mFirstnameUpdateField!!.text.toString()
                checkDataEntry(UPDATE_FIRSTNAME, firstName, uid)
            }
            R.id.update_activity_lastName_submit -> {
                val lastName = mLastnameUpdateField!!.text.toString()
                checkDataEntry(UPDATE_LASTNAME, lastName, uid)
            }
        }
        //Hide keyboard when click on the update button
        closeKeyboard()
    }

    //Method closeKeyboard() that will close/hide the keyboard when click on the valid update button
    private fun closeKeyboard() {
        val view = this.currentFocus
        if (view != null) {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
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
    fun checkDataEntry(code: Int, data: String, uid: String?) {
        val namesCondition = data.matches(REGEX_FL_NAME.toRegex()) && data != "" && data.length > 3
        when (code) {
            UPDATE_LASTNAME -> if (namesCondition) {
                val successMessage = getString(R.string.success_update_lastname)
                updateLastName(data, uid).addOnFailureListener(onFailureListener())
                        .addOnSuccessListener(onSuccessListener(successMessage) as OnSuccessListener<in Void>)
            } else {
                val errorMessage = getString(R.string.error_update_lastname)
                Toast.makeText(applicationContext, errorMessage, Toast.LENGTH_SHORT).show()
            }
            UPDATE_FIRSTNAME -> if (namesCondition) {
                val successMessage = getString(R.string.success_update_firstname)
                updateFirstName(data, uid).addOnFailureListener(onFailureListener())
                        .addOnSuccessListener(onSuccessListener(successMessage) as OnSuccessListener<in Void>)
            } else {
                val errorMessage = getString(R.string.error_update_firstname)
                Toast.makeText(applicationContext, errorMessage, Toast.LENGTH_SHORT).show()
            }
            UPDATE_USERNAME -> if (data.matches(REGEX_USERNAME.toRegex()) && data != "" && data.length > 3) {
                val successMessage = getString(R.string.success_update_username)
                updateUsername(data, uid).addOnFailureListener(onFailureListener()).addOnSuccessListener(onSuccessListener(successMessage) as OnSuccessListener<in Void>)
            } else {
                val errorMessage = getString(R.string.error_update_username)
                Toast.makeText(applicationContext, errorMessage, Toast.LENGTH_SHORT).show()
            }
        }
        updateUI()
    }

    fun updateUI() {
        if (this.currentUser != null) {
            getUser(currentUser!!.uid).addOnSuccessListener { documentSnapshot: DocumentSnapshot ->
                val currentUser = documentSnapshot.toObject(User::class.java)
                if (!currentUser!!.username?.isEmpty()!!) {
                    mUsernameUpdateField?.setText(currentUser.username)
                }
                if (!currentUser.firstName?.isEmpty()!!) {
                    mFirstnameUpdateField?.setText(currentUser.firstName)
                }
                if (!currentUser.lastName?.isEmpty()!!) {
                    mLastnameUpdateField?.setText(currentUser.lastName)
                }
            }
        }
        if (this.currentUser == null) {
            mTitleUpdateTextView!!.text = getString(R.string.no_user_connected_error)
        }
    }

    fun makeToast() {
        Toast.makeText(baseContext, "OKAY", Toast.LENGTH_SHORT).show()
    }

    private fun onSuccessListener(resId: String): OnSuccessListener<*> {
        return OnSuccessListener { e: Any? -> Toast.makeText(applicationContext, resId, Toast.LENGTH_SHORT).show() }
    } // =============================================================================================

    companion object {
        // =============================================================================================
        // ATTRIBUTS MEMBRES
        private const val TAG = "UpdateUserActivity"
        // Expressions régulières
        private const val REGEX_FL_NAME = "[a-zA-Z-]+[:blank]?[a-zA-Z]+"
        private const val REGEX_USERNAME = "[a-zA-Z0-9_]+"
        // CODE REST
        private const val UPDATE_USERNAME = 10
        private const val UPDATE_FIRSTNAME = 20
        private const val UPDATE_LASTNAME = 30
    }
}