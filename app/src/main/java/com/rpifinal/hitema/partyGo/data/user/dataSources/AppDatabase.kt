package com.rpifinal.hitema.partyGo.data.user.dataSources

import androidx.room.Database
import androidx.room.RoomDatabase
import com.rpifinal.hitema.partyGo.data.user.model.User
import com.rpifinal.hitema.partyGo.data.user.model.UserDAO

@Database(entities = [User::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDAO
}
