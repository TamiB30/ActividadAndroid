package com.devst.app;


import android.content.Intent;
import android.hardware.camera2.CameraManager;
import android.net.Uri;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import androidx.activity.EdgeToEdge;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;



public class HomeActivity extends AppCompatActivity {


    //Encapsulamientos.
    private String emailUsuario = "";
    private TextView tvBienvenida;

    //Variables para utilizar linterna y cámara.
    private Button btnLinterna;
    private CameraManager camara;
    private String camaraID = null;
    private boolean luz = false;

    //Función para capturar resultados para el perfil.
    private final ActivityResultLauncher<Intent> editarPerfilLauncher =
            registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result ->{
                if(result.getResultCode() == RESULT_OK && result.getData() != null){
                    String nombre = result.getData().getStringExtra("nombre_editado");
                    if(nombre != null){
                        tvBienvenida.setText("Hola, " + nombre);
                    }
                }
            });


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home);

        tvBienvenida = findViewById(R.id.tvBienvenida);

        Button btnIrPerfil = findViewById(R.id.btnIrPerfil);
        Button btnAbrirWeb = findViewById(R.id.btnAbrirWeb);
        Button btnEnviarCorreo = findViewById(R.id.btnEnviarCorreo);
        Button btnCompartir = findViewById(R.id.btnCompartir);
        Button btnFlash = findViewById(R.id.btnFlash);
        Button btnCamara = findViewById(R.id.btnCamara);

        //Recibir datos desde el login.
        emailUsuario = getIntent().getStringExtra("email_usuario");
        if(emailUsuario == null) emailUsuario = "";
        tvBienvenida.setText("Bienvenido: " + emailUsuario);

        //Lanzador para pedir permiso de cámara en tiempo de ejecución.
        final ActivityResultLauncher<String>permisoCamaraLauncher = registerForActivityResult(new ActivityResultContracts.RequestPermission(), granted ->{
            if (granted){
                alternarFlash();  //Si concede permiso, se intenta prender y apagar la linterna.
            }else{
                Toast.makeText(this, "Permiso de cámara denegado", Toast.LENGTH_SHORT).show();
            }
        });

        //Evento Explicito iniciar vista perfil.
        btnIrPerfil.setOnClickListener(View -> {
            Intent perfil = new Intent(HomeActivity.this, PerfilActivity.class);
            perfil.putExtra("email_usuario", emailUsuario);
            editarPerfilLauncher.launch(perfil);
        });

        //Evento Inplicito para abrir una pagina web.
        btnAbrirWeb.setOnClickListener(view -> {
            Uri url = Uri.parse("http://www.santotomas.cl");
            Intent viewWeb = new Intent(Intent.ACTION_VIEW, url);
            startActivity(viewWeb);
        });

        //Evento Implicito correo.
        btnEnviarCorreo.setOnClickListener(view -> {
            Intent correo = new Intent(Intent.ACTION_SENDTO);
            correo.setData(Uri.parse("mailto:")); //Solo para uso de correo electronico.
            correo.putExtra(Intent.EXTRA_EMAIL, new String[]{emailUsuario});
            correo.putExtra(Intent.EXTRA_SUBJECT, "Prueba de Correo"); //Asunto del correo.
            correo.putExtra(Intent.EXTRA_TEXT, "Hola mundo desde el btn correo");
            startActivity(Intent.createChooser(correo, "Enviar correo a:"));
        });

        //Evento Implicito compartir.
        btnCompartir.setOnClickListener(view -> {
            Intent compartir = new Intent(Intent.ACTION_SEND);
            compartir.setType("text/plain");
            compartir.putExtra(Intent.EXTRA_TEXT, "HOLA MUNDO DESDE ANDROID");
            startActivity(Intent.createChooser(compartir, "Compartiendo: "));
        });

        //Evento abrir camara
        btnCamara.setOnClickListener(view -> {
            Intent camara = new Intent(HomeActivity.this, CamaraActivity.class);
            editarPerfilLauncher.launch(camara);
        });
    }
    private void alternarFlash() {
    }
}