package com.example.mitaller

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun MainScreen(navController: NavController) {
    var name by remember { mutableStateOf("") }
    var savedName by remember { mutableStateOf("") }
    var isNameSaved by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        TextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Ingresa tu nombre") }
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            if (name.isNotEmpty()) {
                savedName = name
                isNameSaved = true
            }
        }) {
            Text(text = "Guardar nombre")
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = if (isNameSaved) "Nombre guardado: $savedName" else "No se ha guardado el nombre")
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = { if (isNameSaved) navController.navigate("settings") },
            enabled = isNameSaved
        ) {
            Text(text = "Ir a Configuración")
        }
    }
}
