package com.example.recibesms;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText editTextTelefono, editTextMensaje;
    private Button buttonEnvio, buttonSalir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextTelefono = findViewById(R.id.editTextTelefono);
        editTextMensaje = findViewById(R.id.editTextMensaje);
        buttonEnvio = findViewById(R.id.buttonEnvio);
        buttonSalir = findViewById(R.id.buttonSalir);

        buttonEnvio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String telefono = editTextTelefono.getText().toString();
                String mensaje = editTextMensaje.getText().toString();
                enviaSMS(telefono, mensaje);
            }
        });
    }

    private void checkSMSStatePermission(){
        int permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS);
        if(permissionCheck != PackageManager.PERMISSION_GRANTED){
            Log.i("mensaje", "No tienes permisos de enviar SMS");
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.SEND_SMS}, 255);
        } else {
            Log.i("mensaje", "Si tienes permiso de enviar SMS");
        }
    }

    private void enviaSMS(String telefono, String mensaje){
        SmsManager smsManager = SmsManager.getDefault();
        smsManager.sendTextMessage(telefono, null, mensaje, null, null);
    }
}
