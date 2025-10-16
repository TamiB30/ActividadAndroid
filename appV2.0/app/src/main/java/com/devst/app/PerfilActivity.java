package com.devst.app;

import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class PerfilActivity extends BaseActivity {

    //Encapsulamiento
    private String correoUsuarioPerfil = "";
    private String contrasennaUsuarioPerfil = "";
    private TextView editCorreoUsuario, editContrasennaUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_perfil);
        String emailRecibido = getIntent().getStringExtra("email_usuario");
        if (emailRecibido == null) emailRecibido = "";

        //Referencias
        editCorreoUsuario = findViewById(R.id.editCorreoUsuario);
        editCorreoUsuario.setText(emailRecibido);

    }

}