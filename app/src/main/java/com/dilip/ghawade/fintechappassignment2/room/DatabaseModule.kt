package com.dilip.ghawade.fintechappassignment2.room

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    val MIGRATION_2_3 = object : Migration(2, 3) {
        override fun migrate(database: SupportSQLiteDatabase) {
            // Migrate users table
            // Create a new table with the correct schema for users
            database.execSQL("""
            CREATE TABLE users_new (
                id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
                username TEXT NOT NULL,
                password TEXT NOT NULL,
                email TEXT NOT NULL,
                mobileNo TEXT NOT NULL
            )
        """.trimIndent())

            // Copy data from old table to new table
            database.execSQL("""
            INSERT INTO users_new (id, username, password, email, mobileNo)
            SELECT id, username, password, email, mobileNo FROM users
        """.trimIndent())

            // Drop the old users table
            database.execSQL("DROP TABLE users")

            // Rename the new table to original name
            database.execSQL("ALTER TABLE users_new RENAME TO users")

            // Check if the posts table exists before migrating
            database.execSQL("""
            CREATE TABLE IF NOT EXISTS posts_new (
                id INTEGER PRIMARY KEY NOT NULL,
                userId INTEGER NOT NULL,
                title TEXT NOT NULL,
                body TEXT NOT NULL
            )
        """.trimIndent())

            // Check if the posts table exists before attempting to copy data
            val cursor = database.query("SELECT name FROM sqlite_master WHERE type='table' AND name='posts'")
            if (cursor.count > 0) {
                // Copy data from old posts table to new posts table
                database.execSQL("""
                INSERT INTO posts_new (id, userId, title, body)
                SELECT id, userId, title, body FROM posts
            """.trimIndent())

                // Drop the old posts table
                database.execSQL("DROP TABLE posts")
            }
            cursor.close()

            // Rename the new posts table to original name
            database.execSQL("ALTER TABLE posts_new RENAME TO posts")
        }
    }





    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, "app_database")
            .addMigrations(MIGRATION_2_3) // ✅ Add Migration
            .build()
    }

    @Provides
    fun provideUserDao(database: AppDatabase): UserDao = database.userDao()

    @Provides
    fun providePostDao(database: AppDatabase): PostDao = database.postDao()

}
