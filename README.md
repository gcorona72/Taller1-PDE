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

```kotlin
val navController = rememberNavController()
var userName by remember { mutableStateOf("") }