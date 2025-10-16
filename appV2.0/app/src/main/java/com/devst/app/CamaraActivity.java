package com.devst.app;

import java.util.Date;
import android.Manifest;
import android.content.ContentValues;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;

public class CamaraActivity extends BaseActivity {

    private ImageView imagenPrevia;
    private Uri urlImagen;

    //Launcher para galeria
    private final ActivityResultLauncher<String> galeriaLauncher =
            registerForActivityResult(new ActivityResultContracts.GetContent(), uri -> {
                if (uri != null) {
                    imagenPrevia.setImageURI(uri);
                } else {
                    Toast.makeText(this, "No se seleccionó ninguna imagen", Toast.LENGTH_SHORT).show();
                }
            });

    //Launcher para permiso de camara
    private final ActivityResultLauncher<String> permisoCamaraLauncher =
            registerForActivityResult(new ActivityResultContracts.RequestPermission(), granted -> {
                if (granted) tomarFoto();
                else Toast.makeText(this, "Permiso denegado", Toast.LENGTH_SHORT).show();
            });

    //Launcher de cámara
    private final ActivityResultLauncher<Uri> takePictureLauncher =
            registerForActivityResult(new ActivityResultContracts.TakePicture(), ok -> {
                if (ok && urlImagen != null) imagenPrevia.setImageURI(urlImagen);
            });


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camara);

        ImageButton btnGaleria = findViewById(R.id.btnGaleria);
        btnGaleria.setOnClickListener(v -> galeriaLauncher.launch("image/*"));

        imagenPrevia = findViewById(R.id.imagenPrevia);
        ImageButton btnTomarFoto = findViewById(R.id.btnTomarFoto);

        btnTomarFoto.setOnClickListener(v -> checkPermisoYTomar());
    }

    private void checkPermisoYTomar() {
        boolean granted = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                == PackageManager.PERMISSION_GRANTED;
        if (granted) tomarFoto();
        else permisoCamaraLauncher.launch(Manifest.permission.CAMERA);
    }

    private void tomarFoto() {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String nombre = "IMG_" + timeStamp + ".jpg";

        ContentValues values = new ContentValues();
        values.put(MediaStore.Images.Media.DISPLAY_NAME, nombre);
        values.put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg");
        values.put(MediaStore.Images.Media.RELATIVE_PATH, Environment.DIRECTORY_PICTURES);

        Uri uri = getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
        urlImagen = uri;

        takePictureLauncher.launch(uri);
    }

    private File crearArchivoImagen() throws IOException {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String nombre = "IMG_" + timeStamp + "_";
        File dir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        return File.createTempFile(nombre, ".jpg", dir);
    }
}
