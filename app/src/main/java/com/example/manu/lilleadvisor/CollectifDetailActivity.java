package com.example.manu.lilleadvisor;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class CollectifDetailActivity extends AppCompatActivity {

    private String intentNom;

    private TextView mFieldNom;
    private TextView mFieldVille;
    private TextView mFieldDescrption;

    private FirebaseDatabase mFirebaseDatabase;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private DatabaseReference mDatabase;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collectif_detail);

        mAuth = FirebaseAuth.getInstance();
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mDatabase = mFirebaseDatabase.getReference().child("Collectifs");
        FirebaseUser user = mAuth.getCurrentUser();

        mFieldDescrption = findViewById(R.id.fieldDescription);
        mFieldNom = findViewById(R.id.fieldNom);
        mFieldVille = findViewById(R.id.fieldVille);

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

        intentNom = getIntent().getExtras().getString("NOM");

        toastMessage(intentNom);

        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                getDetail(dataSnapshot);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });

    }

    private void getDetail(DataSnapshot dataSnapshot)
    {

        for(DataSnapshot ds : dataSnapshot.getChildren())
        {
            String nom = ds.child("Nom").getValue(String.class);

            if (nom.equals(intentNom))
            {
                ItemLille newItemLille = new ItemLille();
                newItemLille.setNom(ds.child("Nom").getValue(String.class));
                newItemLille.setDescription(ds.child("Description").getValue(String.class));
                newItemLille.setVille(ds.child("Ville").getValue(String.class));

                mFieldNom.setText(newItemLille.getNom());
                mFieldVille.setText(newItemLille.getVille());
                mFieldDescrption.setText(newItemLille.getDescription());

            }
        }


    }


    private void toastMessage(String message){
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
}
