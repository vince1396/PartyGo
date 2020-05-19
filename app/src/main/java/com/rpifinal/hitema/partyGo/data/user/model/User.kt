package com.rpifinal.hitema.partyGo.data.user.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import javax.inject.Inject

/**
 * Data class that captures user information for logged in users retrieved from LoginRepository
 */
@Entity
data class User @Inject constructor(
        @PrimaryKey val uid: String? = null,
        val email: String? = null,
        val phoneNumber: String? = null,
        val photoUrl: String? = null,
        val firstName: String? = null,
        val lastName: String? = null,
        val userName: String? = null,
        val lvl: Int = 1,
        val xp: Int = 0
)  {
    constructor() : this(
            null,
            null,
            null,
            null,
            null ,
            null,
            null,
            1,
            0)
}