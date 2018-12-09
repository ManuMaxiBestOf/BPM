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

public class AjoutEvenementActivity extends AppCompatActivity {

    private EditText mNom;
    private EditText mDescription;
    private EditText mAdresse;
    private EditText mDate;
    private EditText mLongt;
    private EditText mLat;

    private Button mAjoutEvnt;

    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajout_evenement);

        mNom = findViewById(R.id.nomevenement_field);
        mDescription = findViewById(R.id.descriptionevenement_field);
        mAdresse = findViewById(R.id.adresseevenement_field);
        mDate = findViewById(R.id.dateevenement_field);
        mLongt = findViewById(R.id.fieldLongt);
        mLat = findViewById(R.id.fieldLat);

        mAjoutEvnt = findViewById(R.id.ajoutevnt_btn);

        mDatabase = FirebaseDatabase.getInstance().getReference().child("Evenements");

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
                    String adresse = mAdresse.getText().toString().trim();
                    String date = mDate.getText().toString().trim();
                    String lat = mLongt.getText().toString().trim();
                    String longt = mLat.getText().toString().trim();

                    HashMap<String, String> dataMap = new HashMap<String, String>();
                    dataMap.put("Nom", nom);
                    dataMap.put("Description", description);
                    dataMap.put("Adresse", adresse);
                    dataMap.put("Date", date);
                    dataMap.put("Longitude", longt);
                    dataMap.put("Latitude", lat);

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

        String adresse = mAdresse.getText().toString();
        if (TextUtils.isEmpty(adresse)) {
            mAdresse.setError("Required");
            valid = false;
        } else {
            mAdresse.setError(null);
        }

        String date = mDate.getText().toString();
        if (TextUtils.isEmpty(date)) {
            mDate.setError("Required");
            valid = false;
        } else {
            mDate.setError(null);
        }

        return valid;
    }

    private void toastMessage(String message){
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
}
