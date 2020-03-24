package com.example.babybook;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
<<<<<<< HEAD
import android.graphics.Color;
=======
>>>>>>> a52cbe9bd4e7201b3a5386bf886694fc8d24d344
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.babybook.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class CreateAccount extends AppCompatActivity {
    EditText emailId, password, name, lastName;
    Button signUp;
    TextView acc;
    private FirebaseAuth mAuth;
    FirebaseDatabase database;
    private static final String TAG = "EmailPassword";
    private DatabaseReference ref;
    private Baby baby;
    private TextView mStatusTextView;
    private TextView mDetailTextView;
    FirebaseFirestore fStore;
    String userID;
    private ProgressBar progressbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);
        setProgress(R.id.progressBar);

        final DatabaseHelper myDB = new DatabaseHelper(this);
        //Important, on recupere l'instance
        mAuth = FirebaseAuth.getInstance();

<<<<<<< HEAD
=======
        final BabyManager m = new BabyManager(this);
        m.open();

        mFirebaseAuth = FirebaseAuth.getInstance();
>>>>>>> a52cbe9bd4e7201b3a5386bf886694fc8d24d344
        emailId = findViewById(R.id.email);
        name = findViewById(R.id.name);
        lastName = findViewById(R.id.lastName);
        password = findViewById(R.id.password);
        signUp = findViewById(R.id.buttonSignUp);
        acc = findViewById(R.id.acc);

        baby = new Baby();

        fStore = FirebaseFirestore.getInstance();

        database = FirebaseDatabase.getInstance();
        ref = database.getReference().child("Baby");

        emailId.setTextColor(Color.parseColor("#FFFFFF"));
        password.setTextColor(Color.parseColor("#FFFFFF"));
        name.setTextColor(Color.parseColor("#FFFFFF"));
        lastName.setTextColor(Color.parseColor("#FFFFFF"));

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailId.getText().toString();
                String pass = password.getText().toString();
                String nom = lastName.getText().toString();
                String prenom = name.getText().toString();

                m.addBaby(new Baby(0, prenom, nom, 1998));

                Cursor c = m.getBaby();
                if (c.moveToFirst())
                {
                    do {
                        Log.d("test",
                                c.getInt(c.getColumnIndex(BabyManager.KEY_ID_BABY)) + "," +
                                        c.getString(c.getColumnIndex(BabyManager.KEY_NAME_BABY)) + "," +
                                        c.getString(c.getColumnIndex(BabyManager.KEY_LASTNAME_BABY)) + "," +
                                        c.getString(c.getColumnIndex(BabyManager.KEY_DATE_BABY))
                        );
                    }
                    while (c.moveToNext());
                }
                c.close();
                m.close();

                if(email.isEmpty()){
                    emailId.setError("Veuillez saisir votre adresse email");
                    emailId.requestFocus();
                }
                else if(pass.isEmpty()){
                    password.setError("Veuillez saisir votre mot de passe");
                    password.requestFocus();
                }
                else if(prenom.isEmpty()){
                    name.setError("Veuillez saisir le prenom de votre enfant");
                    name.requestFocus();
                }
                else if(nom.isEmpty()){
                    lastName.setError("Veuillez saisir le nom de votre enfant");
                }
                else if(!(email.isEmpty() && pass.isEmpty() && prenom.isEmpty() && nom.isEmpty())) {
<<<<<<< HEAD
                    baby.setName_baby(prenom);
                    baby.setLastName_baby(nom);
                   Toast.makeText(CreateAccount.this, "données enregistrées", Toast.LENGTH_LONG).show();
                   mAuth.createUserWithEmailAndPassword(email, pass)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(CreateAccount.this, "User created", Toast.LENGTH_SHORT).show();
                                        userID = mAuth.getCurrentUser().getUid();
                                        FirebaseDatabase.getInstance().getReference().child("Baby").child(userID).setValue(baby);
                                        startActivity(new Intent(getApplicationContext(), Dashboard.class));
                                    } else {
                                        Toast.makeText(CreateAccount.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });

=======
                    mFirebaseAuth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener(CreateAccount.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(!task.isSuccessful()){
                                Toast.makeText(CreateAccount.this, "Erreur 1", Toast.LENGTH_SHORT).show();
                            }
                            else{
                                startActivity(new Intent(CreateAccount.this, Dashboard.class));
                            }
                        }
                    });
>>>>>>> a52cbe9bd4e7201b3a5386bf886694fc8d24d344
                }
                else{
                    Toast.makeText(CreateAccount.this, "Erreur 2", Toast.LENGTH_SHORT).show();
                }
            }
        });
        acc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(CreateAccount.this, Login.class);
                startActivity(i);
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();

    }

}
