package com.example.manu.lilleadvisor;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class AdminActivity extends AppCompatActivity {

    private Button mAjoutEvenementBtn;
    private Button mAjoutCollectifBtn;
    private Button mAjoutMusic;
    private Button mSupCollectif;
    private Button mSupEvent;
    private Button mSupMuz;

    private Button mLogoutBtn;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        mAjoutCollectifBtn = findViewById(R.id.ajoutcollectif_btn);
        mAjoutEvenementBtn = findViewById(R.id.ajoutevenement_btn);
        mAjoutMusic = findViewById(R.id.ajoutmusic_btn);
        mSupCollectif = findViewById(R.id.supcollectif_btn);
        mSupEvent = findViewById(R.id.supevent_btn);
        mSupMuz =findViewById(R.id.supmusic_btn);

        mLogoutBtn = findViewById(R.id.logout_btn);

        mAuth = FirebaseAuth.getInstance();

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null){
                    //user is signed in
                    toastMessage("Signed In");
                }else{
                    //user not signed in
                    toastMessage("Signed Out");
                }

            }
        };


        mAjoutEvenementBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ajoutevt = new Intent(AdminActivity.this,  AjoutEvenementActivity.class);
                startActivity(ajoutevt);
            }
        });

        mAjoutCollectifBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ajoutcol = new Intent(AdminActivity.this,  AjoutCollectifActivity.class);
                startActivity(ajoutcol);
            }
        });

        mAjoutMusic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ajoutmusic = new Intent(AdminActivity.this,  AjoutMusicActivity.class);
                startActivity(ajoutmusic);
            }
        });

        mSupCollectif.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent supCol = new Intent(AdminActivity.this,  SupCollectifActivity.class);
                startActivity(supCol);
            }
        });
        mSupEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent supCol = new Intent(AdminActivity.this,  SupEventActivity.class);
                startActivity(supCol);
            }
        });
        mSupMuz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent supCol = new Intent(AdminActivity.this,  SupMusicActivity.class);
                startActivity(supCol);
            }
        });

        mLogoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.signOut();
                toastMessage("Logout");
                Intent sortir = new Intent(AdminActivity.this, LoginActivity.class);
                startActivity(sortir);
            }
        });



    }
    public void logo_acceuilEvenements(View view) {
        startActivity(new Intent(this,MainActivity.class));
    }

    private void toastMessage(String message){
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

}
