package com.example.manu.lilleadvisor;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    private Button evenementButton;
    private Button collectifsButton;
    private Button adminButton;
    private Button musicButton;
    private Button mapButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        evenementButton = (Button) findViewById(R.id.activity_main_evenements_btn);
        collectifsButton = (Button) findViewById(R.id.activity_main_collectifs_btn);
        adminButton = (Button) findViewById(R.id.activity_main_admin_btn);
        musicButton = findViewById(R.id.activity_main_song_btn);
        mapButton = findViewById(R.id.activity_main_carte_btn);


        adminButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent LoginActivity = new Intent(MainActivity.this,  LoginActivity.class);
                startActivity(LoginActivity);
            }
        });

        evenementButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Evenement = new Intent(MainActivity.this,  EvenementActivity.class);
                startActivity(Evenement);
            }
        });

        mapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent map = new Intent(MainActivity.this,  MapsActivity.class);
                startActivity(map);
            }
        });

        collectifsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Collectif = new Intent(MainActivity.this,  CollectifActivity.class);
                startActivity(Collectif);
            }
        });

        musicButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Music = new Intent(MainActivity.this,  MusicYoutubeActivity.class);
                  startActivity(Music);
            }
        });

    }

    public void logo_main_fb(View view) {
        String url = "https://www.facebook.com/BePartofthMovement/";
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);
    }

    public void logo_acceuil_site(View view) {
        String url = "http://www.bpm-community.com/";
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);
    }
}
