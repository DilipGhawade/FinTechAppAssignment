package com.dilip.ghawade.fintechappassignment2.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.dilip.ghawade.fintechappassignment2.model.User
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
     fun insertUser(user: User):Long

    @Query("SELECT * FROM users WHERE username = :username AND password = :password")
    fun userLogin(username: String, password: String): Flow<User?>
}
