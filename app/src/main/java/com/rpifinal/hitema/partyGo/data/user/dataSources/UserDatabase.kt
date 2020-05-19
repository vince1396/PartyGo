package com.rpifinal.hitema.partyGo.data.user.dataSources

import androidx.room.Database
import androidx.room.RoomDatabase
import com.rpifinal.hitema.partyGo.data.user.model.User

@Database(entities = [User::class], version = 1)
abstract class UserDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}
