package com.example.mitaller

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import java.util.*
import androidx.compose.runtime.*
import kotlinx.coroutines.delay
import java.time.LocalTime
import java.time.format.DateTimeFormatter


@Composable
fun HomeScreen(navController: NavController) {
    val currentTime = remember { mutableStateOf(LocalTime.now()) }
    val greeting = remember { mutableStateOf("") }

    LaunchedEffect(Unit) {
        while (true) {
            delay(1000L)
            currentTime.value = LocalTime.now()
            greeting.value = getGreetingMessage(currentTime.value.hour)
        }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "${greeting.value}, son las ${currentTime.value.format(DateTimeFormatter.ofPattern("HH:mm:ss"))}",
            style = MaterialTheme.typography.headlineMedium
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { navController.navigate("main") }) {
            Text(text = "Ir a la actividad principal")
        }
    }
}

fun getGreetingMessage(hour: Int): String {
    return when (hour) {
        in 5..11 -> "Buenos días"
        in 12..17 -> "Buenas tardes"
        else -> "Buenas noches"
    }
}
