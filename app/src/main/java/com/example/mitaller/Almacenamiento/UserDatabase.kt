package com.example.mitaller.Almacenamiento

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.mitaller.Almacenamiento.UserConstants
import com.example.mitaller.Almacenamiento.User

class UserDatabase(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(
            "CREATE TABLE ${UserConstants.TABLE_NAME} (" +
                    "${UserConstants.COLUMN_ID} INTEGER PRIMARY KEY," +
                    "${UserConstants.COLUMN_NAME_USERNAME} TEXT," +
                    "${UserConstants.COLUMN_NAME_EMAIL} TEXT," +
                    "${UserConstants.COLUMN_NAME_PASSWORD} TEXT)"
        )
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS ${UserConstants.TABLE_NAME}")
        onCreate(db)
    }

    override fun onDowngrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        onUpgrade(db, oldVersion, newVersion)
    }

    fun insertUser(username: String, email: String, password: String): Int {
        val db = writableDatabase
        val values = ContentValues().apply {
            put(UserConstants.COLUMN_NAME_USERNAME, username)
            put(UserConstants.COLUMN_NAME_EMAIL, email)
            put(UserConstants.COLUMN_NAME_PASSWORD, password)
        }
        val newRowId = db.insert(UserConstants.TABLE_NAME, null, values)
        return newRowId.toInt()
    }

    @SuppressLint("Range")
    fun readUsers(usernameToSearch: String): List<User> {
        val db = readableDatabase
        val projection = arrayOf(
            UserConstants.COLUMN_ID,
            UserConstants.COLUMN_NAME_USERNAME,
            UserConstants.COLUMN_NAME_EMAIL,
            UserConstants.COLUMN_NAME_PASSWORD
        )
        val selection = "${UserConstants.COLUMN_NAME_USERNAME} = ?"
        val selectionArgs = arrayOf(usernameToSearch)
        val sortOrder = "${UserConstants.COLUMN_ID} DESC"
        val cursor = db.query(
            UserConstants.TABLE_NAME,
            projection,
            selection,
            selectionArgs,
            null,
            null,
            sortOrder
        )
        val list = mutableListOf<User>()
        with(cursor) {
            while (moveToNext()) {
                val user = User(
                    getInt(getColumnIndex(UserConstants.COLUMN_ID)),
                    getString(getColumnIndex(UserConstants.COLUMN_NAME_USERNAME)),
                    getString(getColumnIndex(UserConstants.COLUMN_NAME_EMAIL)),
                    getString(getColumnIndex(UserConstants.COLUMN_NAME_PASSWORD))
                )
                list.add(user)
            }
        }
        cursor.close()
        return list
    }

    fun updateUser(id: Int, newEmail: String) {
        val db = writableDatabase
        val values = ContentValues().apply {
            put(UserConstants.COLUMN_NAME_EMAIL, newEmail)
        }
        val selection = "${UserConstants.COLUMN_ID} LIKE ?"
        val selectionArgs = arrayOf(id.toString())
        db.update(UserConstants.TABLE_NAME, values, selection, selectionArgs)
    }

    fun deleteUser(username: String) {
        val db = writableDatabase
        val selection = "${UserConstants.COLUMN_NAME_USERNAME} LIKE ?"
        val selectionArgs = arrayOf(username)
        db.delete(UserConstants.TABLE_NAME, selection, selectionArgs)
    }

    companion object {
        const val DATABASE_VERSION = 1
        const val DATABASE_NAME = "UserDatabase.db"
    }
}