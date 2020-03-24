package com.example.babybook;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.babybook.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity {
    EditText emailId, password;
    Button btnSignIn;
    TextView tvSignUp;
    FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();

        //DatabaseHelper db = new DatabaseHelper(this);

        mFirebaseAuth = FirebaseAuth.getInstance();
        emailId = findViewById(R.id.mail);
        password = findViewById(R.id.mdp);
        btnSignIn = findViewById(R.id.buttonSignIn);
        tvSignUp = findViewById(R.id.account);

        emailId.setTextColor(Color.parseColor("#FFFFFF"));
        password.setTextColor(Color.parseColor("#FFFFFF"));

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginUserAccount();
            }
        });

        mAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                //FirebaseUser mFirebaseUser = mFirebaseAuth.getCurrentUser();
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                if(user != null){
                    Toast.makeText(Login.this,"You are logged in",Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(Login.this, Dashboard.class);
                    startActivity(i);
                }
                else{
                    Toast.makeText(Login.this,"Please Login",Toast.LENGTH_SHORT).show();
                }
            }
        };

        tvSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RedirectSignUp();
            }
        });
    }

    private void RedirectSignUp(){
        Intent intSignUp = new Intent(Login.this, CreateAccount.class);
        startActivity(intSignUp);
    }

    private void loginUserAccount(){
        String email = emailId.getText().toString();
        String pwd = password.getText().toString();

        if(email.isEmpty()){
            emailId.setError("Please enter email id");
            emailId.requestFocus();
        }
        else  if(pwd.isEmpty()){
            password.setError("Please enter your password");
            password.requestFocus();
        }
        else  if(email.isEmpty() && pwd.isEmpty()){
            Toast.makeText(Login.this,"Fields Are Empty!",Toast.LENGTH_SHORT).show();
        }
        else  if(!(email.isEmpty() && pwd.isEmpty())){
            mFirebaseAuth.signInWithEmailAndPassword(email, pwd).addOnCompleteListener(Login.this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(!task.isSuccessful()){
                        Toast.makeText(Login.this,"Login Error, Please Login Again",Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(getApplicationContext(),"Login successfully", Toast.LENGTH_LONG).show();
                        Intent intToDashboard = new Intent(Login.this, Dashboard.class);
                        startActivity(intToDashboard);
                    }
                }
            });
        }
        else{
            Toast.makeText(Login.this,"Error Occurred!",Toast.LENGTH_SHORT).show();
        }
    }

}