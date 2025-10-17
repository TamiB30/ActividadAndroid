# 📱 AppIntent

## 🧾 Resumen del proyecto
**AppIntent** es una aplicación Android educativa que demuestra el uso de **Intents explícitos e implícitos** para la comunicación entre pantallas y servicios del sistema.  
Incluye ejemplos de navegación entre Activities, envío de correos, apertura de URLs, acceso a cámara, flash, configuración del sistema, mapas y más.  
Además, implementa animaciones personalizadas y hace uso del **LocationManager** para obtener la ubicación del dispositivo.

---

## ⚙️ Especificaciones técnicas

| Elemento | Versión / Detalle |
|-----------|------------------|
| 🧱 Android Studio | **Nārwhal 2025.1.4** |
| ⚙️ Gradle | **8.13** |
| 🧩 Android Gradle Plugin (AGP) | **8.13.0** |
| 📱 compileSdk | **36** |
| 📱 targetSdk | **36** |
| 📱 minSdk | **31** |
| ☕ Java version | **11** |
| 📦 Librerías principales | AppCompat 1.7.1, Material 1.13.0, Activity 1.11.0, ConstraintLayout 2.2.1, Lifecycle 2.9.3 |

---

## 🧭 Mapa de navegación

**Flujo principal de pantallas (Intents explícitos):**
Cada Activity extiende de `BaseActivity`, que aplica animaciones (`flip_in` y `flip_out`) en las transiciones.

<img width="841" height="389" alt="Diagrama sin título drawio (1)" src="https://github.com/user-attachments/assets/5a6acd15-56e0-486d-a090-12bd2d4572c2" />

---

## 📍 Funcionalidades principales

- **Inicio de sesión simulado** con validación de correo y contraseña.  
- **Uso de `LocationManager`** para obtener ubicación del dispositivo.  
- **Navegación explícita** entre pantallas (`Login`, `Home`, `Perfil`, `Cámara`).  
- **Invocación de acciones del sistema** mediante Intents implícitos:
  - Abrir navegador web.
  - Enviar correo electrónico.
  - Compartir contenido.
  - Abrir ajustes de Wi-Fi.
  - Tomar o seleccionar fotos desde la galería.

  ---

## 🧩 Intents implementados

### 🔹 Intents Explícitos (3)

| Actividad origen | Destino | Descripción |
|------------------|----------|-------------|
| `HomeActivity` | `PerfilActivity` | Muestra los datos del usuario. |
| `HomeActivity` | `CamaraActivity` | Permite tomar o seleccionar fotos. |
| `PerfilActivity` | `HomeActivity` | Regresa al inicio. |

**Prueba:**
1. Inicia sesión con correo `estudiante@st.cl` y contraseña `123456`.  
2. En Home, pulsa **Ir a Perfil** → se abre `PerfilActivity`.
4. Pulsa **Cámara** → se abre `CamaraActivity`.  
5. Pulsa **Volver** → regresa a `HomeActivity`.

### Implícitos (5)
1. **Abrir página web**  
   - Acción: `Intent.ACTION_VIEW`  
   - Ejemplo: Botón “Abrir sitio web” abre `https://www.todotoner.cl`  
   - Prueba: Presionar botón -> navegador se abre con la URL  

2. **Enviar correo**  
   - Acción: `Intent.ACTION_SENDTO` con `mailto:`  
   - Prueba: Presionar botón -> cliente de correo se abre con destinatario y asunto  

3. **Compartir texto**  
   - Acción: `Intent.ACTION_SEND`  
   - Prueba: Presionar botón -> aparece selector de apps para compartir  

4. **Abrir Google Maps**  
   - Acción: `Intent.ACTION_VIEW` con `geo:`  
   - Prueba: Presionar botón -> se abre Maps en la ubicación indicada  

5. **Abrir ajustes de Wi-Fi**  
   - Acción: `Settings.ACTION_WIFI_SETTINGS`  
   - Prueba: Presionar botón -> ajustes de Wi-Fi se abren  

---

## Capturas de Pantalla / GIF

| Pantalla | Imagen / GIF |
|----------|--------------|
| Login | ![Diseño sin título](https://github.com/user-attachments/assets/3210c82d-5af2-4aa9-98dd-9f8994b4f5fd)|
| Home | ![Home](screenshots/home.png) |
| Perfil | ![Perfil](screenshots/perfil.png) |
| Cámara | ![Camara](screenshots/camara.png) |



---

## APK de Prueba

- Ruta del APK debug: `app/build/outputs/apk/debug/app-debug.apk`  
- **O** Instrucciones para compilar:  
  1. Abrir proyecto en Android Studio.  
  2. Seleccionar `Build > Build Bundle(s) / APK(s) > Build APK(s)`  
  3. Encontrar el APK en la ruta anterior.  

---

## Notas Adicionales

- Todas las animaciones se implementaron usando `BaseActivity` y `overridePendingTransition`.  
- Los botones usan Material Components con colores personalizados (azul/cian/morado).  
- Compatibilidad con distintas resoluciones gracias a `ConstraintLayout` y `NestedScrollView`.  
- Uso seguro de `MediaStore` para guardar fotos sin requerir almacenamiento externo completo.
