package com.dilip.ghawade.fintechappassignment2.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.dilip.ghawade.fintechappassignment2.model.Post
import com.dilip.ghawade.fintechappassignment2.model.User

@Database(entities = [User::class, Post::class], version = 3, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun postDao(): PostDao
}
