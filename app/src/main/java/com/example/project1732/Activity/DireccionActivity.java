package com.example.project1732.Activity;

import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;


import com.example.project1732.R;

import java.util.Random;

public class DireccionActivity extends AppCompatActivity {
    private Button btnAceptar;
    private static final String CHANNEL_ID = "channel_id";
    private static final int NOTIFICATION_ID = 1;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_direccion);
        btnAceptar = findViewById(R.id.btnAceptar);
        crearCanal();
        btnAceptar.setOnClickListener(v -> {
            Intent intent = new Intent(DireccionActivity.this, ProcesoPedidoActivity.class);
            startActivity(intent);
            crearNotificacion();
        });
    }

    @SuppressLint("MissingPermission")
    private void crearNotificacion() {
        Random random = new Random();

        // Generar un número aleatorio entre 1 y 100 (cambiar según tus necesidades)
        int numeroAleatorio = random.nextInt(100) + 1;
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID);
        builder.setContentTitle("Tu pedido se esta preparando con la orden : "+numeroAleatorio);
        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setColor(Color.MAGENTA);
        builder.setVibrate(new long[]{1000, 200, 1000, 2000});
        builder.setTicker("Tu pedido se esta preparando");
        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(this);
        notificationManagerCompat.notify(NOTIFICATION_ID, builder.build());

    }

    private void crearCanal() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "Notificaciones";
            String description = "Canal para notificaciones";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel notificationChannel = new NotificationChannel(CHANNEL_ID, name, importance);
            notificationChannel.setDescription(description);
            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(Color.RED);
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            if (notificationManager != null) {
                notificationManager.createNotificationChannel(notificationChannel);
            }
        }
    }
}
