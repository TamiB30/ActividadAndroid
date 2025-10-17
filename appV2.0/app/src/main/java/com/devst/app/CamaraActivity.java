package com.devst.app;

import android.Manifest;
import android.content.ContentValues;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.core.content.ContextCompat;

import java.text.SimpleDateFormat;
import java.util.Date;

//Permite tomar fotos usando la cámara del dispositivo o seleccionar imágenes de la galeria
public class CamaraActivity extends BaseActivity {

    private ImageView imagenPrevia;//Vista para mostrar la imagen seleccionada o capturada
    private Uri urlImagen;//URI donde se guardará la foto tomada

    /**Launcher para seleccionar imágenes desde la galeria
     Devuelve la URI de la imagen seleccionada y la muestra en ImagePreview*/
    private final ActivityResultLauncher<String> galeriaLauncher =
            registerForActivityResult(new ActivityResultContracts.GetContent(), uri -> {
                if (uri != null) {
                    imagenPrevia.setImageURI(uri);
                } else {
                    Toast.makeText(this, "No se seleccionó ninguna imagen", Toast.LENGTH_SHORT).show();
                }
            });

    //Launcher para permiso de cámara al usuario
    private final ActivityResultLauncher<String> permisoCamaraLauncher =
            registerForActivityResult(new ActivityResultContracts.RequestPermission(), granted -> {
                if (granted) tomarFoto();
                else Toast.makeText(this, "Permiso denegado", Toast.LENGTH_SHORT).show();
            });

    //Launcher para tomar una foto con la cámara, recibe un URI donde guardar la imagen y luego la muestra
    private final ActivityResultLauncher<Uri> takePictureLauncher =
            registerForActivityResult(new ActivityResultContracts.TakePicture(), ok -> {
                if (ok && urlImagen != null) imagenPrevia.setImageURI(urlImagen);
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camara);

        //Inicializanos la vista de la imagen
        imagenPrevia = findViewById(R.id.imagenPrevia);

        //Botón para abrir la galeria
        ImageButton btnGaleria = findViewById(R.id.btnGaleria);
        btnGaleria.setOnClickListener(v -> galeriaLauncher.launch("image/*"));

        //Botón para tomar foto con la cámara
        ImageButton btnTomarFoto = findViewById(R.id.btnTomarFoto);
        btnTomarFoto.setOnClickListener(v -> checkPermisoYTomar());
    }

    //Comprueba si el usuario ha concedidp permiso de cámara, Si no, solicita permiso.
    private void checkPermisoYTomar() {
        boolean granted = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                == PackageManager.PERMISSION_GRANTED;
        if (granted) tomarFoto();
        else permisoCamaraLauncher.launch(Manifest.permission.CAMERA);
    }

    /**Genera un nombre único para la foto con timestamp, crea un contentValues
     para guardarala en MediaStore y lanza la cámara para capturar la foto*/
    private void tomarFoto() {
        //Generar nombre único con fecha y hora
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String nombre = "IMG_" + timeStamp + ".jpg";

        //Preparar información de la imagen
        ContentValues values = new ContentValues();
        values.put(MediaStore.Images.Media.DISPLAY_NAME, nombre);
        values.put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg");
        values.put(MediaStore.Images.Media.RELATIVE_PATH, Environment.DIRECTORY_PICTURES);

        //Insertar en MediaStore y obtener URI
        urlImagen = getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);

        //lanzar la cámara con el URI donde se guardará la foto
        takePictureLauncher.launch(urlImagen);
    }
}
