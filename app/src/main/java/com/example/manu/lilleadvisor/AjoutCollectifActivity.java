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

public class AjoutCollectifActivity extends AppCompatActivity {

    private EditText mDescription;
    private EditText mVille;
    private EditText mNom;

    private Button mAjoutEvnt;

    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajout_collectif);
        mNom = findViewById(R.id.nomcollectif_field);
        mDescription = findViewById(R.id.descriptioncollectif_field);
        mVille = findViewById(R.id.villecollectif_field);

        mAjoutEvnt = findViewById(R.id.ajoutevnt_btn);

        mDatabase = FirebaseDatabase.getInstance().getReference().child("Collectifs");

        mAjoutEvnt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {

                if (!validateForm())
                {

                }else
                {
                    String nom = mNom.getText().toString().trim();
                    String description = mDescription.getText().toString().trim();
                    String adresse = mVille.getText().toString().trim();

                    HashMap<String, String> dataMap = new HashMap<String, String>();
                    dataMap.put("Nom", nom);
                    dataMap.put("Description", description);
                    dataMap.put("Ville", adresse);

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

        String email = mNom.getText().toString();
        if (TextUtils.isEmpty(email)) {
            mNom.setError("Required");
            valid = false;
        } else {
            mNom.setError(null);
        }

        String description = mDescription.getText().toString();
        if (TextUtils.isEmpty(description)) {
            mDescription.setError("Required");
            valid = false;
        } else {
            mDescription.setError(null);
        }

        String adresse = mVille.getText().toString();
        if (TextUtils.isEmpty(adresse)) {
            mVille.setError("Required");
            valid = false;
        } else {
            mVille.setError(null);
        }


        return valid;
    }

    private void toastMessage(String message){
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
}
