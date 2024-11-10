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
    -   `MainScreen`: Permite ingresar el nombre del usuario y lo guarda en la base de datos SQLite.
    -   `HomeScreen`: Muestra un saludo basado en la hora del día y el nombre del usuario ingresado.
    -   `SettingsScreen`: Proporciona opciones para cambiar el color de fondo de la aplicación.

* * * * *

### **Componentes Principales:**

#### 1\. **MainActivity.kt**

Este archivo configura la navegación utilizando **NavController** y las diferentes pantallas dentro de la aplicación.

-   **Objetivo**: Administrar la navegación entre las pantallas.
-   **Funcionalidad**:
    -   Se define un `NavHost` que contiene las rutas para `MainScreen`, `HomeScreen` y `SettingsScreen`.
    -   `userDatabase` es una instancia de `UserDatabase` que se utiliza para almacenar y recuperar datos de usuario.

#### 2\. **UserDatabase.kt**

Este archivo define la base de datos SQLite para almacenar los datos de los usuarios.

-   **Objetivo**: Proporcionar una base de datos para almacenar los datos de los usuarios.
-   **Funcionalidad**:
    -   Métodos para insertar, leer, actualizar y eliminar usuarios.

#### 3\. **UserContentProvider.kt**

Este archivo define un `ContentProvider` para proporcionar un punto de acceso a los datos de la base de datos SQLite.

-   **Objetivo**: Permitir que otras aplicaciones interactúen con los datos de manera segura y controlada.
-   **Funcionalidad**:
    -   Métodos para consultar, insertar, actualizar y eliminar datos a través de URIs.

#### 4\. **MainScreen.kt**

Este archivo define la pantalla principal donde el usuario puede ingresar su nombre.

-   **Objetivo**: Permitir al usuario ingresar su nombre y guardarlo en la base de datos.
-   **Funcionalidad**:
    -   Campo de texto para ingresar el nombre.
    -   Botón para guardar el nombre en la base de datos.
    -   Mensaje de confirmación de guardado.

#### 5\. **HomeScreen.kt**

Este archivo define la pantalla de inicio que muestra un saludo personalizado.

-   **Objetivo**: Mostrar un saludo basado en la hora del día y el nombre del usuario.
-   **Funcionalidad**:
    -   Mostrar la hora actual y un saludo personalizado.

#### 6\. **SettingsScreen.kt**

Este archivo define la pantalla de configuración donde el usuario puede cambiar el color de fondo de la aplicación.

-   **Objetivo**: Permitir al usuario personalizar el color de fondo de la aplicación.
-   **Funcionalidad**:
    -   Lista de colores para seleccionar.
    -   Cambio del color de fondo según la selección del usuario.
