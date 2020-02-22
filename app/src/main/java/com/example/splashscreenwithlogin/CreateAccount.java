package com.example.splashscreenwithlogin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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

public class CreateAccount extends AppCompatActivity {
    EditText emailId, password, name, lastName;
    Button signUp;
    TextView acc;
    FirebaseAuth mFirebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        mFirebaseAuth = FirebaseAuth.getInstance();
        emailId = findViewById(R.id.email);
        name = findViewById(R.id.name);
        lastName = findViewById(R.id.lastName);
        password = findViewById(R.id.password);
        signUp = findViewById(R.id.buttonSignUp);
        acc = findViewById(R.id.acc);

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
                    mFirebaseAuth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener(CreateAccount.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(!task.isSuccessful()){
                                Toast.makeText(CreateAccount.this, "Erreur", Toast.LENGTH_SHORT).show();
                            }
                            else{
                                startActivity(new Intent(CreateAccount.this, Dashboard.class));
                            }
                        }
                    });
                }
                else{
                    Toast.makeText(CreateAccount.this, "Erreur", Toast.LENGTH_SHORT).show();
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
