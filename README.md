### **Proyecto: Aplicación de Pantallas con Navegación en Jetpack Compose**

#### **Descripción General:**

Este proyecto es una aplicación Android simple, construida con **Jetpack Compose**, que cuenta con tres pantallas:

1.  **MainScreen**: Pantalla principal donde el usuario introduce su nombre.
2.  **HomeScreen**: Pantalla que muestra un saludo personalizado al usuario dependiendo de la hora del día.
3.  **SettingsScreen**: Pantalla donde el usuario puede cambiar el color de fondo de la aplicación.

El objetivo es proporcionar una navegación sencilla entre las pantallas mientras se aplican interacciones básicas como la validación de la entrada del nombre en la `MainScreen` y la personalización de la experiencia en la `SettingsScreen`.

#### **Estructura del Proyecto:**

-   **MainActivity**: Esta es la actividad principal que configura el sistema de navegación entre las pantallas.
-   **Pantallas (Screens)**:
    -   `MainScreen`: Permite ingresar el nombre del usuario.
    -   `HomeScreen`: Muestra un saludo basado en la hora del día y el nombre del usuario ingresado.
    -   `SettingsScreen`: Proporciona opciones para cambiar el color de fondo de la aplicación.

* * * * *

### **Componentes Principales:**

#### 1\. **MainActivity.kt**

Este archivo configura la navegación utilizando **NavController** y las diferentes pantallas dentro de la aplicación.

-   **Objetivo**: Administrar la navegación entre las pantallas.
-   **Funcionalidad**:
    -   Se define un `NavHost` que contiene las rutas para `MainScreen`, `HomeScreen` y `SettingsScreen`.
    -   `userName` es una variable que se utiliza para almacenar y compartir el nombre del usuario entre pantallas.

kotlin

Copiar código

`val navController = rememberNavController()
var userName by remember { mutableStateOf("") }`

-   **Pantallas**: La navegación comienza desde la `MainScreen` y el flujo se define en el `NavHost`.

kotlin

Copiar código

`NavHost(navController = navController, startDestination = "main") {
    composable("main") {
        MainScreen(navController = navController, userName = userName, onNameEntered = { name ->
            userName = name
        })
    }
    composable("home") {
        HomeScreen(navController = navController, userName = userName)
    }
    composable("settings") {
        SettingsScreen(navController = navController)
    }
}`

#### 2\. **MainScreen.kt**

-   **Objetivo**: Solicitar al usuario que ingrese su nombre.
-   **Funcionalidad**:
    -   La pantalla contiene un `TextField` para ingresar el nombre y un botón para navegar a la siguiente pantalla (`HomeScreen`).
    -   El botón no permite la navegación si el campo de nombre está vacío.

kotlin

Copiar código

`Button(onClick = {
    if (name.text.isNotBlank()) {
        onNameEntered(name.text)
        navController.navigate("home")
    }
}) {
    Text("Go to Home")
}`

#### 3\. **HomeScreen.kt**

-   **Objetivo**: Mostrar un saludo basado en la hora del día y el nombre ingresado.
-   **Funcionalidad**:
    -   El saludo se adapta según la hora (mañana, tarde o noche).
    -   También muestra el nombre ingresado por el usuario en la `MainScreen`.

kotlin

Copiar código

`fun getTimeBasedGreeting(): String {
    val currentHour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY)
    return when (currentHour) {
        in 0..11 -> "Good morning"
        in 12..17 -> "Good afternoon"
        else -> "Good evening"
    }
}`

#### 4\. **SettingsScreen.kt**

-   **Objetivo**: Permitir al usuario cambiar el color de fondo de la aplicación.
-   **Funcionalidad**:
    -   Se ofrecen botones para seleccionar diferentes colores (`Red`, `Green`, `Blue`).
    -   Se muestra un botón adicional para volver a la pantalla `HomeScreen`.

kotlin

Copiar código

`Button(onClick = { backgroundColor = Color.Red }) {
    Text("Red")
}`

* * * * *

### **Flujo de la Aplicación:**

1.  **Inicio**: El usuario abre la aplicación y se le presenta la `MainScreen` donde debe ingresar su nombre.
2.  **Navegación a HomeScreen**: Una vez que el usuario ingresa su nombre, puede avanzar a la `HomeScreen`, donde recibirá un saludo personalizado basado en la hora actual.
3.  **Personalización en SettingsScreen**: Desde la `HomeScreen`, el usuario puede acceder a la `SettingsScreen`, donde puede cambiar el color de fondo de la aplicación.
4.  **Interactividad**: Después de cambiar el color de fondo, el usuario puede regresar a la `HomeScreen`.

* * * * *

### **Arquitectura:**

El proyecto sigue el patrón **MVVM (Model-View-ViewModel)** utilizando los principios de **Jetpack Compose** para la interfaz de usuario declarativa y la navegación basada en `NavController`.

-   **Model**: La aplicación actualmente no incluye un modelo de datos persistente, ya que no hay almacenamiento de datos (como bases de datos). El valor de `userName` se maneja en memoria.
-   **View**: Las tres pantallas (`MainScreen`, `HomeScreen`, `SettingsScreen`) representan las vistas de la aplicación.
-   **ViewModel**: Dado que los datos de la aplicación no se conservan entre cierres, no se utiliza un `ViewModel`. El estado del nombre del usuario se maneja utilizando variables de estado locales.

* * * * *

### **Temas y Estilos:**

La aplicación utiliza `MaterialTheme` para establecer los colores y tipografías. Las pantallas están diseñadas para ser simples y fáciles de usar, con botones claros y mensajes de texto adecuados para la interacción del usuario.

* * * * *

### **Futuras Mejoras:**

1.  **Persistencia de Datos**: Se podría agregar una base de datos para almacenar el nombre del usuario, para que no tenga que ser reingresado cada vez que se abre la aplicación.
2.  **Mejora en la Personalización**: Añadir más opciones en `SettingsScreen`, como cambiar la fuente o añadir un selector de colores más avanzado.
3.  **Optimización del Flujo**: Se podría optimizar la navegación y mejorar la lógica de las restricciones de ingreso de datos.

* * * * *

### **Requisitos del Proyecto:**

-   **Android Studio**: Para ejecutar el proyecto en un entorno de desarrollo Android.
-   **Kotlin**: Lenguaje de programación principal utilizado en la aplicación.
-   **Jetpack Compose**: Biblioteca para la creación de interfaces de usuario declarativas
