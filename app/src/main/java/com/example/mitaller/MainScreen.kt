import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.navigation.NavController
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.platform.LocalContext
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.unit.dp
import androidx.compose.ui.platform.LocalContext
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import androidx.compose.material3.*
import androidx.compose.material.*
import com.example.mitaller.database.AppDatabase
import com.example.mitaller.database.User
import com.google.firebase.firestore.auth.User

@Composable
fun MainScreen(navController: NavController) {
    val context = LocalContext.current
    val db = remember { AppDatabase.getDatabase(context) }  // Obtén la instancia de la base de datos
    val userDao = remember { db.userDao() }
    val nameState = remember { mutableStateOf("") }
    val scope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Ingrese su nombre:")

        OutlinedTextField(
            value = nameState.value,
            onValueChange = { newValue ->
                nameState.value = newValue

                // Guarda el nombre en la base de datos tan pronto se ingrese
                scope.launch {
                    withContext(Dispatchers.IO) {
                        val user = User(name = nameState.value)
                        userDao.insertUser(user)  // Inserta el nombre en la base de datos
                    }
                }
            },
            label = { Text("Nombre") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                if (nameState.value.isNotEmpty()) {
                    navController.navigate("settings")
                } else {
                    // Mostrar mensaje o hacer algo si el nombre está vacío
                    // Por ejemplo, puedes mostrar un Toast indicando que deben ingresar el nombre
                }
            }
        ) {
            Text(text = "Continuar")
        }
    }
}
