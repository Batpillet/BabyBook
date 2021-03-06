package com.example.babybook;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;


public class CreateAccount extends AppCompatActivity {
    private EditText emailId, password, name, lastName;
    private Button signUp;
    private TextView acc;
    private FirebaseAuth mAuth;
    private DatabaseReference ref;
    private Baby baby;
    private FirebaseFirestore fStore;
    private String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);
        setProgress(R.id.progressBar);

        emailId = findViewById(R.id.email);
        name = findViewById(R.id.name);
        lastName = findViewById(R.id.lastName);
        password = findViewById(R.id.password);
        signUp = findViewById(R.id.buttonSignUp);
        acc = findViewById(R.id.acc);

        mAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        ref = FirebaseDatabase.getInstance().getReference().child("Baby");

        baby = new Baby();

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
}
