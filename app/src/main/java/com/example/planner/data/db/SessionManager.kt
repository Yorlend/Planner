package com.example.planner.data.db

import android.content.Context
import android.content.SharedPreferences
import com.example.planner.data.entities.UserEntity

class SessionManager(
    private val context: Context,
) {
    private val sharedPrefName = "session"
    private val sessionKey = "session_user"
    private val sharedPrefs: SharedPreferences =
        context.getSharedPreferences(sharedPrefName, Context.MODE_PRIVATE)
    private val sharedPrefsEditor = sharedPrefs.edit()

    fun logoutFromSession() {
        if (getSession() != -1) {
            sharedPrefsEditor.remove(sessionKey).commit()
        }
    }
    
    fun saveSession(userEntity: UserEntity) {
        val id = userEntity.id

        id?.let {
            sharedPrefsEditor.putInt(sessionKey, id).commit()
        }
    }
    
    fun getSession(): Int {
        return sharedPrefs.getInt(sessionKey, -1)
    }
}