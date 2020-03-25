package com.example.babybook;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.FirebaseDatabase;


public class Dashboard extends AppCompatActivity {
    private static final String TAG = "Dashboard";
    Button btnLogout;
    TextView NAME, LASTNAME;
    ImageView conseil, croissance, poids, vaccin, alimentation;
    String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        setTitle("Votre Dashboard");

        btnLogout = findViewById(R.id.logout);
        NAME = findViewById(R.id.prenom_baby);
        LASTNAME = findViewById(R.id.nom_baby);
        conseil = findViewById(R.id.conseil);
        croissance = findViewById(R.id.croissance);
        poids = findViewById(R.id.poids);
        vaccin = findViewById(R.id.vaccin);
        alimentation = findViewById(R.id.alimentation);

        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference().child("Baby");
        userID = currentUser.getUid();

        //Lecture de la BDD Firebase de l'utilisateur courant via son ID unique (attribué par firebase) : Baby => UserID => nom/prenom/date
        mDatabase.child(userID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Baby baby = dataSnapshot.getValue(Baby.class);
                Toast.makeText(Dashboard.this, "Bienvenue " + baby.getName_baby() + " " + baby.getLastName_baby() + " !" , Toast.LENGTH_SHORT).show(); //Bulle notif de bienvenue

                //ecriture depuis la BDD sur les textView de nom et prenom
                NAME.setText(baby.getName_baby());
                LASTNAME.setText(baby.getLastName_baby());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.w(TAG, "Failed to read value", databaseError.toException());
            }
        });

        NAME.setTextSize(60);
        LASTNAME.setTextSize(60);

        //Blindage si l'utilisateur n'est pas connecté, le retourne à l'ecran de connexion
        if (!checkLoggedIn()) {
            startActivity(new Intent(Dashboard.this, Login.class));
            finish();
            return;
        }

        //Deconnexion utilisateur
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intToMain = new Intent(Dashboard.this, Login.class);
                startActivity(intToMain);
            }
        });

        //Lance l'activité suivi de croissance
        croissance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent=new Intent(Dashboard.this, CroissanceView.class);
                startActivity(myIntent);
            }
        });

        //Lance l'activité conseils
        conseil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent=new Intent(Dashboard.this, Conseil.class);
                startActivity(myIntent);
            }
        });

        //Lance l'activité suivi de poids
        poids.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent=new Intent(Dashboard.this, PoidsView.class);
                startActivity(myIntent);
            }
        });

        //Lance l'activité suivi des vaccins
        vaccin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent=new Intent(Dashboard.this, Vaccin.class);
                startActivity(myIntent);
            }
        });

        //Lance l'activité suivi de l'alimentation
        alimentation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent=new Intent(Dashboard.this, Alimentation.class);
                startActivity(myIntent);
            }
        });
    }

    //Verification de bonne connexion
    private boolean checkLoggedIn() {
        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        if (firebaseUser == null) {
            return false;
        } else {
            return true;
        }
    }
}



