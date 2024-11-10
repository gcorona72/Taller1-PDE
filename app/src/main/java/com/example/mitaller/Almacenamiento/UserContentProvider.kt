package com.example.mitaller.Almacenamiento

import android.content.ContentProvider
import android.content.ContentUris
import android.content.ContentValues
import android.content.UriMatcher
import android.database.Cursor
import android.database.sqlite.SQLiteException
import android.net.Uri

class UserContentProvider : ContentProvider() {

    companion object {
        const val AUTHORITY = "com.example.mitaller.provider"
        private const val PATH_USERS = UserConstants.TABLE_NAME
        val CONTENT_URI_USERS: Uri = Uri.parse("content://$AUTHORITY/$PATH_USERS")
        val CONTENT_URI_USER_ID: Uri = Uri.parse("content://$AUTHORITY/$PATH_USERS/#")

        private const val CODE_URI_USERS = 1
        private const val CODE_URI_USER_ID = 2

        private val uriMatcher = UriMatcher(UriMatcher.NO_MATCH).apply {
            addURI(AUTHORITY, PATH_USERS, CODE_URI_USERS)
            addURI(AUTHORITY, "$PATH_USERS/#", CODE_URI_USER_ID)
        }
    }

    private lateinit var db: UserDatabase

    override fun onCreate(): Boolean {
        val context = context ?: return false
        db = UserDatabase(context)
        return true
    }

    override fun getType(uri: Uri): String? {
        return when (uriMatcher.match(uri)) {
            CODE_URI_USERS -> "vnd.android.cursor.dir/vnd.$AUTHORITY.${UserConstants.TABLE_NAME}"
            CODE_URI_USER_ID -> "vnd.android.cursor.item/vnd.$AUTHORITY.${UserConstants.TABLE_NAME}"
            else -> throw IllegalArgumentException("Unsupported URI: $uri")
        }
    }

    override fun query(uri: Uri, projection: Array<out String>?, selection: String?, selectionArgs: Array<out String>?, sortOrder: String?): Cursor? {
        return when (uriMatcher.match(uri)) {
            CODE_URI_USERS -> db.readableDatabase.query(UserConstants.TABLE_NAME, projection, selection, selectionArgs, null, null, sortOrder)
            CODE_URI_USER_ID -> {
                val id = ContentUris.parseId(uri)
                db.readableDatabase.query(UserConstants.TABLE_NAME, projection, "${UserConstants.COLUMN_ID} = ?", arrayOf(id.toString()), null, null, sortOrder)
            }
            else -> throw IllegalArgumentException("Unsupported URI: $uri")
        }
    }

    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        val rowID = db.writableDatabase.insert(UserConstants.TABLE_NAME, null, values)
        if (rowID > 0) {
            val _uri = ContentUris.withAppendedId(CONTENT_URI_USER_ID, rowID)
            context?.contentResolver?.notifyChange(_uri, null)
            return _uri
        }
        throw SQLiteException("Failed to add a record into $uri")
    }

    override fun update(uri: Uri, values: ContentValues?, selection: String?, selectionArgs: Array<out String>?): Int {
        val count: Int
        when (uriMatcher.match(uri)) {
            CODE_URI_USER_ID -> {
                val id = ContentUris.parseId(uri)
                count = db.writableDatabase.update(UserConstants.TABLE_NAME, values, "${UserConstants.COLUMN_ID} = ?", arrayOf(id.toString()))
            }
            else -> throw IllegalArgumentException("Unknown URI $uri")
        }
        context?.contentResolver?.notifyChange(uri, null)
        return count
    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<out String>?): Int {
        val count: Int
        when (uriMatcher.match(uri)) {
            CODE_URI_USER_ID -> {
                val id = ContentUris.parseId(uri)
                count = db.writableDatabase.delete(UserConstants.TABLE_NAME, "${UserConstants.COLUMN_ID} = ?", arrayOf(id.toString()))
            }
            CODE_URI_USERS -> {
                count = db.writableDatabase.delete(UserConstants.TABLE_NAME, selection, selectionArgs)
            }
            else -> throw IllegalArgumentException("Unknown URI $uri")
        }
        context?.contentResolver?.notifyChange(uri, null)
        return count
    }
}