package com.rpifinal.hitema.partyGo.data.model

import android.net.Uri
import javax.inject.Inject

/**
 * Data class that captures user information for logged in users retrieved from LoginRepository
 */
data class LoggedInUser @Inject constructor(
        val userId: String,
        val displayName: String?,
        val email: String?,
        val phoneNumber: String?,
        val photoUrl: Uri?
)