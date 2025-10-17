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

## Capturas de Pantalla / GIF

| Pantalla | Imagen / GIF |
|----------|--------------|
| Login | ![Login](https://github.com/user-attachments/assets/0b8fe1af-3124-46dc-adc6-7bd82c75107e) |
| Home | ![Home](https://github.com/user-attachments/assets/c8732fb6-882f-4ca2-9797-4a6ba9f31140) |
| Perfil | ![Perfil](https://github.com/user-attachments/assets/d41df796-b166-484b-9910-634a72cf5a53) | 
| Cámara | ![Cámara](https://github.com/user-attachments/assets/f2866e4a-ffd7-4914-b502-ae3fc45a01a2) |

---

### 🔹 Implícitos (5)

| Acción | Descripción | Componente del sistema |
|--------|--------------|------------------------|
| `Intent.ACTION_VIEW` | Abrir navegador web con URL | Navegador |
| `Intent.ACTION_SENDTO` | Enviar correo | Cliente de correo |
| `Intent.ACTION_SEND` | Compartir texto o imagen | Compartir global |
| `Settings.ACTION_WIFI_SETTINGS` | Abrir ajustes de Wi-Fi | Configuración |
| `MediaStore.ACTION_IMAGE_CAPTURE` | Tomar foto con cámara | Cámara del sistema |

**Prueba:**
1. En *HomeActivity*, toca **Abrir Web** → abre navegador.  
2. Toca **Enviar Correo** → abre cliente de correo.  
3. Toca **Compartir** → muestra el menú del sistema.  
4. Toca **Ajustes Wi-Fi** → abre la configuración del sistema.  
5. En *CamaraActivity*, toca **Tomar Foto** → abre la cámara.

![Acciones](https://github.com/user-attachments/assets/bb6b2957-ce86-4a6e-9074-83ee839e610f)

---

## 📱APK de Prueba

- Ruta del APK debug: `app/build/outputs/apk/debug/app-debug.apk`  
- **O** Si no abre directamente, puedes compilarlo nuevamente desde Android Studio: 
  1. Abrir proyecto en Android Studio.  
  2. Seleccionar `Build > Build Bundle(s) / APK(s) > Build APK(s)`  
  3. Encontrar el APK en la ruta anterior.  

---

## 📝 Notas Adicionales

- Todas las animaciones se implementaron usando `BaseActivity` y `overridePendingTransition`.  
- Los botones usan Material Components con colores personalizados (azul/cian/morado).  
- Compatibilidad con distintas resoluciones gracias a `ConstraintLayout` y `NestedScrollView`.  
- Uso seguro de `MediaStore` para guardar fotos sin requerir almacenamiento externo completo.
