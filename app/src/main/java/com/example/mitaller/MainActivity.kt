package com.example.mitaller

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.navigation.compose.rememberNavController
import com.example.mitaller.Almacenamiento.UserDatabase
import com.example.mitaller.ui.theme.MiTallerTheme

class MainActivity : ComponentActivity() {
    private lateinit var userDatabase: UserDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        userDatabase = UserDatabase(this)

        setContent {
            val navController = rememberNavController()
            MiTallerTheme {
                MainScreen(navController, userDatabase)
            }
        }
    }
}