# AndroidPrueba

## Resumen del Proyecto
Aplicación Android de ejemplo que demuestra manejo de cámara, perfil de usuario, intents implícitos y explícitos, navegación entre actividades con animaciones personalizadas y uso de LocationManager.

- **Versión de Android:** Min SDK 23, Target SDK 33  
- **Android Gradle Plugin (AGP):** 8.0  
- **Librerías internas:** Material Components 

---

## Funcionalidades Principales

- Inicio de sesión simulado con validación de email y contraseña.
- Pantalla de bienvenida con botones que lanzan distintas acciones.
- Perfil de usuario editable.
- Cámara integrada usando `ActivityResultLauncher`.
- Uso de `LocationManager` (ubicación simulada o real, según tu implementación).
- Animaciones entre actividades usando `BaseActivity`.

---

## Intents Implementados

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

### Explícitos (3)
1. **Ir a PerfilActivity con resultado**  
   - Prueba: Presionar botón “Ir a Perfil” -> editar nombre -> volver a Home -> texto actualizado  

2. **Abrir CamaraActivity**  
   - Prueba: Presionar botón “Cámara del celular” -> se abre la cámara integrada  

3. **Volver a LoginActivity**  
   - Prueba: Presionar botón “Volver” -> regresa a la pantalla de login  

---

## Capturas de Pantalla / GIF

| Pantalla | Imagen / GIF |
|----------|--------------|
| Login | ![Login](screenshots/login.png) |
| Home con botones | ![Home](screenshots/home.png) |
| Perfil de usuario | ![Perfil](screenshots/perfil.png) |
| Cámara integrada | ![Camara](screenshots/camara.png) |

> Reemplaza los placeholders con tus imágenes o GIFs reales.

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

