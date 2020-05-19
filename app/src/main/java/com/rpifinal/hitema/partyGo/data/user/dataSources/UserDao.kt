package com.rpifinal.hitema.partyGo.data.user.dataSources

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.rpifinal.hitema.partyGo.data.user.model.User

@Dao
interface UserDao {
    @Insert(onConflict = REPLACE)
    fun save(user: User)

    @Query("SELECT * FROM user WHERE uid = :uid")
    fun load(uid: String): LiveData<User>
}
