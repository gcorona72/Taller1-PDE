import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController // Asegúrate de tener la clase creada
import com.example.mitaller.User  // Esta es tu clase de entidad, no la de Firebase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

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
            },
            label = { Text("Nombre") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                if (nameState.value.isNotEmpty()) {
                    // Guarda el nombre en la base de datos cuando el usuario hace clic en "Continuar"
                    scope.launch {
                        withContext(Dispatchers.IO) {
                            val user = User(name = nameState.value)
                            userDao.insertUser(user)  // Inserta el nombre en la base de datos
                        }
                    }

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
