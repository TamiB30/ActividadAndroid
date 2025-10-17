package com.devst.app;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.hardware.camera2.CameraManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends BaseActivity {
    //Encapsulamientos.
    private String emailUsuario = "";
    private TextView tvBienvenida;

    //Variables para utilizar linterna y cámara.
    private Button btnLinterna;
    private CameraManager camara;
    private String camaraID = null;
    private boolean luz = false;

    private Intent mapIntent;//Intent para mapa

    //Launcher para iniciar el perfil y recibir resultado.
    private final ActivityResultLauncher<Intent> editarPerfilLauncher =
            registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result ->{
                if(result.getResultCode() == RESULT_OK && result.getData() != null){
                    String nombre = result.getData().getStringExtra("nombre_editado");
                    if(nombre != null){
                        tvBienvenida.setText("Hola, " + nombre);
                    }
                }
            });

    @SuppressLint("QueryPermissionsNeeded")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Habilita Edge-to-Edge (pantalla completa sin cortes)
        EdgeToEdge.enable(this);

        //Layout principal
        setContentView(R.layout.activity_home);

        //Inicialización de views
        tvBienvenida = findViewById(R.id.tvBienvenida);

        Button btnIrPerfil = findViewById(R.id.btnIrPerfil);
        Button btnAbrirWeb = findViewById(R.id.btnAbrirWeb);
        Button btnEnviarCorreo = findViewById(R.id.btnEnviarCorreo);
        Button btnCompartir = findViewById(R.id.btnCompartir);
        Button btnCamara = findViewById(R.id.btnCamara);
        Button btnConfiguracion = findViewById(R.id.btnConfiguracion);
        Button btnMapa = findViewById(R.id.btnMapa);
        Button btnVolverLogin = findViewById(R.id.btnVolverLogin);

        //Recibir datos desde el login.
        emailUsuario = getIntent().getStringExtra("email_usuario");
        if(emailUsuario == null || emailUsuario.isEmpty()) emailUsuario = "Usuario";
        tvBienvenida.setText("Bienvenido: " + emailUsuario);

        //Lanzador para pedir permiso de cámara en tiempo de ejecución.
        final ActivityResultLauncher<String>permisoCamaraLauncher = registerForActivityResult(new ActivityResultContracts.RequestPermission(), granted ->{
            if (granted){
             //Si concede permiso, se intenta prender y apagar la linterna.
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

        //Evento Explicito para volver al login.
        btnVolverLogin.setOnClickListener(view -> {
            Intent intent = new Intent(HomeActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        });

        //Evento Inplicito para abrir una pagina web.
        btnAbrirWeb.setOnClickListener(view -> {
            Uri url = Uri.parse("https://www.todotoner.cl");
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

        //Evento Implicito compartir texto.
        btnCompartir.setOnClickListener(view -> {
            Intent compartir = new Intent(Intent.ACTION_SEND);
            compartir.setType("text/plain");
            compartir.putExtra(Intent.EXTRA_TEXT, "HOLA MUNDO DESDE ANDROID");
            startActivity(Intent.createChooser(compartir, "Compartiendo: "));
        });

        //Evento abrir actividad de cámara.
        btnCamara.setOnClickListener(view -> {
            Intent camara = new Intent(HomeActivity.this, CamaraActivity.class);
            editarPerfilLauncher.launch(camara);
        });

        //Evento abrir ajustes de Wi-Fi.
        btnConfiguracion.setOnClickListener(view -> {
            Intent configuracion = new Intent(Settings.ACTION_WIFI_SETTINGS);
            startActivity(configuracion);
        });

        //Evento abrir Google Maps.
        btnMapa.setOnClickListener(view -> {
            Uri gmmIntentUri = Uri.parse("geo:-33.449303, -70.662225?q=Instituto Santo Tomás");
            Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
            mapIntent.setPackage("com.google.android.apps.maps");
            startActivity(mapIntent);
        });
    }
}