package com.example.manu.lilleadvisor;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class AjoutMusicActivity extends AppCompatActivity {

    private EditText mLien;
    private EditText mNom;

    private Button mAjoutEvnt;

    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajout_music);
        mLien = findViewById(R.id.embed_field);
        mNom = findViewById(R.id.nom_field);

        mAjoutEvnt = findViewById(R.id.ajoutevnt_btn);

        mDatabase = FirebaseDatabase.getInstance().getReference().child("Music");

        mAjoutEvnt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {

                if (!validateForm())
                {

                }else
                {
                    String lien = mLien.getText().toString().trim();
                    String nom = mNom.getText().toString().trim();

                    HashMap<String, String> dataMap = new HashMap<String, String>();
                    dataMap.put("Lien", lien);
                    dataMap.put("Nom", nom);

                    mDatabase.push().setValue(dataMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful())
                            {
                                toastMessage("Uploaded");
                            }else
                            {

                            }
                        }
                    });
                }

            }
        });

    }
    private boolean validateForm() {
        boolean valid = true;

        String lien = mLien.getText().toString();
        if (TextUtils.isEmpty(lien)) {
            mLien.setError("Required");
            valid = false;
        } else {
            mLien.setError(null);
        }

        String nom = mNom.getText().toString();
        if (TextUtils.isEmpty(lien)) {
            mNom.setError("Required");
            valid = false;
        } else {
            mNom.setError(null);
        }

        return valid;
    }

    private void toastMessage(String message){
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
}
